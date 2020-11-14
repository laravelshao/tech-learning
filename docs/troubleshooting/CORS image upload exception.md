# 异常问题

项目中存在跨域图片上传请求，提示下列错误

> Access to XMLHttpRequest at 'http://localhost:8080/common/uploadPic' from origin 'http://localhost:8000'has been blocked by CORS policy: Response to preflight request doesn't pass access control check: No 'Access-Control-Allow-Origin' header is present on the requested resource. If an opaque response serves your needs, set the request's mode to 'no-cors' to fetch the resource with CORS disabled.

最初的跨域支持使用` spring`提供的注解 `@CrossOrigin` 来处理，针对项目中普通接口 `@CrossOrigin`注解的默认策略已经可以满足，唯独对于图片上传请求无法支持。

查看 `Spring CORS` 支持文档，`@CrossOrigin` 注解默认策略支持所有的`origins` 以及 `GET` 、`HEAD` 、`POST ` 三种请求方法。

> In addition to fine-grained, annotation-based configuration you’ll probably want to define some global CORS configuration as well. This is similar to using filters but can be declared withing Spring MVC and combined with fine-grained `@CrossOrigin` configuration. By default all origins and `GET`, `HEAD` and `POST` methods are allowed.



# CORS机制

## CORS请求

浏览器将 `CORS` 请求分成两类：简单请求（ `simple request` ）和非简单请求（ `not-so-simple request` ）。

只要同时满足以下两大条件，就属于简单请求。（引用自[跨域资源共享 CORS 详解](http://www.ruanyifeng.com/blog/2016/04/cors.html)）

> （1) 请求方法是以下三种方法之一：
>
> - HEAD
> - GET
> - POST
>
> （2）HTTP的头信息不超出以下几种字段：
>
> - Accept
> - Accept-Language
> - Content-Language
> - Last-Event-ID
> - Content-Type：只限于三个值`application/x-www-form-urlencoded`、`multipart/form-data`、`text/plain`



## 预检请求

因为前端H5页面请求时添加了全局配置 `Content-Type` 为 `application/json` ，不符合上述简单请求条件，属于非简单请求，因此会添加预检 `OPTIONS` 请求

预检 `OPTIONS` 请求 `header` 信息

>  Request Method: OPTIONS
>  Access-Control-Request-Headers: x-requested-with
>  Access-Control-Request-Method: POST
>  Origin: http://test.demo.com:59004
>  User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36

## 预检请求响应

浏览器收到预检请求响应后，会检查 `Access-Control-Allow-Origin` 、`Access-Control-Allow-Methods` 、`Access-Control-Allow-Headers` 确认允许跨域请求。

> Access-Control-Allow-Credentials:  true
> Access-Control-Allow-Headers:  x-requested-with
> Access-Control-Allow-Methods:  GET,POST,OPTIONS
> Access-Control-Allow-Origin:  http://test.demo.com:59004
> Connection:  keep-alive
> Content-Length:  0
> Date:  Thu, 01 Nov 2018 07:06:30 GMT
> Server:  nginx
> Vary:  Origin



# 异常解决

因此决定在 `@CrossOrigin` 注解中配置支持 `OPTIONS` 请求，再次本地测试跨域图片上传，可以成功上传。

```java
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
```

但是部署测试环境之后，发现了另一个潜在的问题，登录环境打开了登录拦截，会使用 `cookie` 中登录信息检验登录，当登录信息在有效期内，所有接口都能正常跨域请求使用。但登录信息失效后，导致所有的接口包括图片上传都提示开头的跨域问题，怀疑是 `@CrossOrigin` 注解的执行顺序在登录拦截器之后，当请求被拦截器拦截，因为还未执行 `@CrossOrigin` 跨域支持逻辑，导致响应就存在异常，也不能正确跳转登录页面。因此尝试寻找另外的跨域处理方式，在 `Spring CORS` 支持文档中提供了Spring Boot应用基于 `Filter` 方式的全局跨域支持。

```java
@Configuration
public class CorsConfig {

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod(HttpMethod.GET);
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.OPTIONS);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}
```

修改为 `Filter` 全局跨域支持后，再次测试包括登录有效期内及登录失效情况下图片上传和其它常规接口，都能正常处理，登录失效时也能正常返回跳转登录的业务状态码。

# 参考资料

[CORS Support in Spring Framework](https://spring.io/blog/2015/06/08/cors-support-in-spring-framework)

[http://www.ruanyifeng.com/blog/2016/04/cors.html](http://www.ruanyifeng.com/blog/2016/04/cors.html)

