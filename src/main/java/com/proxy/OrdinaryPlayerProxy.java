package com.proxy;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-23 13:50
 * @Version: 1.0
 */
public class OrdinaryPlayerProxy implements IGamePlayer {
    private IGamePlayer gamePlayer = null;

    OrdinaryPlayerProxy(String name) {
        try {
            gamePlayer = new OrdinaryGamePlayer(this, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void login(String user, String password) {
        this.gamePlayer.login(user, password);
    }

    @Override
    public void killBoss() {
        this.gamePlayer.killBoss();
    }

    @Override
    public void upperGrade() {
        this.gamePlayer.upperGrade();
    }
}
