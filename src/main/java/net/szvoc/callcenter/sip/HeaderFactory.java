package net.szvoc.callcenter.sip;

import lombok.var;
import net.szvoc.callcenter.sip.annotation.SipHeader;
import net.szvoc.callcenter.sip.header.UnknownHeader;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class HeaderFactory {

    private static final Set<Class<? extends Header>> subTypes = new Reflections(HeaderFactory.class.getPackage().getName()).getSubTypesOf(Header.class);
    private static Map<String, Constructor<? extends Header>> constructors = new ConcurrentHashMap<>();

    public static Header create(String nameAndValue) {
        var index = nameAndValue.indexOf(':');
        var name = index > 0 ? nameAndValue.substring(0, index).trim() : nameAndValue.trim();
        Constructor<? extends Header> ctor = constructors.computeIfAbsent(name, k -> {
            var type = subTypes.stream()
                    .filter(c -> {
                        var annotation = c.getAnnotation(SipHeader.class);
                        if (annotation == null || annotation.value().length == 0) {
                            return false;
                        }
                        return Arrays.stream(annotation.value()).anyMatch(v -> v.equalsIgnoreCase(k));
                    })
                    .findAny()
                    .orElse(UnknownHeader.class);
            try {
                var constructor = type.getDeclaredConstructor();
                constructor.setAccessible(true);
                return constructor;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        try {
            Header instance = ctor.newInstance();
            instance.load(nameAndValue);
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
