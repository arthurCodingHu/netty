package com.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-08-10 15:28
 * @Version: 1.0
 */
public class TryConcurrent {
    public static AtomicInteger countIndex = new AtomicInteger(1);
    public static void main(String[] args) {
        new Thread(() -> listenMusic()).start();
        redNews();
    }


    public static void listenMusic() {
        for (;;) {
            System.out.println("听音乐......"+countIndex.getAndIncrement());
            sleep();
        }
    }

    public static void redNews() {
        for (;;) {
            System.out.println("看新闻......"+countIndex.getAndIncrement());
            sleep();
        }
    }

    public static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
