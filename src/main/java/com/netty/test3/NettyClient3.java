package com.netty.test3;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-13 20:26
 * @Version: 1.0
 */
public class NettyClient3 {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup client  = new NioEventLoopGroup(1);

        Bootstrap b = new Bootstrap();

        b.group(client);

        b.channel(NioSocketChannel.class);

        b.handler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new MyHandler());
            }
        });

        ChannelFuture channelFuture = b.connect("localhost", 8833).sync();

        channelFuture.channel().closeFuture().sync();

        System.out.println("client go on");

        client.shutdownGracefully();
    }

    private static class MyHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ByteBuf byteBuf = Unpooled.copiedBuffer("franky".getBytes());
            ctx.writeAndFlush(byteBuf);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("client=====:"+msg.toString());
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }
    }
}
