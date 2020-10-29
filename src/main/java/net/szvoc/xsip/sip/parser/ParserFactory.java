package net.szvoc.xsip.sip.parser;

import net.szvoc.xsip.sip.parser.annotation.BindingHeader;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class ParserFactory {
    private static final Set<Class<? extends Parser>> subTypes = new Reflections(ParserFactory.class.getPackage().getName()).getSubTypesOf(Parser.class);
    private static Map<String, Constructor<? extends Parser>> constructors = new ConcurrentHashMap<>();

    public static Parser create(String headerName) {
        Constructor<? extends Parser> ctor = constructors.computeIfAbsent(headerName, k -> {
            Class<? extends Parser> type = subTypes.stream()
                    .filter(c -> {
                        BindingHeader annotation = c.getAnnotation(BindingHeader.class);
                        return annotation != null && Arrays.stream(annotation.value()).anyMatch(p -> p.equalsIgnoreCase(k));
                    })
                    .findAny()
                    .orElse(null);
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
            throw new TypeNotPresentException("HeaderName." + headerName + "'s binding type is missing.", null);
        }
        try {
            return ctor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
