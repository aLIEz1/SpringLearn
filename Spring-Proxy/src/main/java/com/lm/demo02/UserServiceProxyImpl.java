package com.lm.demo02;

public class UserServiceProxyImpl implements UserService{
    private UserServiceImpl service;

    public void setService(UserServiceImpl service) {
        this.service = service;
    }

    @Override
    public void add() {
        log("add");
        service.add();
    }

    @Override
    public void delete() {
        log("delete");
        service.delete();
    }

    @Override
    public void update() {
        log("update");
        service.update();

    }

    @Override
    public void query() {
        log("query");
        service.query();

    }
    private void log(String msg){
        System.out.println("使用了"+msg+"方法");
    }
}
