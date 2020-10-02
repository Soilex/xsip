package net.szvoc.callcenter.sip.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.var;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.StringUtils;

@Getter
@AllArgsConstructor
public class Field {
    private String name;
    private String value;
    private boolean quote;
    private char delimiter;

    public Field(String name, String value) {
        this.name = name;
        this.value = value;
        this.quote = true;
        this.delimiter = '=';
    }

    public static Field parse(String text) {
        return parse(text, '=');
    }

    public static Field parse(String text, char delimiter) {
        String name, value;
        boolean quote = false;
        var index = text.indexOf(delimiter);
        if (index > 0) {
            name = text.substring(0, index).trim();
            value = text.substring(index + 1).trim();
            quote = value.startsWith("\"");
            value = StringUtils.trimLeadingCharacter(value, '\"');
            value = StringUtils.trimTrailingCharacter(value, '\"');
        } else {
            name = text;
            value = "";
        }
        return new Field(name, value, quote, delimiter);
    }

    @Override
    public String toString() {
        return Strings.isBlank(value) ? name : (name + delimiter + (this.quote ? "\"" + value + "\"" : value));
    }
}
