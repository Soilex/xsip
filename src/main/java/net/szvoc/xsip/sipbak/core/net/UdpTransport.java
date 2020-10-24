package net.szvoc.xsip.sipbak.core.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import net.szvoc.xsip.sipbak.core.SipTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Slf4j
@Component
public class UdpTransport extends Transport {

    private Connection connection;

    public UdpTransport(@Value("${sip.bind:127.0.0.1}") String host, @Value("${sip.port:5060}") int port) {
        super(new InetSocketAddress(host, port));
    }

    @Override
    public SipTransport getTransportType() {
        return SipTransport.UDP;
    }

    @Override
    public synchronized Connection select(Channel channel) {
        if (connection == null) {
            connection = new Connection(this, channel);
        }
        return connection;
    }

    @Override
    public void open() throws Exception {
        var bootstrap = new Bootstrap()
                .group(bossGroup)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_REUSEADDR, true)
                .handler(new InboundHandler(this));
        this.setChannel(bootstrap.bind(this.getBind()).sync().channel());
        super.open();
    }
}
