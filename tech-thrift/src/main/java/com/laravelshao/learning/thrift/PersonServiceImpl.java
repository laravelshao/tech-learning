package com.laravelshao.learning.thrift;

import com.laravelshao.learning.thrift.generated.DataException;
import com.laravelshao.learning.thrift.generated.Person;
import com.laravelshao.learning.thrift.generated.PersonService;
import org.apache.thrift.TException;

/**
 * Person 服务实现
 *
 * @author qinghua.shao
 * @date 2019/10/26
 * @since 1.0.0
 */
public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public Person getPersonByUsername(String username) throws DataException, TException {

        System.out.println("getPersonByUsername() got client param: " + username);

        Person person = new Person();
        person.setUsername(username);
        person.setAge(20);
        person.setMarried(false);

        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {

        System.out.println("savePerson() got client param: " + person.toString());

        System.out.println(person.getUsername());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
    }
}
