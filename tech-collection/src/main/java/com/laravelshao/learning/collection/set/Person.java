package com.laravelshao.learning.collection.set;

/**
 * Created by LaravelShao on 2017/12/13.
 */
public class Person implements Comparable {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        return name != null ? name.equals(person.name) : person.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    @Override
    public int compareTo(Object o) {
        // 由于传递进来的对象被提升成Object类型，因此需要向下转型
        if (!(o instanceof Person)) {
            // 如果判断成立，说明传递进来的不是Person
            throw new ClassCastException("请传递一个Person进来，否则不允许比较");
        }
        // 向下转型
        Person p = (Person) o;
        // 因为age都是int值，如果相等，它们的差值恰好是零
        int temp = this.age - p.age;
        return temp == 0 ? this.name.compareTo(p.name) : temp;
    }
}
