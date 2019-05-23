package com.laravelshao.learning.spring.tx.service;

import com.laravelshao.learning.spring.tx.pojo.User1;
import com.laravelshao.learning.spring.tx.pojo.User2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shaoqinghua
 * @date 2019/5/23
 * @description 事务测试服务
 */
@Service
public class TxPropagationService {


    @Autowired
    private User1Service user1Service;

    @Autowired
    private User2Service user2Service;

    public void notransaction_exception_required_required() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addRequired(user2);

        throw new RuntimeException();
    }

    public void notransaction_required_required_exception() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addRequiredException(user2);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_required_required() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addRequired(user2);

        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_required_exception() {
        User1 user1 = new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("李四");
        user2Service.addRequiredException(user2);
    }

}
