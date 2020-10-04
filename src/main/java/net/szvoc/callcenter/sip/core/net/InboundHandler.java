package net.szvoc.callcenter.sip.core.net;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.DatagramPacket;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import net.szvoc.callcenter.sip.core.InOutFlag;
import net.szvoc.callcenter.sip.core.event.SipEvent;
import net.szvoc.callcenter.sip.core.Request;

import java.nio.charset.StandardCharsets;

@Slf4j
@AllArgsConstructor
public class InboundHandler extends ChannelInboundHandlerAdapter {
    private UdpTransport transport;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        var packet = (DatagramPacket) msg;
        var payload = packet.content().toString(StandardCharsets.UTF_8);
        log.debug("[ IN] {} {}", ctx.channel().id(), payload);
        if (!payload.equalsIgnoreCase("\r\n")) {
            var request = Request.parse(packet.sender(), packet.recipient(), payload);
            var connection = transport.select(ctx.channel());
            transport.triggerEvent(new SipEvent(connection, InOutFlag.IN, request));
        } else {
            var ack = new DatagramPacket(Unpooled.copiedBuffer("\r\n", StandardCharsets.UTF_8), packet.sender());
            ctx.writeAndFlush(ack);
            log.debug("[OUT] {} {}", ctx.channel().id(), "\r\n");
        }
    }
}
