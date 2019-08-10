package com.netty.test5;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-13 21:43
 * @Version: 1.0
 */
public class NettyServer5 {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup(2);
        EventLoopGroup worker = new NioEventLoopGroup(4);

        ServerBootstrap s = new ServerBootstrap();
        s.group(boss, worker);

        s.channel(NioServerSocketChannel.class);

        s.childHandler(new MyChannelInitializer());

        ChannelFuture channelFuture = s.bind(8885).sync();

        channelFuture.channel().closeFuture().sync();

        boss.shutdownGracefully();


        worker.shutdownGracefully();

    }
}

class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new MyChildHandler());
    }
}

class MyChildHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println("server====" + byteBuf);

        ctx.writeAndFlush(msg);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
