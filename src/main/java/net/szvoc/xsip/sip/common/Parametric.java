package net.szvoc.xsip.sip.common;

import org.springframework.util.CollectionUtils;

import java.util.*;

public class Parametric<T> {
    private Map<String, Parameter> parameters = new HashMap<>();

    public Parameter getParameter(String name) {
        return parameters.getOrDefault(name, null);
    }

    public void setParameter(Parameter parameter) {
        parameters.put(parameter.getName(), parameter);
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
