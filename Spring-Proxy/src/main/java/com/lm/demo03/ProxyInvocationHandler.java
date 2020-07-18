package com.lm.demo03;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 等会会用这个类自动生成代理类
 * @author super
 */
public class ProxyInvocationHandler implements InvocationHandler {

    private Rent rent;

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    /**
     * 生成得到代理类
     * @return
     */
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                rent.getClass().getInterfaces(),
                this);
    }
    /**
     * 处理代理实例并返回结果
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //动态代理的本质就是使用反射实现
        seeHouse();
        return method.invoke(rent, args);
    }
    private void seeHouse(){
        System.out.println("中介带看房子");
    }
    private void fare(){
        System.out.println("中介收取费用");
    }
}
