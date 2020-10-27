package net.szvoc.xsip.sip.common;

import java.util.*;

public class Parametric<T> {
    private Map<String, Parameter> parameters = new HashMap<>();

    public Parameter getParameter(String name) {
        return parameters.getOrDefault(name, null);
    }

    public void setParameters(Iterable<Parameter> parameters) {
        parameters.forEach(this::setParameter);
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

    public String getParameterValue(String name, String defaultValue) {
        Parameter parameter = getParameter(name);
        return parameter == null ? defaultValue : parameter.getString();
    }

    public int getParameterValue(String name, int defaultValue) {
        Parameter parameter = getParameter(name);
        return parameter == null ? defaultValue : parameter.getInt32();
    }

    public long getParameterValue(String name, long defaultValue) {
        Parameter parameter = getParameter(name);
        return parameter == null ? defaultValue : parameter.getInt64();
    }

    public float getParameterValue(String name, float defaultValue) {
        Parameter parameter = getParameter(name);
        return parameter == null ? defaultValue : parameter.getFloat();
    }
}
