package com.laravelshao.springmvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.laravelshao.springmvc.pojo.User;
import com.laravelshao.springmvc.pojo.UserForm;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class DemoController {

    @RequestMapping("/test/*/show2")
    public ModelAndView show2() {
        // 创建模型视图对象
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("msg", "测试Ant(*)风格映射");
        return mv;
    }

    @RequestMapping("/test/**/show3")
    public ModelAndView show3() {
        // 创建模型视图对象
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("msg", "测试Ant(**)风格映射");
        return mv;
    }

    @RequestMapping("/user/{id}/{name}")
    public ModelAndView show4(@PathVariable("id") Long id, @PathVariable("name") String name) {
        // 创建模型视图对象
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("msg", "占位符映射 id:" + id + " name:" + name);
        return mv;
    }

    @RequestMapping(value = "show5", method = RequestMethod.POST)
    public ModelAndView show5(@PathVariable("id") Long id, @PathVariable("name") String name) {
        // 创建模型视图对象
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("msg", "看到这个页面说明你发起的是post请求");
        return mv;
    }

    @RequestMapping(value = "query/item", params = "itemId")
    public ModelAndView show6(@RequestParam("itemId") Long itemId) {
        // 创建模型视图对象
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("msg", "查询商品接口，itemId：" + itemId);
        return mv;
    }


    @RequestMapping(value = "show7")
    public ModelAndView show7(@RequestParam(value = "id", required = true) Long id) {
        // 创建模型视图对象
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("msg", "show7演示id：" + id);
        return mv;
    }


    @RequestMapping(value = "show8")
    public ModelAndView show8(User user) {
        // 创建模型视图对象
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("msg", "user：" + user);
        return mv;
    }

    @RequestMapping("demo/show9")
    // 无需跳转页面，直接返回200状态
    @ResponseStatus(value = HttpStatus.OK)
    public void show9(@RequestParam("name") String name, @RequestParam("age") Integer age,
                      @RequestParam("income") Double income, @RequestParam("isMarried") Boolean isMarried,
                      @RequestParam("interests") String[] interests) {
        System.out.println("简单数据类型绑定=========");
        System.out.println("名字:" + name);
        System.out.println("年龄:" + age);
        System.out.println("收入:" + income);
        System.out.println("已结婚:" + isMarried);
        System.out.println("兴趣:");
        for (String interest : interests) {
            System.out.println(interest);
        }
        System.out.println("====================");
    }


    @RequestMapping("demo/show10")
    // 无需跳转页面，直接返回200状态
    @ResponseStatus(value = HttpStatus.OK)
    public void show10(UserForm userForm) {
        System.out.println(userForm);
    }


    @RequestMapping("show11")
    public ModelAndView show11() {
        ModelAndView mv = new ModelAndView("users");
        List<User> users = new ArrayList<User>();

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setAge(20 + i);
            user.setCreated(new Date());
            user.setId(Long.valueOf(i));
            user.setName("name_" + i);
            user.setPassword("123456");
            user.setSex(i / 2);
            user.setUpdated(user.getCreated());
            user.setuserName("userName_" + i);
            users.add(user);
        }
        mv.addObject("users", users);
        return mv;
    }


    @RequestMapping("user/list")
    @ResponseBody
    public List<User> queryUserList() {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setAge(20 + i);
            user.setCreated(new Date());
            user.setId(Long.valueOf(i));
            user.setName("name_" + i);
            user.setPassword("123456");
            user.setSex(i / 2);
            user.setUpdated(user.getCreated());
            user.setuserName("userName_" + i);
            users.add(user);
        }
        return users;
    }


    /**
     * 接收json数据，将json数据反序列化为user对象
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "demo/show12")
    public ModelAndView show12(@RequestBody User user) {
        // 创建模型视图对象
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("msg", "user：" + user);
        return mv;
    }

    /**
     * 接收json数据
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "demo/show13")
    public ModelAndView show13(@RequestBody String json) {
        // 创建模型视图对象
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("msg", "json：" + json);
        return mv;
    }
}
