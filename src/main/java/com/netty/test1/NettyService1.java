package com.netty.test1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-11 17:45
 * @Version: 1.0
 */
public class NettyService1 {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup lead = new NioEventLoopGroup(2);
        EventLoopGroup worker = new NioEventLoopGroup(4);

        ServerBootstrap b = new ServerBootstrap();
        b.group(lead, worker);


        b.channel(NioServerSocketChannel.class);

        b.childHandler(new MyChildHandlerInitializer());

        ChannelFuture future = b.bind(8881).sync();

        future.channel().closeFuture().sync();

        lead.shutdownGracefully();
        worker.shutdownGracefully();

    }

}

class MyChildHandlerInitializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new myChildHandler());
    }

}
class myChildHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("server read:======="+byteBuf.toString());
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
