package com.lm.config;

import com.lm.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author super
 * Configuration 这个也会被Spring托管，注册到容器中，自己本省就是一个 Component
 * Configuration 表示这是一个配置类，就是和beans.xml一样的
 */
@Configuration
@ComponentScan("com.lm.pojo")
public class LmConfig {
    /**
     * 注册 Bean 相当于之前写的<bean></bean>
     * 这个方法的名字就相当于bean标签中的id属性
     * 这个方法的返回值就相当于bean标签中的class对象
     * @return user类
     */
    @Bean
    public User getUser(){
        //返回要注入到bean中的对象
        return new User();
    }
}
