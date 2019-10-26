package com.laravelshao.learning.thrift;

import com.laravelshao.learning.thrift.generated.Person;
import com.laravelshao.learning.thrift.generated.PersonService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * Thrift 客户端
 *
 * @author qinghua.shao
 * @date 2019/10/26
 * @since 1.0.0
 */
public class ThriftClient {

    public static void main(String[] args) {
        TTransport transport = new TFramedTransport(new TSocket("localhost", 8080), 600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);

        try {
            transport.open();

            Person person = client.getPersonByUsername("cl");
            System.out.println(person.getUsername());
            System.out.println(person.getAge());
            System.out.println(person.isMarried());

            System.out.println("--------------------");

            Person person2 = new Person();
            person2.setUsername("haha");
            person2.setAge(25);
            person2.setMarried(true);

            client.savePerson(person2);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
        }
    }
}
