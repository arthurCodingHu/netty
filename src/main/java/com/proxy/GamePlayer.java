package com.proxy;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-23 11:36
 * @Version: 1.0
 */
public class GamePlayer implements IGamePlayer {

    private String name = null;

    GamePlayer(String name) {
        this.name = name;
    }

    @Override
    public void login(String user, String password) {
        System.out.println("登录名：" + user + "密码：" + password + "用户" + this.name + "登录成功");
    }

    @Override
    public void killBoss() {
        System.out.println(this.name + "in fighting with boss");
    }

    @Override
    public void upperGrade() {
        System.out.println(this.name + "升了一级");
    }
}
