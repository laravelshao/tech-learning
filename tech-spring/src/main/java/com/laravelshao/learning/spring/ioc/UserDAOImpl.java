package com.laravelshao.learning.spring.ioc;

/**
 * Created by shaoqinghua on 2017/12/17.
 */
public class UserDAOImpl implements UserDAO {

    @Override
    public void findNameAndPwd() {
        System.out.println("数据层->UserDAOImpl->从数据库查询用户名和密码.....");
    }
}
