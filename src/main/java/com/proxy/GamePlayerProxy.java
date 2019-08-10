package com.proxy;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-23 12:16
 * @Version: 1.0
 */
public class GamePlayerProxy implements IGamePlayer {

    private IGamePlayer gamePlayer = null;

    GamePlayerProxy(IGamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
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
