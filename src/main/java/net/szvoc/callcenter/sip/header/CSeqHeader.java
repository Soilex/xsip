package net.szvoc.callcenter.sip.header;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.var;
import net.szvoc.callcenter.sip.Header;
import net.szvoc.callcenter.sip.HeaderName;
import net.szvoc.callcenter.sip.annotation.SipHeader;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SipHeader(HeaderName.CSEQ)
public class CSeqHeader extends Header {
    private int seq;
    private String method;

    public CSeqHeader(int seq, String method) {
        super(HeaderName.CSEQ, seq + " " + method);
    }

    @Override
    protected void load(String nameAndValue) {
        super.load(nameAndValue);

        var index = value().indexOf(' ');
        this.seq = Integer.parseInt(value().substring(0, index));
        this.method = value().substring(index + 1).trim();
    }
}
