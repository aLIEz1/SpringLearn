package com.lm.pojo;

import com.lm.config.LmConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void testGetUser() {
        //如果完全使用了配置类方式去做，
        // 我们就只能通过AnnotationConfigApplicationContext上下文来获取配置，
        // 通过配置类的class对象加载
        ApplicationContext context =new AnnotationConfigApplicationContext(LmConfig.class);
        User user = context.getBean("getUser", User.class);
        System.out.println(user.getName());
    }
}