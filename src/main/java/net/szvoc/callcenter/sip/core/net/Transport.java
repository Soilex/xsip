package net.szvoc.callcenter.sip.core.net;

import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.szvoc.callcenter.sip.core.SipTransport;
import net.szvoc.callcenter.sip.core.event.SipEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ApplicationEventMulticaster;

import java.net.InetSocketAddress;

@Slf4j
public abstract class Transport implements AutoCloseable {
    protected final static EventLoopGroup bossGroup = new NioEventLoopGroup();

    @Getter
    private InetSocketAddress bind;

    @Autowired
    private ApplicationEventMulticaster multicaster;

    public abstract SipTransport getTransportType();

    @Getter
    @Setter(AccessLevel.PROTECTED)
    private Channel channel;

    public Transport(InetSocketAddress bind) {
        this.bind = bind;
    }

    public abstract Connection select(Channel channel);

    public void open() throws Exception {
        log.info("Sip server startup at {}:{}:{}.", getTransportType().name().toLowerCase(), bind.getHostName(), bind.getPort());
    }

    @Override
    public void close() throws Exception {
        this.channel.close();
        log.info("Sip server shutdown at {}:{}:{}.", getTransportType().name().toLowerCase(), bind.getHostName(), bind.getPort());
    }

    public void triggerEvent(SipEvent event) {
        multicaster.multicastEvent(event);
    }
}
