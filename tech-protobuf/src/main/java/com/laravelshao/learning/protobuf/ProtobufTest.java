package com.laravelshao.learning.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author qinghua.shao
 * @date 2019/10/26
 * @since 1.0.0
 */
public class ProtobufTest {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        UniversityStudent.Student student = UniversityStudent.Student.newBuilder()
                .setName("haha").setAge(25).setAddress("上海").build();

        byte[] student2ByteArray = student.toByteArray();

        UniversityStudent.Student afterStudent = UniversityStudent.Student.parseFrom(student2ByteArray);

        System.out.println(afterStudent.getName());
        System.out.println(afterStudent.getAge());
        System.out.println(afterStudent.getAddress());
    }
}
