package com.proxy;

/**
 * @Description: 设计模式之责任链模式
 * @Author: franky
 * @CreateDate: 2019-07-23 13:44
 * @Version: 1.0
 */
public class OrdinaryGamePlayer implements IGamePlayer {

    private  String name = null;
    public OrdinaryGamePlayer(IGamePlayer iGamePlayer, String name) throws Exception{
        if (iGamePlayer == null) {
            throw new Exception("创建真实角色失败");
        } else {
            this.name = name;
        }
    }

    @Override
    public void login(String user, String password) {
        System.out.println("登录名：" + user + "密码：" + password+ this.name + "登录成功");
    }

    @Override
    public void killBoss() {
        System.out.println(this.name +"in fighting with boss");
    }

    @Override
    public void upperGrade() {
        System.out.println(this.name + "升了一级");
    }
}
