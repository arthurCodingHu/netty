package com.io;

import java.io.*;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-16 14:42
 * @Version: 1.0
 */
public class InIo {
    public static void main(String[] args) throws Exception{
        String filePath = "/Users/hujiaming/Desktop/publish-document/aa.txt";
        getByFileOutPutStream(filePath);
        getByFileReader(filePath);
    }

    public static void getByFileOutPutStream(String filePath) {
        try (InputStream in = new FileInputStream(filePath)) {
            byte[] bytes = new byte[1024];
            int length;
            while ((length = in.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, length));
            }

//            BufferedReader  r= new BufferedReader(new FileReader("/Users/hujiaming/Desktop/publish-document/aa.txt"));
//            System.out.println(in.read());
//            System.out.println(r.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public static void getByFileReader(String path) throws Exception{
        try (FileReader f = new FileReader(path)) {
            char[] bytes = new char[1024];
            int length;
            while ((length = f.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, length));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        InputStream in = new FileInputStream("");

        int byteRead = 0;
        int byteToRead = 1024;
        byte[] bytes = new byte[byteToRead];
        while (byteToRead > byteRead) {
            int result = in.read(bytes, byteRead, byteToRead-byteRead);

            if(result == -1) break;

            byteRead += result;
        }

   }
}
