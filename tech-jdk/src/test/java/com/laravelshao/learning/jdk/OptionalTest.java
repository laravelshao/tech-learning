package com.laravelshao.learning.jdk;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author qinghua.shao
 * @date 2020/4/22
 * @since 1.0.0
 */
public class OptionalTest {

    //@Test(expected = NoSuchElementException.class)
    @Test
    public void emptyOptional_getException() {
        Optional<Integer> emptyOpt = Optional.empty();
        emptyOpt.get();
    }

    /**
     * of => 传递null将会抛NPE，明确不为null时才使用of()
     */
    //@Test(expected = NullPointerException.class)
    @Test
    public void ofOptional_thenNPE() {
        Optional<Integer> opt = Optional.of(null);
    }

    /**
     * ofNullable => 传递的值可能为null，则可使用ofNullable
     */
    @Test
    public void ofNullableOptional() {
        Optional<Integer> opt = Optional.ofNullable(null);
    }

    @Test
    public void whenCreateOfNullableOptional_thenOk() {
        Optional<String> opt = Optional.ofNullable("John");
        assertEquals("John", opt.get());
    }

    /**
     * 检查是否有值
     */
    @Test
    public void whenCheckIfPresent_thenOk() {
        Optional<Integer> opt = Optional.ofNullable(100);
        assertTrue(opt.isPresent());
        System.out.println(opt.get());
    }

    /**
     * 空值则返回默认值
     * orElse 和 orElseGet，相比orElse，如果不为空，orElseGet不会执行
     */
    @Test
    public void whenEmptyValue_thenReturnDefault() {
        Integer i1 = null;
        Integer res1 = Optional.ofNullable(i1).orElse(2000);
        System.out.println(res1);

        Integer res2 = Optional.ofNullable(100).orElse(2000);
        System.out.println(res2);
        System.out.println("=======================");

        // orElseGet => 接收Supplier对象
        Integer res3 = Optional.ofNullable(i1).orElseGet(() -> 100);
        System.out.println(res3);
        System.out.println("=======================");

        User user1 = null;
        System.out.println("Using orElse");
        Optional.ofNullable(user1).orElse(new User());
        System.out.println("Using orElseGet");
        Optional.ofNullable(user1).orElseGet(() -> new User());
        System.out.println("=======================");

        /**
         * 在不为null值时，orElse仍然创建对象，orElseGet则不创建对象
         */
        User user2 = new User("haha", "haha@123");
        System.out.println("Using orElse");
        Optional.ofNullable(user2).orElse(new User());
        System.out.println("Using orElseGet");
        Optional.ofNullable(user2).orElseGet(() -> new User());
    }

    /**
     * map
     */
    @Test
    public void whenMap_thenOk() {
        User user = new User("haha", "anna@gmail.com");
        String email = Optional.ofNullable(user)
                .map(u -> u.getEmail()).orElse("default@gmail.com");
        System.out.println(email);
        assertEquals(email, user.getEmail());
    }

    /**
     * flatMap
     */
    @Test
    public void whenFlatMap_thenOk() {
        User user = new User("1234", "anna@gmail.com");
        user.setPosition("Developer");
        String position = Optional.ofNullable(user)
                .flatMap(u -> u.getPosition()).orElse("default");
        System.out.println(position);
        assertEquals(position, user.getPosition().get());
    }

    /**
     * filter
     */
    @Test
    public void whenFilter_thenOk() {
        User user = new User("1234", "anna@gmail.com");
        Optional<User> result = Optional.ofNullable(user)
                .filter(u -> u.getEmail() != null && u.getEmail().contains("@"));
        assertTrue(result.isPresent());
    }

    public class User {
        private String name;
        private String email;
        private String position;

        public Optional<String> getPosition() {
            return Optional.ofNullable(position);
        }

        public User() {
            System.out.println("create user");
        }

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }
}
