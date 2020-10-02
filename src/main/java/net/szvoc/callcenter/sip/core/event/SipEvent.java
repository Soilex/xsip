package net.szvoc.callcenter.sip.core.event;

import lombok.Getter;
import net.szvoc.callcenter.sip.core.InOutFlag;
import net.szvoc.callcenter.sip.core.net.Connection;
import net.szvoc.callcenter.sip.core.Message;
import org.springframework.context.ApplicationEvent;

@Getter
public class SipEvent extends ApplicationEvent {
    private InOutFlag direction;
    private Message message;


    @Override
    public Connection getSource() {
        return (Connection) super.getSource();
    }

    public SipEvent(Connection source, InOutFlag direction, Message message) {
        super(source);
        this.direction = direction;
        this.message = message;
    }
}
