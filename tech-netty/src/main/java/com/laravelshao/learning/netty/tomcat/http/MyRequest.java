package com.laravelshao.learning.netty.tomcat.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

/**
 * @author shaoqinghua
 * @date 2018/12/22
 * @description 自定义请求对象
 */
public class MyRequest {

    private ChannelHandlerContext ctx;
    private HttpRequest httpReq;

    public MyRequest(ChannelHandlerContext ctx, HttpRequest httpReq) {
        this.ctx = ctx;
        this.httpReq = httpReq;
    }

    /**
     * 获取请求uri
     *
     * @return
     */
    public String getUri() {
        return httpReq.uri();
    }

    /**
     * 获取http请求方式
     *
     * @return
     */
    public String getMethod() {
        return httpReq.method().name();
    }

    /**
     * 获取请求参数
     *
     * @return
     */
    public Map<String, List<String>> getParameters() {
        QueryStringDecoder decoder = new QueryStringDecoder(httpReq.uri());
        return decoder.parameters();
    }

    /**
     * 获取执行参数
     *
     * @param name
     * @return
     */
    public String getParameter(String name) {
        Map<String, List<String>> params = getParameters();
        List<String> param = params.get(name);
        if (param == null) {
            return null;
        } else {
            return param.get(0);
        }
    }

}
