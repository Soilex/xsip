package net.szvoc.callcenter.sip.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.var;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class Addr {
    private String name;
    private String uri;
    private List<Field> fields;

    public static Addr parse(String value) {
        var name = "";
        var uri = "";
        var fields = new ArrayList<Field>();

        var from = value.indexOf('<');
        if (from >= 0) {
            var to = value.indexOf('>');
            name = value.substring(0, from).trim();
            name = StringUtils.trimLeadingCharacter(name, '\"');
            name = StringUtils.trimTrailingCharacter(name, '\"');
            var temp = value.substring(from + 1, to).split(";");
            uri = temp[0];
            Arrays.stream(temp)
                    .skip(1)
                    .forEach(c -> fields.add(Field.parse(c)));
        } else {
            uri = value;
            uri = StringUtils.trimLeadingCharacter(uri, '\"');
            uri = StringUtils.trimTrailingCharacter(uri, '\"');
        }
        return new Addr(name, uri, fields);
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();
        if (StringUtils.isEmpty(name)) {
            stringBuilder.append("<").append(uri).append(">");
        } else {
            stringBuilder.append("\"").append(name).append("\" ").append("<").append(uri);
            if (fields != null && !fields.isEmpty()) {
                stringBuilder.append(";").append(fields.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(";"))
                );
            }
            stringBuilder.append(">");
        }

        return stringBuilder.toString();
    }
}
