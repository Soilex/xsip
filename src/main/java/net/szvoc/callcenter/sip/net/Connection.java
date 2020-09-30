package net.szvoc.callcenter.sip.net;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.socket.DatagramPacket;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import net.szvoc.callcenter.sip.protocol.Message;

import java.net.InetSocketAddress;

@Slf4j
@AllArgsConstructor
public class Connection {
    private Channel channel;

    public InetSocketAddress localAddress() {
        return (InetSocketAddress) this.channel.localAddress();
    }

    public void send(Message message) {
        var packet = new DatagramPacket(Unpooled.copiedBuffer(message.buffer()), message.recipient());
        this.channel.writeAndFlush(packet);

        log.debug("[OUT] {} {}", this.channel.id(), message.string());
    }
}
