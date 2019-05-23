
package com.laravelshao.learning.spring.tx.service;

import com.laravelshao.learning.spring.tx.mapper.User2Mapper;
import com.laravelshao.learning.spring.tx.pojo.User2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class User2ServiceImpl implements User2Service {

    @Autowired
    private User2Mapper user2Mapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequired(User2 user) {
        user2Mapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequiredException(User2 user) {
        user2Mapper.insert(user);
        throw new RuntimeException();
    }

}