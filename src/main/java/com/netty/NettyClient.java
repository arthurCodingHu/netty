package com.netty;

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
 * @CreateDate: 2019-07-09 21:14
 * @Version: 1.0
 */
public class NettyClient {
    public static void main(String[] args) throws Exception{
        EventLoopGroup work = new NioEventLoopGroup(1);

        Bootstrap b = new Bootstrap();

        b.group(work);
        b.channel(NioSocketChannel.class);
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new MyHandler());
            }
        });

        ChannelFuture channelFuture = b.connect("localhost", 9999).sync();
        channelFuture.channel().closeFuture().sync();

        System.out.println("go on");

        work.shutdownGracefully();

    }

    static class MyHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            System.out.println(ctx.toString());
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ByteBuf byteBuf= Unpooled.copiedBuffer("franky".getBytes());
            ctx.writeAndFlush(byteBuf);
        }
    }
}
