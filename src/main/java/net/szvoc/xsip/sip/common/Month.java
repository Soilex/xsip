package net.szvoc.xsip.sip.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Month {
    January("Jan"),
    February("Feb"),
    March("Mar"),
    April("Apr"),
    May("May"),
    June("Jun"),
    July("Jul"),
    August("Aug"),
    September("Sep"),
    October("Oct"),
    November("Nov"),
    December("Dec"),
    ;

    /**
     * 缩写
     */
    private String abbr;

    public static Month abbrOf(String abbr) {
        return Arrays.stream(Month.values())
                .filter(c -> c.getAbbr().equalsIgnoreCase(abbr))
                .findAny()
                .orElseThrow(() -> new EnumConstantNotPresentException(Month.class, abbr));
    }
}
