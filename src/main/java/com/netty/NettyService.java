package com.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-09 21:04
 * @Version: 1.0
 */
public class NettyService {
    public static void main(String[] args) throws Exception{
        EventLoopGroup boss = new NioEventLoopGroup(2);
        EventLoopGroup worker = new NioEventLoopGroup(4);
        ServerBootstrap b = new ServerBootstrap();
        b.group(boss, worker);

        b.channel(NioServerSocketChannel.class);

        b.childHandler(new MyChildInitializer());

        ChannelFuture channelFuture =  b.bind(9999).sync();
        channelFuture.channel().closeFuture().sync();

        boss.shutdownGracefully();
        worker.shutdownGracefully();

    }

}

class MyChildInitializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new MyChildHandler());
    }
}

class MyChildHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;

        System.out.println(buf.toString());

        ctx.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable) throws Exception{
        super.exceptionCaught(ctx, throwable);
    }

}
