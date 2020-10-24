package net.szvoc.xsip.sip.common;

import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;

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
        this.value = value;
        this.quote = true;
    }

    public int getInteger() {
        return Integer.parseInt(value);
    }

    public void setValue(int value) {
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
