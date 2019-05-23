package com.laravelshao.learning.spring;

import com.laravelshao.learning.spring.tx.service.TxPropagationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;

/**
 * @author shaoqinghua
 * @date 2019/5/23
 * @description Spring事务传播测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-tx-propagation.xml")
public class SpringTxPropagationTest {

    @Autowired
    private TxPropagationService txPropagationService;

    /**
     * 外围方法未开启事务，分别调用 {@link Propagation#REQUIRED} 修饰
     * 的内部方法会开启内部方法自己的事务，且开启的事务相互独立，互不影响。
     *
     * <p>测试结果："张三"、"李四" 均插入。
     * 外围方法未开启事务，插入"张三"、"李四"方法在自己的事务中独立运行，
     * 外围方法异常不影响内部插入"张三"、"李四"方法独立的事务。
     */
    @Test
    public void notransaction_exception_required_required() {
        txPropagationService.notransaction_exception_required_required();
    }

    /**
     * 测试结果："张三"插入，"李四"未插入。
     * 外围方法没有事务，插入"张三"、"李四"方法都在自己的事务中独立运行，
     * 所以插入"李四"方法抛出异常只会回滚插入"李四"方法，插入"张三"方法不受影响。
     */
    @Test
    public void notransaction_required_required_exception() {
        txPropagationService.notransaction_required_required_exception();
    }


    

}
