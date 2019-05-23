package com.laravelshao.learning.spring.tx.service;

import com.laravelshao.learning.spring.tx.pojo.User2;

public interface User2Service {

    void addRequired(User2 user);

    void addRequiredException(User2 user);

    void addRequiresNew(User2 user);

    void addRequiresNewException(User2 user);

    void addNested(User2 user);

    void addNestedException(User2 user);

}
