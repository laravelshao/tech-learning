package com.laravelshao.spring.api.service.impl;


import com.laravelshao.spring.api.service.DemoService;
import com.laravelshao.spring.my.annotation.Service;

@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String get(String name) {
        return "My name is " + name;
    }

}
