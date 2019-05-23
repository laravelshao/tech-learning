package com.laravelshao.spring.api.service.impl;


import com.laravelshao.spring.api.service.DemoService;
import com.laravelshao.spring.framework.annotation.Service;

@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String get(String name) {
        return "My name is " + name;
    }

    @Override
    public String add(String name, String addr) {
        return "DemoService add, name=" + name + ", addr=" + addr;
    }

}
