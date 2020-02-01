package com.laravelshao.learning.concurrent.atomic;


import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicIntegerFieldUpdater注意事项
 *
 * <ol>
 * <li>更新器更新的必须是int类型变量，不能是包装类型。</li>
 * <li>更新器更新的必须是volatile类型变量，确保线程之间共享变量时的立即可见性。</li>
 * <li>变量不能是static的，必须是实例变量。因为{@link Unsafe#objectFieldOffset} 方法不支持
 * 静态变量(CAS操作本质上是通过对象实例的偏移量来直接进行赋值)。</li>
 * <li>更新器只能修改它可见范围内的变量，因为更新器是通过反射来得到这个变量，如果变量不可见就会报错。</li>
 * </ol>
 *
 * @author qinghua.shao
 * @date 2019/12/21
 * @since 1.0.0
 */
public class AtomicIntegerFieldUpdaterTest {

    public static void main(String[] args) {

        //normalUpdaterTest();
        AtomicUpdaterTest();
    }

    /**
     * 常规赋值更新(存在非原子更新问题)
     */
    public static void normalUpdaterTest() {
        Person person = new Person();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(20L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(person.age++);
            });

            thread.start();
        }
    }

    /**
     * 原子更新(需要满足字段volatile修饰)
     */
    public static void AtomicUpdaterTest() {
        Person person2 = new Person();
        AtomicIntegerFieldUpdater<Person> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Person.class, "age");

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(20L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(atomicIntegerFieldUpdater.getAndIncrement(person2));
            });

            thread.start();
        }
    }

    static public class Person {

        // 必须是volatile、int基本类型变量
        volatile int age = 1;
    }
}


