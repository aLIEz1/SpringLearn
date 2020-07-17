package com.lm.pojo;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void testUser() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("userBeans.xml");
        User user = context.getBean("user2",User.class);
        System.out.println(user);
    }
}