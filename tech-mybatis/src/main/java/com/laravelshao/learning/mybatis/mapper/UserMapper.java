package com.laravelshao.learning.mybatis.mapper;

import com.laravelshao.learning.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by shaoqinghua on 2018/7/21.
 */
public interface UserMapper {

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    User queryUserById(Long id);

    /**
     * 查询所有用户数据
     *
     * @return
     */
    List<User> queryAll();

    /**
     * 新增用户信息
     *
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户信息
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id删除用户信息
     *
     * @param id
     */
    void deleteUserById(Long id);

    /**
     * 测试动态SQL 查询男性用户，如果输入了姓名进行模糊查找
     *
     * @param name
     * @return
     */
    List<User> queryUserListLikeName(@Param("name") String name);

    /**
     * 测试动态SQL 查询男性用户，如果输入了姓名则按照姓名模糊查找，否则如果输入了年龄则按照年龄查找
     *
     * @param name
     * @return
     */
    List<User> queryUserListLikeNameORAge(@Param("name") String name, @Param("age") Integer age);

    /**
     * 测试动态SQL 查询所有用户，如果输入了姓名，进行模糊查找，如果输入了年龄则按照年龄查找
     *
     * @param name
     * @param age
     * @return
     */
    List<User> queryAllUserListLikeNameORAge(@Param("name") String name, @Param("age") Integer age);

    /**
     * 按照多个ID查询用户信息
     *
     * @param ids
     * @return
     */
    List<User> queryAllUserListByIds(@Param("ids") Long[] ids);

}
