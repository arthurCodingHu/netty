package com.io;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-16 14:25
 * @Version: 1.0
 */
public class OutIo {

    public static void main(String[] args) {
        try (OutputStream outputStream = new FileOutputStream("/Users/hujiaming/Desktop/publish-document/aa.txt")) {
            outputStream.write("frankyHello".getBytes());
            outputStream.flush();
            outputStream.write("\r\n".getBytes());
            outputStream.write("arlenHello".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
