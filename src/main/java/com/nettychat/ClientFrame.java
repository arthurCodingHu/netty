package com.nettychat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-11 20:12
 * @Version: 1.0
 */
public class ClientFrame extends Frame {

    public static final ClientFrame INSTANCE = new ClientFrame();

    private TextArea ta = new TextArea();
    private TextField tf = new TextField();

    private Client client = null;

    public ClientFrame(){
        this.setSize(300, 400);
        this.setLocation(400, 20);
        this.add(ta, BorderLayout.CENTER);
        this.add(tf, BorderLayout.SOUTH);
        this.setTitle("franky聊天室");

        tf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //send to server
                client.send(tf.getText() +"\r\n");
                tf.setText("");

            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                client.closeConnection();
                System.exit(0);
            }
        });
    }

    void  connectToServer() throws Exception{
        client = new Client();
        client.connect();
    }

    public static void main(String[] args) throws Exception{
        ClientFrame f = ClientFrame.INSTANCE;
        f.setVisible(true);
        f.connectToServer();
    }

    public void updateText(String s) {
        ta.setText(ta.getText() + s);
//        ta.setText(ta.getText() + "aaaa"+"\r\n");
    }
}
