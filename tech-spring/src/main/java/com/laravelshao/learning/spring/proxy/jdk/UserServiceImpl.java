package com.laravelshao.learning.spring.proxy.jdk;

/**
 * Created by shaoqinghua on 2017/12/18.
 */
public class UserServiceImpl implements UserService {

    @Override
    public void save() {
        System.out.println("保存用户信息成功");
    }

    @Override
    public int select() {
        System.out.println("查询用户信息成功");
        return 10;
    }
}
