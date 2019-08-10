package com.nettycodec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description:
 * @Author: franky
 * @CreateDate: 2019-07-22 19:40
 * @Version: 1.0
 */
class TankMsgTest {

    @Test
    void decode() {
        EmbeddedChannel ch = new EmbeddedChannel();
        ch.pipeline().addLast(new TankMsgDecoder());

        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(5);
        buf.writeInt(8);

        ch.writeInbound(buf);

        TankMsg tm = ch.readInbound();

        assertEquals(5, tm.x);
        assertEquals(8, tm.y);

    }

    @Test
    void encode() {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel();
        embeddedChannel.pipeline().addLast(new TankMsgEncoder());
        TankMsg tm = new TankMsg(5, 8);

        embeddedChannel.writeOutbound(tm);
        ByteBuf buf = embeddedChannel.readOutbound();
        int x = buf.readInt();
        int y = buf.readInt();

        assertEquals(5, x);
        assertEquals(8, y);


    }

}