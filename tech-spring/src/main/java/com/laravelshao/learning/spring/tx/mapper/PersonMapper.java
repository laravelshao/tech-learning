package com.laravelshao.learning.spring.tx.mapper;

import com.laravelshao.learning.spring.tx.pojo.Person;

import java.util.List;

/**
 * Created by shaoqinghua on 2018/9/6.
 */
public interface PersonMapper {

    /**
     * 添加
     *
     * @param person
     * @return
     */
    int insert(Person person);

    /**
     * 查询列表
     *
     * @return
     */
    List<Person> findAll();



}
