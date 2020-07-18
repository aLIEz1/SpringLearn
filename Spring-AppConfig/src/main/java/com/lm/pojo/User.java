package com.lm.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author super
 * Component 注解 说明这个类被Spring接管了也就是说注册到了容器中
 */
@Component
public class User {
    /**
     * 属性注入值
     */
    @Value("lm")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
