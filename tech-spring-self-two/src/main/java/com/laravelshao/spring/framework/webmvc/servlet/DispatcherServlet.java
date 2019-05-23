package com.laravelshao.spring.framework.webmvc.servlet;

import com.laravelshao.spring.framework.annotation.Controller;
import com.laravelshao.spring.framework.annotation.RequestMapping;
import com.laravelshao.spring.framework.annotation.RequestParam;
import com.laravelshao.spring.framework.aop.AopProxyUtils;
import com.laravelshao.spring.framework.context.ApplicationContext;
import com.laravelshao.spring.framework.webmvc.HandlerAdapter;
import com.laravelshao.spring.framework.webmvc.HandlerMapping;
import com.laravelshao.spring.framework.webmvc.ModelAndView;
import com.laravelshao.spring.framework.webmvc.ViewResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shaoqinghua on 2018/8/11.
 */
public class DispatcherServlet extends HttpServlet {

    private List<HandlerMapping> handlerMappings = new ArrayList<>();

    private Map<HandlerMapping, HandlerAdapter> handlerAdapters = new HashMap<>();

    private List<ViewResolver> viewResolvers = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationContext context = new ApplicationContext(config.getInitParameter("contextConfigLocation"));

        initStrategies(context);
    }

    /**
     * 初始化策略
     *
     * @param context
     */
    private void initStrategies(ApplicationContext context) {
        /**
         * springmvc九大组件
         */
        //initMultipartResolver(context);
        //initLocaleResolver(context);
        //initThemeResolver(context);
        /**
         * HandlerMapping 用来保存controller中配置的RequestMapping与Method对应关系
         */
        initHandlerMappings(context); // 使用HandlerMapping将请求映射到处理器
        /**
         * HandlerAdapter 用来动态匹配Method参数 包括类转换 动态赋值
         */
        initHandlerAdapters(context); // 通过HandlerAdapter进行多类型参数动态匹配
        //initHandlerExceptionResolvers(context);
        //initRequestToViewNameTranslator(context);
        /**
         * ViewResolver 实现动态模板的解析
         */
        initViewResolvers(context); // 通过ViewResolver解析逻辑视图到具体视图实现
        //initFlashMapManager(context);
    }

    //private void initMultipartResolver(ApplicationContext context) { }
    //private void initLocaleResolver(ApplicationContext context) { }
    //private void initThemeResolver(ApplicationContext context) { }
    //private void initHandlerExceptionResolvers(ApplicationContext context) { }
    //private void initRequestToViewNameTranslator(ApplicationContext context) { }
    //private void initFlashMapManager(ApplicationContext context) { }

    /**
     * 将Controller中配置的RequestMapping与Method一一对应
     *
     * @param context
     */
    private void initHandlerMappings(ApplicationContext context) {

        // 首先从容器获取所有实例
        String[] beanNames = context.getBeanDefinitionNames();
        try {
            for (String beanName : beanNames) {

                Object proxy = context.getBean(beanName);
                Object controller = AopProxyUtils.getTargetObject(proxy);
                Class<?> clazz = controller.getClass();

                // 判断是否加有Controller注解
                if (!clazz.isAnnotationPresent(Controller.class)) {
                    continue;
                }

                String baseUrl = "";
                if (clazz.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
                    baseUrl = requestMapping.value();
                }

                // 扫描所有的public方法
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    // 判断方法是否加有RequestMapping注解
                    if (!method.isAnnotationPresent(RequestMapping.class)) {
                        continue;
                    }

                    RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                    String regex = ("/" + baseUrl + requestMapping.value().replaceAll("\\*", ".*")).replaceAll("/+", "/");
                    Pattern pattern = Pattern.compile(regex);
                    this.handlerMappings.add(new HandlerMapping(pattern, controller, method));
                    System.out.println("Mapping: " + regex + " , " + method);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initHandlerAdapters(ApplicationContext context) {
        /**
         * 在初始化阶段可以将这些参数的名字或者类型按一定的顺序保存下来
         * 因为后面用反射调用的时候，传的形参是一个数组
         * 可以通过记录这些参数的位置index，挨个从数组中填值，这样的话，就和参数的顺序无关了
         */
        for (HandlerMapping handlerMapping : this.handlerMappings) {
            // 每一个方法都有一个参数列表，那么这里保存的是形参列表
            Map<String, Integer> paramMapping = new HashMap<>();

            // 处理命名参数
            Annotation[][] pa = handlerMapping.getMethod().getParameterAnnotations();
            for (int i = 0; i < pa.length; i++) {
                for (Annotation a : pa[i]) {
                    if (a instanceof RequestParam) {
                        String paramName = ((RequestParam) a).value();
                        if (!"".equals(paramName.trim())) {
                            paramMapping.put(paramName, i);
                        }
                    }
                }
            }

            // 处理非命名参数 request response
            Class<?>[] parameterTypes = handlerMapping.getMethod().getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                Class<?> parameterType = parameterTypes[i];
                if (parameterType == HttpServletRequest.class || parameterType == HttpServletResponse.class) {
                    paramMapping.put(parameterType.getName(), i);
                }
            }

            this.handlerAdapters.put(handlerMapping, new HandlerAdapter(paramMapping));
        }
    }

    private void initViewResolvers(ApplicationContext context) {
        /**
         * 在页面敲一个 http://localhost:9090/first.html
         * 解决页面名字和模板文件关联的问题
         */
        String templateRoot = context.getConfig().getProperty("templateRoot");
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();

        File templateRootDir = new File(templateRootPath);

        for (File template : templateRootDir.listFiles()) {
            this.viewResolvers.add(new ViewResolver(template.getName(), template));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("<font size='25' color='blue'>500 Exception</font><br/>Details:<br/>" +
                    Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]", "").replaceAll("\\s", "\r\n"));
            e.printStackTrace();
        }
    }

    /**
     * 执行请求转发
     *
     * @param req
     * @param resp
     */
    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HandlerMapping handler = getHandler(req);
        if (handler == null) {
            resp.getWriter().write("<font size='25' color='red'>404 Not Found</font><br/>");
            return;
        }
        HandlerAdapter ha = getHandlerAdapter(handler);
        ModelAndView mv = ha.handle(req, resp, handler);
        processDispatchResult(resp, mv);


    }

    private HandlerMapping getHandler(HttpServletRequest req) {

        if (this.handlerMappings.isEmpty()) {
            return null;
        }

        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");

        for (HandlerMapping handler : this.handlerMappings) {
            Matcher matcher = handler.getPattern().matcher(url);
            if (!matcher.matches()) {
                continue;
            }
            return handler;
        }

        return null;
    }

    private HandlerAdapter getHandlerAdapter(HandlerMapping handler) {
        if (this.handlerAdapters.isEmpty()) {
            return null;
        }
        return this.handlerAdapters.get(handler);
    }

    private void processDispatchResult(HttpServletResponse resp, ModelAndView mv) throws Exception {
        //调用viewResolver的resolveView方法
        if (null == mv) {
            return;
        }

        if (this.viewResolvers.isEmpty()) {
            return;
        }

        for (ViewResolver viewResolver : this.viewResolvers) {
            if (!mv.getViewName().equals(viewResolver.getViewName())) {
                continue;
            }

            String out = viewResolver.viewResolver(mv);

            if (out != null) {
                resp.getWriter().write(out);
                break;
            }
        }
    }


}
