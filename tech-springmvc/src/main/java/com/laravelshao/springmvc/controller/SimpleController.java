package com.laravelshao.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by shaoqinghua on 2018/8/12.
 */
// 标记为SpringMVC的handler对象
@Controller
public class SimpleController {

    // 定义请求的url路径
    @RequestMapping("/show")
    public ModelAndView show() {
        //创建模型视图对象
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("msg", "这是我的第一个注解springmvc应用程序");
        return mv;
    }
}
