package net.szvoc.xsip.sip.header;

import net.szvoc.xsip.sip.common.Parameter;
import org.springframework.util.CollectionUtils;

import java.util.*;

public abstract class Header<T> {
    private List<T> values = new ArrayList<>();
    private Map<String, Parameter> parameters = new HashMap<>();

    public abstract String getName();

    public boolean containsValue() {
        return !CollectionUtils.isEmpty(values);
    }

    public T get() {
        return containsValue() ? values.get(0) : null;
    }

    public Iterator<T> getAll() {
        return values.iterator();
    }

    public void add(T value) {
        values.add(value);
    }

    public Parameter getParameter(String name) {
        return parameters.getOrDefault(name, null);
    }

    public void setParameter(String name, String value) {
        Parameter parameter = new Parameter();
        parameter.setName(name);
        parameter.setValue(value);
        parameters.put(name, parameter);
    }

    public void setParameter(String name, int value) {
        Parameter parameter = new Parameter();
        parameter.setName(name);
        parameter.setValue(value);
        parameters.put(name, parameter);
    }

    public void setParameter(String name, float value) {
        Parameter parameter = new Parameter();
        parameter.setName(name);
        parameter.setValue(value);
        parameters.put(name, parameter);
    }

    public Iterator<Parameter> getParameters() {
        return parameters.values().iterator();
    }

    public void removeParameter(String name) {
        parameters.remove(name);
    }
}
