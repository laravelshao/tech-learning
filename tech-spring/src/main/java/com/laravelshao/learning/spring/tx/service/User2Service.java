package com.laravelshao.learning.spring.tx.service;

import com.laravelshao.learning.spring.tx.pojo.User2;

public interface User2Service {

    void addRequired(User2 user);

    void addRequiredException(User2 user);

}
