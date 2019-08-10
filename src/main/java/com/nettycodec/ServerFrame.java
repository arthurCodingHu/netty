package com.nettycodec;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-17 08:18
 * @Version: 1.0
 */
public class ServerFrame extends Frame {
    //设置单例
    public static final ServerFrame INSTANCE = new ServerFrame();

    private Server server = new Server();

    TextArea taServer = new TextArea();
    TextArea taClient = new TextArea();

    public ServerFrame() {
        this.setSize(800, 600);
        this.setLocation(300, 30);
        Panel p = new Panel(new GridLayout(1, 2));
        p.add(taServer);
        p.add(taClient);
        this.add(p);

        taServer.setFont(new Font("Consolas", Font.PLAIN, 25));
        taClient.setFont(new Font("Consolas", Font.PLAIN, 25));

        this.updateServerMeg("server:");
        this.updateClientMeg("client:");

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void updateServerMeg(String text) {
        this.taServer.setText(taServer.getText() + text + System.getProperty("line.separator"));
    }

    public void updateClientMeg(String text) {
        this.taClient.setText(taClient.getText() + text + System.getProperty("line.separator"));
    }

    public static void main(String[] args) {
        ServerFrame.INSTANCE.setVisible(true);
        ServerFrame.INSTANCE.server.serverStart();
    }

}
