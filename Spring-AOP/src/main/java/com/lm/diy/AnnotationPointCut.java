package com.lm.diy;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author super
 * Aspect 标记这个类是一个切面
 */
@Aspect
public class AnnotationPointCut {
    @Before("execution(* com.lm.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("方法执行前");
    }
    @After("execution(* com.lm.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("方法执行后");
    }
}
