package com.concurrent;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-08-10 16:14
 * @Version: 1.0
 */
public class TicketWindowRunnable implements Runnable{
    private static final int max = 50;
    private int index = 1;

    @Override
    public void run() {
        while (index < max) {
            System.out.println(Thread.currentThread() + "的号码是:" + index++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TicketWindowRunnable ticketWindowRunnable = new TicketWindowRunnable();
        Thread t1 = new Thread(ticketWindowRunnable, "一号柜台");
        Thread t2 = new Thread(ticketWindowRunnable, "二号柜台");
        Thread t3 = new Thread(ticketWindowRunnable, "三号柜台");
        Thread t4 = new Thread(ticketWindowRunnable, "四号柜台");
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}
