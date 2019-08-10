package com.nettycodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-18 19:10
 * @Version: 1.0
 */
public class TankMsgEncoder extends MessageToByteEncoder<TankMsg> {

    @Override
    protected void encode(ChannelHandlerContext ctx, TankMsg msg, ByteBuf out) throws Exception {
        out.writeInt(msg.x);
        out.writeInt(msg.y);

    }
}
