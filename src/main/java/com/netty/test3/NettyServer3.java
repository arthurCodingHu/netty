package com.netty.test3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-13 20:09
 * @Version: 1.0
 */
public class NettyServer3 {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup(2);

        EventLoopGroup worker = new NioEventLoopGroup(4);

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(boss, worker);

        serverBootstrap.channel(NioServerSocketChannel.class);

        serverBootstrap.childHandler(new MyChildInitializer());

        ChannelFuture channelFuture = serverBootstrap.bind(8833).sync();

        channelFuture.channel().closeFuture().sync();

        boss.shutdownGracefully();
        worker.shutdownGracefully();

    }

}


class MyChildInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new MyChildHandler());
    }
}

class MyChildHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf b = (ByteBuf) msg;
        System.out.println(b.toString());
        ctx.writeAndFlush(b);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}

