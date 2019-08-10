package com.nettycodec;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-18 19:06
 * @Version: 1.0
 */
public class TankMsg {
    public int x,y;

    public TankMsg(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "TankMsg{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
