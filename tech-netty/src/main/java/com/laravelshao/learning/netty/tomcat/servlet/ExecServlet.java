package com.laravelshao.learning.netty.tomcat.servlet;

import com.laravelshao.learning.netty.tomcat.http.MyRequest;
import com.laravelshao.learning.netty.tomcat.http.MyResponse;
import com.laravelshao.learning.netty.tomcat.http.MyServlet;

/**
 * @author shaoqinghua
 * @date 2018/12/22
 * @description
 */
public class ExecServlet extends MyServlet {

    @Override
    public void doGet(MyRequest request, MyResponse response) {
        doPost(request, response);
    }

    @Override
    public void doPost(MyRequest request, MyResponse response) {
        String name = request.getParameter("name");
        response.write(name);
    }
}
