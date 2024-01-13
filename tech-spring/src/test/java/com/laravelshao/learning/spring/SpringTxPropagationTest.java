package com.laravelshao.learning.spring;

import com.laravelshao.learning.spring.tx.service.TxPropagationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;

/**
 * Spring 事务传播行为测试
 *
 * @author shaoqinghua
 * @date 2018/5/23
 * @see <a href="https://segmentfault.com/a/1190000013341344#articleHeader17">Spring事务传播行为详解</a>
 * @see <a href="https://github.com/TmTse/transaction-test">transaction测试demo</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-tx-propagation.xml")
public class SpringTxPropagationTest {

    @Autowired
    private TxPropagationService txPropagationService;

    //===================场景1：外围方法未开启事务，内部方法使用REQUIRED===================

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
     * 外围方法未开启事务，分别调用 {@link Propagation#REQUIRED} 修饰
     * 的内部方法会开启内部方法自己的事务，且开启的事务相互独立，互不影响。子方法A正常执行，子方法B抛出异常。
     * <p>测试结果："张三"插入，"李四"未插入。
     * 外围方法没有事务，插入"张三"、"李四"方法都在自己的事务中独立运行，
     * 所以插入"李四"方法抛出异常只会回滚插入"李四"方法，插入"张三"方法不受影响。
     */
    @Test
    public void notransaction_required_required_exception() {
        txPropagationService.notransaction_required_required_exception();
    }

    //===================场景2：外围方法开启事务，内部方法使用REQUIRED=====================

    /**
     * 外围方法开启事务情况下，{@link Propagation#REQUIRED} 修饰的内部方法会加
     * 入到外围方法的事务中，所有 {@link Propagation#REQUIRED} 修饰的内部方法
     * 和外围方法均属于同一事务，只要一个方法回滚，整个事务均回滚。
     *
     * <p>测试结果："张三"、"李四"均未插入。
     * 外围方法开启事务，内部方法加入外围方法事务，外围方法回滚，内部方法也要回滚。
     */
    @Test
    public void transaction_exception_required_required() {
        txPropagationService.transaction_exception_required_required();
    }

    /**
     * 测试结果："张三"、"李四"均未插入。
     * 外围方法开启事务，内部方法加入外围方法事务，内部方法抛出异常回滚，外围方法感知异常致使整体事务回滚。
     */
    @Test
    public void transaction_required_required_exception() {
        txPropagationService.transaction_required_required_exception();
    }

    /**
     * 测试结果："张三"、"李四"均未插入。
     * 外围方法开启事务，内部方法加入外围方法事务，内部方法抛出异常回滚，
     * 即使方法被catch不被外围方法感知，整个事务依然回滚。
     */
    @Test
    public void transaction_required_required_exception_try() {
        txPropagationService.transaction_required_required_exception_try();
    }

    //=================场景3：外围方法未开启事务，内部方法使用REQUIRED_NEW=================

    /**
     * 外围方法未开启事务情况下 {@link Propagation#REQUIRES_NEW} 修饰的内部方法
     * 会新开启自己的事务，且开启的事务相互独立，互不干扰。
     *
     * <p>测试结果："张三"插入，"李四"插入。
     * 外围方法没有事务，插入"张三"、"李四"方法都在自己的事务中独立
     * 运行,外围方法抛出异常回滚不会影响内部方法。
     */
    @Test
    public void notransaction_exception_requiresNew_requiresNew() {
        txPropagationService.notransaction_exception_requiresNew_requiresNew();
    }

    /**
     * 测试结果："张三"插入，"李四"未插入。
     * 外围方法没有开启事务，插入"张三"方法和插入"李四"方法分别开启自己的事务，
     * 插入"李四"方法抛出异常回滚，其他事务不受影响。
     */
    @Test
    public void notransaction_requiresNew_requiresNew_exception() {
        txPropagationService.notransaction_requiresNew_requiresNew_exception();
    }

    //=============场景4：外围方法开启事务，内部方法使用REQUIRED及REQUIRED_NEW=============

    /**
     * 外围方法开启事务情况下 {@link Propagation#REQUIRES_NEW} 修饰的内部方法依然会单独开启独立事务，
     * 且与外部方法事务也独立，内部方法之间、内部方法和外部方法事务均相互独立，互不干扰。
     *
     * <p>测试结果："张三"未插入，"李四"插入，"王五"插入。
     * 外围方法开启事务，插入"张三"方法和外围方法一个事务，插入"李四"方法、插入"王五"方法
     * 分别在独立的新建事务中，外围方法抛出异常只回滚和外围方法同一事务的方法，故插入"张三"的方法回滚。
     */
    @Test
    public void transaction_exception_required_requiresNew_requiresNew() {
        txPropagationService.transaction_exception_required_requiresNew_requiresNew();
    }

    /**
     * 测试结果："张三"未插入，"李四"插入，"王五"未插入。
     * 外围方法开启事务，插入"张三"方法和外围方法一个事务，插入"李四"方法、插入"王五"方法
     * 分别在独立的新建事务中。插入"王五"方法抛出异常，首先插入"王五"方法的事务被回滚，
     * 异常继续抛出被外围方法感知，外围方法事务亦被回滚，故插入“张三”方法也被回滚。
     */
    @Test
    public void transaction_required_requiresNew_requiresNew_exception() {
        txPropagationService.transaction_required_requiresNew_requiresNew_exception();
    }

    /**
     * 测试结果："张三"插入，"李四"插入，"王五"未插入。
     * 外围方法开启事务，插入"张三"方法和外围方法一个事务，插入"李四"方法、插入"王五"方法
     * 分别在独立的新建事务中。插入"王五"方法抛出异常，首先插入"王五"方法的事务被回滚，
     * 异常被catch不会被外围方法感知，外围方法事务不回滚，故插入"张三"方法插入成功。
     */
    @Test
    public void transaction_required_requiresNew_requiresNew_exception_try() {
        txPropagationService.transaction_required_requiresNew_requiresNew_exception_try();
    }

    //===================场景5：外围方法未开启事务，内部方法使用NESTED=====================

    /**
     * 外围方法未开启事务情况下 {@link Propagation#NESTED} 和 {@link Propagation#REQUIRED} 作用相同，
     * 修饰的内部方法都会新开启自己的事务，且开启的事务相互独立，互不干扰。
     *
     * <p>测试结果："张三"、"李四"均插入。
     * 外围方法未开启事务，插入"张三"、"李四"方法在自己的事务中独立运行，
     * 外围方法异常不影响内部插入"张三"、"李四"方法独立的事务。
     */
    @Test
    public void notransaction_exception_nested_nested() {
        txPropagationService.notransaction_exception_nested_nested();
    }

    /**
     * 测试结果："张三"插入，"李四"未插入。
     * 外围方法没有事务，插入"张三"、"李四"方法都在自己的事务中独立运行，
     * 所以插入"李四"方法抛出异常只会回滚插入"李四"方法，插入"张三"方法不受影响。
     */
    @Test
    public void notransaction_nested_nested_exception() {
        txPropagationService.notransaction_nested_nested_exception();
    }

    //=================场景6：外围方法开启事务NESTED，内部方法使用NESTED===================

    /**
     * 外围方法开启事务的情况下{@link Propagation#NESTED} 修饰的内部方法属于外部事务的子事务，
     * 外围主事务回滚，子事务一定回滚，而内部子事务可以单独回滚而不影响外围主事务和其他子事务。
     *
     * <p>测试结果："张三"、"李四"均未插入。
     * 外围方法开启事务，内部事务为外围事务的子事务，外围方法回滚，内部方法也要回滚。
     */
    @Test
    public void transaction_exception_nested_nested() {
        txPropagationService.transaction_exception_nested_nested();
    }

    /**
     * 测试结果："张三"、"李四"均未插入。
     * 外围方法开启事务，内部事务为外围事务的子事务，内部方法抛出异常回滚，且外围方法感知异常致使整体事务回滚。
     */
    @Test
    public void transaction_nested_nested_exception() {
        txPropagationService.transaction_nested_nested_exception();
    }

    /**
     * 测试结果："张三"插入、"李四"未插入。
     * 外围方法开启事务，内部事务为外围事务的子事务，插入"李四"内部方法抛出异常对导致内部子事务回滚，
     * 另外因为异常被捕获，不会影响外部方法。
     */
    @Test
    public void transaction_nested_nested_exception_try() {
        txPropagationService.transaction_nested_nested_exception_try();
    }

}
