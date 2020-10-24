package net.szvoc.xsip.sip.parser.internal;

import lombok.var;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class TokenFactory {
    private static final Set<Class<? extends Token>> subTypes = new Reflections(TokenFactory.class.getPackage().getName()).getSubTypesOf(Token.class);
    private static Map<TokenType, Constructor<? extends Token>> constructors = new ConcurrentHashMap<>();

    public static Token create(TokenType tokenType, Lexer lexer) {
        Constructor<? extends Token> ctor = constructors.computeIfAbsent(tokenType, k -> {
            var type = subTypes.stream()
                    .filter(c -> {
                        var annotation = c.getAnnotation(BindingType.class);
                        return (annotation != null && annotation.value() == k);
                    })
                    .findAny()
                    .orElse(null);
            if (type == null) {
                return null;
            }
            try {
                var constructor = type.getDeclaredConstructor(Lexer.class);
                constructor.setAccessible(true);
                return constructor;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        if (ctor == null) {
            throw new TypeNotPresentException("TokenType." + tokenType.name() + "'s binding type is missing.", null);
        }
        try {
            return ctor.newInstance(lexer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
