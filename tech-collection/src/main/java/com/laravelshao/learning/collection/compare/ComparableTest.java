package com.laravelshao.learning.collection.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Comparable 测试
 *
 * @author qinghua.shao
 * @date 2023/7/30
 * @since 1.0.0
 */
public class ComparableTest {

    public static void main(String[] args) {

        Student s1 = new Student(1, "张三", 20);
        Student s2 = new Student(2, "李四", 18);
        Student s3 = new Student(3, "王五", 22);

        List<Student> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);

        // 年龄升序排列
        Collections.sort(list);

        for (Student s : list) {
            System.out.println(s.getName() + " " + s.getAge());
        }
    }
}
