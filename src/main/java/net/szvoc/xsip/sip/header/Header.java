package net.szvoc.xsip.sip.header;

import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Header<T> {
    private List<T> values = new ArrayList<>();

    @Getter
    private String name;

    public Header(String name) {
        this.name = name;
    }

    public boolean containsValue() {
        return !CollectionUtils.isEmpty(values);
    }

    public T get() {
        return containsValue() ? values.get(0) : null;
    }

    public Iterator<T> iterator() {
        return values.iterator();
    }

    public void add(T value) {
        values.add(value);
    }

    public void clear() {
        values.clear();
    }

    public void set(T value) {
        clear();
        add(value);
    }
}
