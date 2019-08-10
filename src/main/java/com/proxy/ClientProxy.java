package com.proxy;

/**
 *
 * 代理模式
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-23 12:18
 * @Version: 1.0
 */

public class ClientProxy {
    public static void main(String[] args) {
        System.out.println("开始游戏");
        IGamePlayer gamePlayer = new GamePlayer("张三");

        IGamePlayer gamePlayerProxy = new GamePlayerProxy(gamePlayer);

        gamePlayerProxy.login("zhangsan", "123456");

        gamePlayerProxy.killBoss();

        gamePlayerProxy.upperGrade();
        System.out.println("结束游戏");

    }
}
