package net.szvoc.callcenter.sip.net;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.DatagramPacket;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import net.szvoc.callcenter.sip.InOutFlag;
import net.szvoc.callcenter.sip.event.SipEvent;
import net.szvoc.callcenter.sip.protocol.Request;

import java.nio.charset.StandardCharsets;

@Slf4j
@AllArgsConstructor
public class InboundHandler extends ChannelInboundHandlerAdapter {
    private SipServer server;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        var packet = (DatagramPacket) msg;
        var payload = packet.content().toString(StandardCharsets.UTF_8);
        log.debug("[ IN] {} {}", ctx.channel().id(), payload);
        if (!payload.equalsIgnoreCase("\r\n")) {
            var request = Request.parse(packet.sender(), packet.recipient(), payload);
            var connection = server.select(ctx.channel());
            server.triggerEvent(new SipEvent(connection, InOutFlag.IN, request));
        } else {
            var ack = new DatagramPacket(Unpooled.copiedBuffer("\r\n", StandardCharsets.UTF_8), packet.sender());
            ctx.writeAndFlush(ack);
            log.debug("[OUT] {} {}", ctx.channel().id(), "\r\n");
        }
    }
}
