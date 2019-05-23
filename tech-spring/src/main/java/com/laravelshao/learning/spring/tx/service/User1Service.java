package com.laravelshao.learning.spring.tx.service;

import com.laravelshao.learning.spring.tx.pojo.User1;

public interface User1Service {

    void addRequired(User1 user);

    void addRequiresNew(User1 user);

    void addNested(User1 user);
}
