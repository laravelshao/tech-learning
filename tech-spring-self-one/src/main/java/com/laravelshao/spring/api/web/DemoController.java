package com.laravelshao.spring.api.web;


import com.laravelshao.spring.api.service.DemoService;
import com.laravelshao.spring.my.annotation.Autowried;
import com.laravelshao.spring.my.annotation.Controller;
import com.laravelshao.spring.my.annotation.RequestMapping;
import com.laravelshao.spring.my.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DemoController {

    @Autowried
    private DemoService demoService;

    @RequestMapping("/query")
    public void query(HttpServletRequest req, HttpServletResponse resp,
                      @RequestParam("name") String name) {
        String result = demoService.get(name);
        System.out.println(result);
    }


}
