package com.nettychat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-11 20:23
 * @Version: 1.0
 */
public class Server {

    public ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public void serverStart() {
        EventLoopGroup boss = new NioEventLoopGroup(2);
        EventLoopGroup worker = new NioEventLoopGroup(4);

        try {
            ServerBootstrap s = new ServerBootstrap();

            ChannelFuture future = s.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ServerChildHandler());
                        }
                    })
                    .bind(8888)
                    .sync();
            ServerFrame.INSTANCE.updateServerMeg("server started!");
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }


    }

    private class ServerChildHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            clients.add(ctx.channel());
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf byteBuf = (ByteBuf) msg;

            byte[] bytes = new byte[byteBuf.readableBytes()];

            byteBuf.getBytes(0, bytes);
            byteBuf.readBytes(bytes);
//            String s = new String(bytes);
            String s = new String(bytes, 0, byteBuf.readableBytes(), "UTF-8");
            if (s.equals("bye")) {
                System.out.println("client ready to exit!");
                clients.remove(ctx.channel());
                ctx.close();
                System.out.println(clients.size());
            } else {
                clients.writeAndFlush(msg);
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            clients.remove(ctx.channel());
            ctx.close();
        }
    }
}
