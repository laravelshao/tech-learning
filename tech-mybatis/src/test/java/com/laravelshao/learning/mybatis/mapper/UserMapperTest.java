package com.laravelshao.learning.mybatis.mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.laravelshao.learning.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by shaoqinghua on 2018/7/21.
 */
public class UserMapperTest {

    private UserMapper userMapper;

    SqlSession sqlSession = null;
    SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void setUp() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //openSession(boolean autoCommit) 设置事务是否自动提交
        sqlSession = sqlSessionFactory.openSession(true);
        // 获取动态代理实现类
        this.userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void testQueryUserById() {
        User user = userMapper.queryUserById(1L);
        System.out.println(user);
    }

    @Test
    public void testQueryAll() {
        List<User> users = userMapper.queryAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试PageHelper分页查询
     */
    @Test
    public void testQueryAllByPageHelper() {
        PageHelper.startPage(2, 3);
        List<User> users = userMapper.queryAll();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        System.out.println("数据总数： " + pageInfo.getTotal());
        System.out.println("总页数： " + pageInfo.getPages());
        System.out.println("最后一页： " + pageInfo.getLastPage());

        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setAge(20);
        user.setBirthday(new Date());
        user.setName("test_name_22");
        user.setPassword("123456");
        user.setSex(1);
        user.setUserName("test_userName_22");
        userMapper.saveUser(user);
    }

    @Test
    public void testUpdateUser() {
        User user = userMapper.queryUserById(2L);
        user.setAge(30);
        userMapper.updateUser(user);
    }

    @Test
    public void testDeleteUserById() {
        userMapper.deleteUserById(8L);
    }

    /**
     * 测试一级缓存
     */
    @Test
    public void testQueryCache1() {
        // 第一次查询
        User user = userMapper.queryUserById(1L);
        System.out.println(user);

        // 清空缓存
        // this.sqlSession.clearCache();

        //user.setAge(20);
        //userMapper.updateUser(user);

        // 第二次查询
        //user = userMapper.queryUserById(1L);
        //System.out.println(user);
        
        // 第三次查询
        user = userMapper.queryUserById(2L);
        System.out.println(user);
    }

    /**
     * 测试二级缓存
     */
    @Test
    public void testQueryCache2() {
        // 第一次查询
        User user = this.userMapper.queryUserById(1L);
        System.out.println(user);

        // 关闭session
        this.sqlSession.close();

        // 再次打开session
        this.sqlSession = this.sqlSessionFactory.openSession(true);
        this.userMapper = this.sqlSession.getMapper(UserMapper.class);

        //user.setAge(111);
        //userMapper.updateUser(user);

        // 第二次查询
        user = this.userMapper.queryUserById(1L);
        System.out.println(user);

    }

    @Test
    public void testQueryUserListLikeName() {
        List<User> users = userMapper.queryUserListLikeName("张");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUserListLikeNameORAge() {
        List<User> users = userMapper.queryUserListLikeNameORAge("张", 30);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testQueryAllUserListLikeNameORAge() {
        List<User> users = userMapper.queryAllUserListLikeNameORAge(null, 30);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testQueryAllUserListByIds() {
        List<User> users = userMapper.queryAllUserListByIds(new Long[]{1L, 2L, 3L});
        for (User user : users) {
            System.out.println(user);
        }
    }

}