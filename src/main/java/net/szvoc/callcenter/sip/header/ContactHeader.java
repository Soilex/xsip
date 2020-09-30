package net.szvoc.callcenter.sip.header;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.var;
import net.szvoc.callcenter.sip.Addr;
import net.szvoc.callcenter.sip.Field;
import net.szvoc.callcenter.sip.Header;
import net.szvoc.callcenter.sip.HeaderName;
import net.szvoc.callcenter.sip.annotation.SipHeader;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SipHeader(HeaderName.CONTACT)
public class ContactHeader extends Header {
    private Addr addr;
    private List<Field> fields = new ArrayList<>();

    protected ContactHeader(String headerName, Addr addr, List<Field> fields) {
        super(headerName, generateValue(addr, fields));
    }

    public ContactHeader(Addr addr, List<Field> fields) {
        this(HeaderName.CONTACT, addr, fields);
    }

    @Override
    protected void load(String nameAndValue) {
        super.load(nameAndValue);

        var index = value().indexOf('>');
        if (index > 0) {
            this.addr = Addr.parse(value().substring(0, ++index));
            if (index < value().length()) {
                Arrays.stream(value().substring(index).split(";"))
                        .forEach(c -> {
                            if (!StringUtils.isEmpty(c)) {
                                this.fields.add(new Field(c));
                            }
                        });
            }
        } else {
            var temp = value().split(";");
            this.addr = Addr.parse(temp[0]);
            Arrays.stream(temp)
                    .skip(1)
                    .forEach(c -> this.fields.add(new Field(c)));
        }
    }

    private static String generateValue(Addr addr, List<Field> fields) {
        var stringBuilder = new StringBuilder().append(addr.toString());
        if (fields != null && !fields.isEmpty()) {
            stringBuilder.append(";").append(fields.stream().map(Field::toString).collect(Collectors.joining(";")));
        }
        return stringBuilder.toString();
    }
}
