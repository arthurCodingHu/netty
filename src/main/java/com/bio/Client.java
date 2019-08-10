package com.bio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-10 13:16
 * @Version: 1.0
 */
public class Client {
    public static void main(String[] args) throws Exception{

        Socket socket = new Socket("localhost", 8888);

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedWriter.write("hello world");
//        bufferedWriter.newLine();
        bufferedWriter.flush();
//
//        BufferedReader bw  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        String str = bw.readLine();
//        System.out.println(str);

        bufferedWriter.close();


    }
}
