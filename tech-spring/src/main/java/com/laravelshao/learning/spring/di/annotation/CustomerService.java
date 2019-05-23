package com.laravelshao.learning.spring.di.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//@Component("customerService")
@Service("customerService") //默认bean名称为类名首字母小写
public class CustomerService {
    /**
     * 注入简单类型
     */
    @Value("貂蝉") //底层自动调用setName("Rose")
    private String name;

    /**
     * 注入复杂类型
     * <p>
     * 方式1 使用@Value结合SpEL(spring3.0后可用)
     * 方式2 使用@Autowired结合@Qualifier(推荐)
     * 方式3 使用JSR-250标准(JDK)提供的@Resource
     * 方式4 使用JSR-330标准(JDK)提供的@Inject
     */

    @Value("#{customerDAO}")

    //@Autowired//默认按照类型注入
    //@Qualifier("customerDAO")//配合@autowaired使用 可根据指定bean名字注入

    //@Resource//默认按照类型注入
    //@Resource(name="customerDAO")//按照bean名称注入

    //@Inject//默认按照类型注入
    //@Named("customerDAO")//必须配合@Inject使用 按照bean名称注入
    private CustomerDAO customerDAO;

    public void save() {
        System.out.println("CustomerService->业务层save()方法被调用.......");
        System.out.println("name：" + name);

        //调用dao层，需要注入dao对象（bean）
        customerDAO.save();
    }
}
