package net.szvoc.callcenter.sip;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.var;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Header {
    private String name;
    private String value;
    private List<String> elements;

    public Header(String name, String... items) {
        this.name = name;
        if (items != null) {
            if (items.length > 1) {
                this.elements = Arrays.asList(items);
            } else {
                this.value = items[0];
            }
        }
    }

    public Header(String name, Collection<String> items) {
        this.name = name;
        if (items != null) {
            if (items.size() > 1) {
                this.elements = new ArrayList<>(items);
            } else {
                this.value = items.iterator().next();
            }
        }
    }

    public String name() {
        return this.name;
    }

    public String[] values() {
        if (this.elements != null) {
            return this.elements.toArray(new String[0]);
        }
        return new String[]{this.value};
    }

    public String value() {
        return value2(null);
    }

    public String value2(String defaultValue) {
        return !CollectionUtils.isEmpty(this.elements) ? this.elements.get(0) :
                Strings.isNotEmpty(this.value) ? this.value : defaultValue;
    }

    public int intValue() {
        return intValue(0);
    }

    public int intValue(int defaultValue) {
        return !CollectionUtils.isEmpty(this.elements) ? Integer.parseInt(this.elements.get(0)) :
                Strings.isNotEmpty(this.value) ? Integer.parseInt(this.value) : defaultValue;
    }

    public long longValue() {
        return longValue(0);
    }

    public long longValue(long defaultValue) {
        return !CollectionUtils.isEmpty(this.elements) ? Long.parseLong(this.elements.get(0)) :
                Strings.isNotEmpty(this.value) ? Long.parseLong(this.value) : defaultValue;
    }

    public int size() {
        return this.elements == null ? 1 : this.elements.size();
    }

    protected void load(String nameAndValue) {
        var index = nameAndValue.indexOf(':');
        if (index > 0) {
            this.name = nameAndValue.substring(0, index).trim();
            this.value = nameAndValue.substring(index + 1).trim();
        } else {
            this.name = nameAndValue;
            this.value = "";
        }
    }

    @Override
    public String toString() {
        return this.name + ": " + this.value;
    }
}
