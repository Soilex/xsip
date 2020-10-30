package net.szvoc.xsip.sip.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EnumEx<T extends Enum<T>> {
    private T value;
    private String origin;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Enum) {
            return value.equals(obj);
        }
        if (obj instanceof EnumEx) {
            EnumEx<?> enumEx = (EnumEx<?>)obj;
            return this.value.equals(enumEx.getValue()) && this.origin.equalsIgnoreCase(enumEx.getOrigin());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return origin.hashCode();
    }

    public boolean equals(T value, String origin) {
        return this.value == value && this.origin.equalsIgnoreCase(origin);
    }
}
