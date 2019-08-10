package com.concurrent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.MessageDigest;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-22 21:23
 * @Version: 1.0
 */
public class DigestThread extends Thread {

    private String fileName;

    public DigestThread(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(fileName);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
