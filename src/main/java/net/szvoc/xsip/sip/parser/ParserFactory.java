package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.common.EnumEx;
import net.szvoc.xsip.sip.header.HeaderName;
import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class ParserFactory {
    private static final Set<Class<? extends Parser>> subTypes = new Reflections(ParserFactory.class.getPackage().getName()).getSubTypesOf(Parser.class);
    private static Map<HeaderName, Constructor<? extends Parser>> constructors = new ConcurrentHashMap<>();

    private static Class<? extends Parser> retrieveParser(HeaderName headerName) {
        return subTypes.stream()
                .filter(c -> {
                    BindingHeader annotation = c.getAnnotation(BindingHeader.class);
                    return annotation != null && Arrays.stream(annotation.value()).anyMatch(p -> p == headerName);
                })
                .findAny()
                .orElse(null);
    }

    public static Parser create(HeaderName headerName) {
        Constructor<? extends Parser> ctor = constructors.computeIfAbsent(headerName, k -> {
            Class<? extends Parser> type = retrieveParser(headerName);
            if (type == null) {
                return null;
            }
            try {
                Constructor<? extends Parser> constructor = type.getDeclaredConstructor();
                constructor.setAccessible(true);
                return constructor;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        if (ctor == null) {
            throw new TypeNotPresentException("Binding type of HeaderName." + headerName.name() + " is missing.", null);
        }
        try {
            return ctor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
