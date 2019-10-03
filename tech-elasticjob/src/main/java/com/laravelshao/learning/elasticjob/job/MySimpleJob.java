package com.laravelshao.learning.elasticjob.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;

/**
 * elastic-job 简单任务
 *
 * @author qinghua.shao
 * @date 2019/10/2
 * @since 1.0.0
 */
@Slf4j
public class MySimpleJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("当前分片为：{}", shardingContext.getShardingItem());
        //System.out.println("当前分片为："+shardingContext.getShardingItem());
    }

}
