package com.laravelshao.learning.rpc.manual.server;

import com.laravelshao.learning.rpc.manual.service.HiService;

/**
 * @author shaoqinghua
 * @date 2018/10/5
 * @description
 */
public class HiServiceImpl implements HiService {

    @Override
    public String sayHi(String msg) {
        return "hello " + msg;
    }
}
