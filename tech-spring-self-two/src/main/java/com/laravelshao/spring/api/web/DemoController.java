package com.laravelshao.spring.api.web;


import com.laravelshao.spring.api.service.DemoService;
import com.laravelshao.spring.framework.annotation.Autowired;
import com.laravelshao.spring.framework.annotation.Controller;
import com.laravelshao.spring.framework.annotation.RequestMapping;
import com.laravelshao.spring.framework.annotation.RequestParam;
import com.laravelshao.spring.framework.webmvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/query")
    public ModelAndView query(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam("name") String name) {
        String result = demoService.get(name);
        return out(response, result);
    }

    @RequestMapping("/add*")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam("name") String name, @RequestParam("addr") String addr) {
        String result = demoService.add(name, addr);
        return out(response, result);
    }


    @RequestMapping("/first.html")
    public ModelAndView first(@RequestParam("name") String name) {
        Map<String, Object> model = new HashMap<>();
        model.put("name", name);
        model.put("token", "123456");
        return new ModelAndView("first.html", model);
    }

    private ModelAndView out(HttpServletResponse resp, String str) {
        try {
            resp.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
