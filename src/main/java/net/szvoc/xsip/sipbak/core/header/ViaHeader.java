package net.szvoc.xsip.sipbak.core.header;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.var;
import net.szvoc.xsip.sipbak.core.Field;
import net.szvoc.xsip.sipbak.core.Header;
import net.szvoc.xsip.sipbak.core.HeaderName;
import net.szvoc.xsip.sipbak.core.annotation.SipHeader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SipHeader(HeaderName.VIA)
public class ViaHeader extends Header {
    private String version;
    private String transport;
    private String contact;
    private List<Field> fields = new ArrayList<>();

    public ViaHeader(String version, String transport, String contact, List<Field> fields) {
        super(HeaderName.VIA, generateValue(version, transport, contact, fields));
    }

    @Override
    protected void load(String nameAndValue) {
        super.load(nameAndValue);

        var temp = value().split(";");
        var from = temp[0].lastIndexOf('/');
        var to = temp[0].lastIndexOf(' ');

        this.version = temp[0].substring(0, from);
        this.transport = temp[0].substring(from + 1, to);
        this.contact = temp[0].substring(to + 1);

        Arrays.stream(temp)
                .skip(1)
                .forEach(c -> fields.add(Field.parse(c)));
    }

    private static String generateValue(String version, String transport, String contact, List<Field> fields) {
        var stringBuilder = new StringBuilder()
                .append(version).append("/").append(transport).append(" ").append(contact);
        if (fields != null && !fields.isEmpty()) {
            stringBuilder.append(";").append(fields.stream().map(Field::toString).collect(Collectors.joining(";")));
        }
        return stringBuilder.toString();
    }
}
