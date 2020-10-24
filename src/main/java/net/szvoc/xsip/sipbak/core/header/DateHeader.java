package net.szvoc.xsip.sipbak.core.header;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.szvoc.xsip.sipbak.core.Header;
import net.szvoc.xsip.sipbak.core.HeaderName;
import net.szvoc.xsip.sipbak.core.annotation.SipHeader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SipHeader(HeaderName.DATE)
public class DateHeader extends Header {

    private static final DateTimeFormatter gmt = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);

    public LocalDateTime date() {
        return LocalDateTime.parse(this.value(), gmt);
    }

    public DateHeader(LocalDateTime date) {
        super(HeaderName.CSEQ, date.format(gmt));
    }
}
