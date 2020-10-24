package net.szvoc.xsip.sip.common;

import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

public class Parameter {
    @Getter
    @Setter
    private String name;

    private String value;
    private boolean quote;

    public String getString() {
        return value;
    }

    public void setValue(String value) {
        if (StringUtils.hasText(value)) {
            this.quote = value.startsWith("\"") && value.endsWith("\"");
            this.value = this.quote ? value.substring(1, value.length() - 1) : value;
        } else {
            this.value = value;
        }
    }

    public int getInt32() {
        return Integer.parseInt(value);
    }

    public void setValue(int value) {
        this.value = String.valueOf(value);
        this.quote = false;
    }

    public long getInt64() {
        return Long.parseLong(value);
    }

    public void setValue(long value) {
        this.value = String.valueOf(value);
        this.quote = false;
    }

    public float getFloat() {
        return Float.parseFloat(value);
    }

    public void setValue(float value) {
        this.value = String.valueOf(value);
        this.quote = false;
    }

    public boolean hasValue() {
        return !Strings.isNullOrEmpty(value);
    }

    @Override
    public String toString() {
        return String.format("Parameter{name=%s, value=%s}", quote ? "'" + name + "'" : name, quote ? "'" + value + "'" : value);
    }
}
