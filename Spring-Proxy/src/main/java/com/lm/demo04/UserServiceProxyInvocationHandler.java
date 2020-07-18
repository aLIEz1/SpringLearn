package com.lm.demo04;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author super
 */
public class UserServiceProxyInvocationHandler implements InvocationHandler {
    private Object target;

    public void setUserService(UserServiceImpl userService) {
        this.target = userService;
    }
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log(method.getName());
        Object invoke = method.invoke(target, args);
        end();
        return invoke;
    }

    private void end() {
        System.out.println("退出");
    }

    private void log(String msg) {
        System.out.println("使用了"+msg+"方法");
    }

}
