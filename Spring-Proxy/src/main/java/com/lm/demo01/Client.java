package com.lm.demo01;

/**
 * @author super
 */
public class Client {
    public static void main(String[] args) {
        //代理，中介帮房东出租房子
        // 代理角色一般会有一些附属操作
        // 你不用面对房东，直接找中介租房即可
        Proxy proxy = new Proxy(new Host());
        proxy.rent();
    }
}
