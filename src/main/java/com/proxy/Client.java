package com.proxy;

import java.sql.SQLOutput;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-23 12:13
 * @Version: 1.0
 */
public class Client {
    public static void main(String[] args) {

        /*System.out.println("开始游戏");

        IGamePlayer gamePlayer = new GamePlayer("张三");

        gamePlayer.login("zhangsan", "12356");

        gamePlayer.killBoss();

        gamePlayer.upperGrade();

        System.out.println("结束游戏");*/

        // ==========更简洁的方式=======，普通代理，调用者只需要知道代理即可，不需要知道具体的代理了谁

        System.out.println("begin play game");

        IGamePlayer gamePlayer = new OrdinaryPlayerProxy("张三");

        gamePlayer.login("zhangsan", "123456");

        gamePlayer.killBoss();

        gamePlayer.upperGrade();

        System.out.println("game over");


    }
}
