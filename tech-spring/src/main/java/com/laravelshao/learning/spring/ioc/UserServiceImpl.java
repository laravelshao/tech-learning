package com.laravelshao.learning.spring.ioc;

/**
 * Created by shaoqinghua on 2017/12/17.
 */
public class UserServiceImpl implements UserService {
    //声明引用
    private UserDAO userDAO;

    //必须提供setter方法
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void login() {
        System.out.println("业务层->UserServiceImpl->login方法.....");

        /**
         * 方式1 通过自己创建UserDAO对象，进行方法调用
         */
        //UserDAO userDAO = new UserDAOImpl();
        //userDAO.findNameAndPwd();

        /**
         * 方式2 IoC控制反转，使用工厂类获取UserDao对象，进行方法调用
         *
         * 步骤
         * 1 创建spring工厂，读取spring核心配置文件
         * 2 通过spring工厂获取bean对象
         * 3 调用方法
         *
         * 获取bean对象方式
         * 1 通过spring工厂bean的id/name获取
         * 2 通过bean类型或bean接口类型获取，一般采用接口类型
         */
        //ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ////UserDAO userDAO = (UserDAO) applicationContext.getBean("userDAO");
        //UserDAO userDAO = applicationContext.getBean(UserDAO.class);
        //userDAO.findNameAndPwd();

        /**
         * 方式3 依赖注入，需提供成员变量和setter方法，在spring核心配置文件中需要配置属性
         */
        userDAO.findNameAndPwd();
    }
}
