package com.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * BIO【OIO】 模型  效率高  模型简单 基本被淘汰，如果是能够确定连接少，就可以使用这种模型
 * 但是如果并发很高，是成千上万，这时候就会启动成千上万个线程 ，很浪费资源
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-22 23:09
 * @Version: 1.0
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress("localhost", 8888));
        boolean stared = true;

        while (stared) {
            // accept 是阻塞方法
            Socket s = ss.accept();
            new Thread(() -> {
                try {

                    BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    // readLine 也是阻塞的，如果client连接上了，但是就是不关闭不写数据，这里就阻塞在这里
                    String str = reader.readLine();

                    System.out.println(str);

                    reader.close();
                    s.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        ss.close();
    }
}
