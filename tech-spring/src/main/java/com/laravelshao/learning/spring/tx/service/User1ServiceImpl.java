package com.laravelshao.learning.spring.tx.service;

import com.laravelshao.learning.spring.tx.mapper.User1Mapper;
import com.laravelshao.learning.spring.tx.pojo.User1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class User1ServiceImpl implements User1Service {

    @Autowired
    private User1Mapper user1Mapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequired(User1 user) {
        user1Mapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNew(User1 user) {
        user1Mapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void addNested(User1 user) {
        user1Mapper.insert(user);
    }

}