package net.szvoc.callcenter.sip.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.var;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HeaderSpec {
    private String rawString;

    private List<Header> headers = new ArrayList<>();

    public Header header(String name) {
        return this.headers.stream()
                .filter(c -> c.name().equalsIgnoreCase(name))
                .findAny()
                .orElse(null);
    }

    public List<Header> headers(String name) {
        return this.headers.stream()
                .filter(c -> c.name().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Header> headers() {
        return this.headers;
    }

    public static HeaderSpec parse(String rawString) {
        var spec = new HeaderSpec();
        spec.rawString = rawString;
        Arrays.stream(rawString.split("\r\n"))
                .forEach(c -> {
                    var header = HeaderFactory.create(c);
                    spec.headers.add(header);
                });
        return spec;
    }

    @Override
    public String toString() {
        if (rawString == null) {
            rawString = headers.stream()
                    .map(Header::toString)
                    .collect(Collectors.joining("\r\n"));
        }
        return rawString;
    }

    public static class Builder {

        private HeaderSpec spec = new HeaderSpec();

        public Builder header(Header value) {
            if (value != null) {
                spec.headers.add(value);
            }
            return this;
        }

        public Builder header(Collection<Header> values) {
            if (!CollectionUtils.isEmpty(values)) {
                spec.headers.addAll(values);
            }
            return this;
        }

        public HeaderSpec build() {
            return spec;
        }
    }
}
