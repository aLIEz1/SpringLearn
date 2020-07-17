package com.lm.pojo;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


class HelloTest {
    @Test
    void testShow() {
        //获取Spring的上下文对象
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");
        //我们的对象都在Spring中管理，我们要使用直接取出来就可以
        Hello hello = (Hello) context.getBean("hello");
        hello.show();
    }
}