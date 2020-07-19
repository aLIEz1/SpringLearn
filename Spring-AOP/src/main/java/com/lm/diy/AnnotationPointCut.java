package com.lm.diy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
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
    @Around("execution(* com.lm.service.UserServiceImpl.*(..))")
    public Object around(ProceedingJoinPoint pj) throws Throwable {
        System.out.println("环绕前");
        Signature signature =pj.getSignature();
        System.out.println(signature);
        Object proceed = pj.proceed();
        System.out.println("环绕后");
        return proceed;
    }
}
