package com.laravelshao.learning.spring.tx.service;

import com.laravelshao.learning.spring.tx.mapper.PersonMapper;
import com.laravelshao.learning.spring.tx.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by shaoqinghua on 2018/9/6.
 */
//@Transactional
@Service
public class PersonService {

    @Autowired
    private PersonMapper personMapper;

    /**
     * 新增
     *
     * @param person
     * @return
     */
    public int insert(Person person) {
        personMapper.insert(new Person("异常前"));
//        int i = 1 / 0;
        personMapper.insert(new Person("异常后"));
        return 0;
        //return personMapper.insert(person);
        //return personMapper.insert(person);
    }

    /**
     * 查询列表
     *
     * @return
     */
    public List<Person> findAll() {
        return personMapper.findAll();
    }

}
