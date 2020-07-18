package com.lm.demo01;

/**
 * @author super
 * 代理帮房东出租房子
 */
public class Proxy implements Rent{
    private Host host;

    public Proxy(Host host) {
        this.host = host;
    }

    public Proxy() {
    }


    @Override
    public void rent() {
        seeHouse();
        host.rent();
        signContract();
        fare();

    }

    /**
     * 看房方法
     */
    private void seeHouse(){
        System.out.println("中介带你看房");
    }

    /**
     * 收中介费
     */
    private void fare(){
        System.out.println("中介收取中介费");
    }

    /**
     * 签合同
     */
    private void signContract(){
        System.out.println("签合同");
    }
}
