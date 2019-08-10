package com.netty.test1;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-11 19:20
 * @Version: 1.0
 */
public class NettyClient1 {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup client = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(client);

        b.channel(NioSocketChannel.class);
        b.handler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new MyHandler());
            }
        });

       ChannelFuture future = b.connect("localhost", 8881).sync();

       future.channel().closeFuture().sync();

        System.out.println("client end");

        client.shutdownGracefully();



    }

    private static class MyHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("client read:=======" +msg.toString());
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ByteBuf byteBuf  = Unpooled.copiedBuffer("frankyHello".getBytes());
            ctx.writeAndFlush(byteBuf);
        }
    }
}
