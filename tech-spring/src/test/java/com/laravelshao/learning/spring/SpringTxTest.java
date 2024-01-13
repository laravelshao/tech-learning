package com.laravelshao.learning.spring;

import com.laravelshao.learning.spring.tx.pojo.Person;
import com.laravelshao.learning.spring.tx.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author qinghua.shao
 * @date 2018/9/6
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-tx.xml")
public class SpringTxTest {

    @Autowired
    private PersonService personService;

    @Test
    public void insertPersonTest() {
        Person person = new Person();
        person.setName("嘻嘻嘻");
        int res = personService.insert(person);
        System.out.println("insert " + res);
    }

    @Test
    public void findAllTest() {
        List<Person> list = personService.findAll();
        System.out.println(list);
    }

}
