package com.laravelshao.spring.my.servlet;

import com.laravelshao.spring.api.web.DemoController;
import com.laravelshao.spring.my.annotation.Autowried;
import com.laravelshao.spring.my.annotation.Controller;
import com.laravelshao.spring.my.annotation.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shaoqinghua on 2018/8/11.
 */
public class DispatcherServlet extends HttpServlet {

    private Properties contextConfig = new Properties();

    private Map<String, Object> beanMap = new ConcurrentHashMap<String, Object>();

    private List<String> classNames = new ArrayList<String>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("调用POST方法");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        /**
         * Spring初始化过程
         */

        // 定位
        doLocateConfig(config.getInitParameter("contextConfigLocation"));

        // 加载
        doScanner(contextConfig.getProperty("scanPackage"));

        // 注册
        doRegistry();

        // 依赖注入
        doAutowired();

        DemoController demoController = (DemoController) beanMap.get("demoController");
        demoController.query(null, null, "laravelshao");
    }

    /**
     * 定位
     *
     * @param
     */
    private void doLocateConfig(String location) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(location.replace("classpath:", ""));
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 加载
     *
     * @param packageName
     */
    private void doScanner(String packageName) {
        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));
        File classDir = new File(url.getFile());
        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(packageName + "." + file.getName());
            } else {
                classNames.add(packageName + "." + file.getName().replace(".class", ""));
            }
        }
    }

    /**
     * 注册
     */
    private void doRegistry() {

        if (classNames.isEmpty()) {
            return;
        }

        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(Controller.class)) {
                    String beanName = lowerFirstCase(clazz.getSimpleName());
                    beanMap.put(beanName, clazz.newInstance());
                } else if (clazz.isAnnotationPresent(Service.class)) {
                    Service service = clazz.getAnnotation(Service.class);
                    /**
                     * 默认用类名首字母注入
                     * 如果自己定义了beanName，那么优先使用自己定义的beanName
                     * 如果是一个接口，使用接口的类型去自动注入
                     * 在Spring中同样会分别调用不同的方法 autowriedByName autowritedByType
                     */
                    String beanName = service.value();
                    if ("".equals(beanName.trim())) {
                        beanName = lowerFirstCase(clazz.getSimpleName());
                    }
                    Object instance = clazz.newInstance();
                    beanMap.put(beanName, instance);
                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class c : interfaces) {
                        beanMap.put(c.getName(), instance);
                    }
                } else {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 依赖注入
     */
    private void doAutowired() {
        if (beanMap.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(Autowried.class)) {
                    continue;
                }
                Autowried autowried = field.getAnnotation(Autowried.class);
                String beanName = autowried.value().trim();
                if ("".equals(beanName)) {
                    beanName = field.getType().getName();
                }
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(), beanMap.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String lowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
