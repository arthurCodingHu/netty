package com.templatemethodpattern;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-08-09 13:28
 * @Version: 1.0
 */
public abstract class HummerModel {
    public abstract void start();
    public abstract void stop();
    public abstract void alarm();
    public abstract void engineBoom();
    final void run() {
        this.start();
        this.engineBoom();
        if (this.isAlarm()) {
            this.alarm();
        }
        this.stop();
    }
    //hook method
    public boolean isAlarm() {
        return true;
    }

}
