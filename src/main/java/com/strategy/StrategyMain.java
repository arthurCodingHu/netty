package com.strategy;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-08-09 13:46
 * @Version: 1.0
 */
public class StrategyMain {
    public static void main(String[] args) {
        int a = Integer.valueOf(args[0]);
        String symbol = args[1];
        int b = Integer.valueOf(args[2]);
        System.out.println("输出结果为:" + Calculator.ADD.exec(a, b));
    }
}
