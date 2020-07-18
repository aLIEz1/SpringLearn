package com.lm.demo02;

public class Client {
    public static void main(String[] args) {
        UserServiceProxyImpl proxy =new UserServiceProxyImpl();
        proxy.setService(new UserServiceImpl());
        proxy.add();
        System.out.println("===================");
        proxy.delete();
        System.out.println("===================");
        proxy.update();
        System.out.println("===================");
        proxy.query();
    }
}
