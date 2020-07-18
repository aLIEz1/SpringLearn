package com.lm.demo04;

/**
 * @author super
 */
public class Client {
    public static void main(String[] args) {
        UserServiceImpl userService =new UserServiceImpl();
        UserServiceProxyInvocationHandler handler =new UserServiceProxyInvocationHandler();
        handler.setUserService(userService);
        UserService proxy = (UserService) handler.getProxy();
        proxy.delete();
    }
}
