package net.szvoc.xsip.sip.header;

import net.szvoc.xsip.sip.common.Parameter;
import org.springframework.util.CollectionUtils;

import java.util.*;

public abstract class Header {
    private List<String> values = new ArrayList<>();
    private Map<String, Parameter> parameters = new HashMap<>();

    public abstract String getName();

    public boolean containsValue() {
        return !CollectionUtils.isEmpty(values);
    }

    protected String get() {
        return containsValue() ? values.get(0) : null;
    }

    protected Iterator<String> getAll() {
        return values.iterator();
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
