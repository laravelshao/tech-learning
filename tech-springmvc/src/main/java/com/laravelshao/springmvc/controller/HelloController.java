package com.laravelshao.springmvc.controller;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        //创建模型视图对象
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("msg", "这是我的第一个springmvc应用程序");
        return mv;
    }

}
