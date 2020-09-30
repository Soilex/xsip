package net.szvoc.callcenter.sip;

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

    public Field(String name, String value) {
        this.name = name;
        this.value = value;
        this.quote = true;
    }

    public Field(String nameAndValue) {
        var index = nameAndValue.indexOf('=');
        if (index > 0) {
            this.name = nameAndValue.substring(0, index).trim();
            this.value = nameAndValue.substring(index + 1).trim();
            this.quote = this.value.startsWith("\"");
            this.value = StringUtils.trimLeadingCharacter(this.value, '\"');
            this.value = StringUtils.trimTrailingCharacter(this.value, '\"');
        } else {
            this.name = nameAndValue;
            this.value = "";
        }
    }

    @Override
    public String toString() {
        return Strings.isBlank(value) ? name : (name + "=" + (this.quote ? "\"" + value + "\"" : value));
    }
}
