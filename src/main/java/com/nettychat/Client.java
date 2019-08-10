package com.nettychat;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-11 20:12
 * @Version: 1.0
 */
public class Client {

    private Channel channel = null;

    public void connect() throws Exception {
        EventLoopGroup work = new NioEventLoopGroup(1);

        Bootstrap b = new Bootstrap();

        b.group(work);
        b.channel(NioSocketChannel.class);
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                channel = ch;
                ch.pipeline().addLast(new MyHandler());
            }
        });

        System.out.println("connect to server");
        ChannelFuture channelFuture = b.connect("localhost", 8888).sync();
        channelFuture.channel().closeFuture().sync();

        System.out.println("go on");

        work.shutdownGracefully();


    }

    public void send(String text){
//        ByteBuf byteBuf = Unpooled.buffer();
//        try {
//            byteBuf.writeBytes(text.getBytes("utf-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        channel.writeAndFlush(byteBuf);
        channel.writeAndFlush(Unpooled.copiedBuffer(text.getBytes(Charset.forName("utf-8"))));
    }

    public void closeConnection() {
        send("bye");
        channel.close();
    }

    static class MyHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            ByteBuf byteBuf = (ByteBuf) msg;

           try{
               byte[] bytes = new byte[byteBuf.readableBytes()];

               byteBuf.getBytes(0, bytes);
               String s = new String(bytes);
               System.out.println(s);
               System.out.println(byteBuf.refCnt());
               ClientFrame.INSTANCE.updateText(s);
           } finally {
               ReferenceCountUtil.release(byteBuf);
           }

        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }
    }
}
