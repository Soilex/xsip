package net.szvoc.xsip.sip.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.DayOfWeek;
import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Weekday {
    MONDAY("Mon", DayOfWeek.MONDAY),
    TUESDAY("Tue", DayOfWeek.TUESDAY),
    WEDNESDAY("Wed", DayOfWeek.WEDNESDAY),
    THURSDAY("Thu", DayOfWeek.THURSDAY),
    FRIDAY("Fri", DayOfWeek.FRIDAY),
    SATURDAY("Sat", DayOfWeek.SATURDAY),
    SUNDAY("Sun", DayOfWeek.SUNDAY)
    ;

    /**
     * 缩写
     */
    private String abbr;

    private DayOfWeek dayOfWeek;

    public static Weekday abbrOf(String abbr) {
        return Arrays.stream(Weekday.values())
                .filter(c -> c.getAbbr().equalsIgnoreCase(abbr))
                .findAny()
                .orElseThrow(() -> new EnumConstantNotPresentException(Weekday.class, abbr));
    }
}
