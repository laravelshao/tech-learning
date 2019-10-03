package com.laravelshao.learning.elasticjob.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.laravelshao.learning.elasticjob.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * elastic-job 数据流式任务
 *
 * @author qinghua.shao
 * @date 2019/10/3
 * @since 1.0.0
 */
@Slf4j
public class MyDataflowJob implements DataflowJob<Order> {

    private List<Order> orders = new ArrayList<>();

    {
        for (int i = 0; i < 100; i++) {
            orders.add(new Order(i + 1, 0));
        }
    }

    @Override
    public List<Order> fetchData(ShardingContext shardingContext) {
        // 订单号 % 分片总数 == 当前分片项
        List<Order> orderList = orders.stream()
                .filter(o -> o.getStatus() == 0)
                .filter(o -> o.getOrderId() % shardingContext.getShardingTotalCount() == shardingContext.getShardingItem())
                .collect(toList());

        List<Order> subList = null;
        if (!CollectionUtils.isEmpty(orderList)) {
            subList = orderList.subList(0, 10);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LocalTime time = LocalTime.now();
        log.info(time + " 当前分片项：{}, 当前抓取的数据是：{}", shardingContext.getShardingItem(), subList);
        return subList;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Order> data) {
        data.forEach(o -> o.setStatus(1));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime time = LocalTime.now();
        log.info(time + " 当前分片项：{}, 正在处理数据......", shardingContext.getShardingItem());
    }
}
