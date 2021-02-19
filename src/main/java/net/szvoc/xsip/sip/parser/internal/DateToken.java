package net.szvoc.xsip.sip.parser.internal;

import lombok.Data;
import lombok.ToString;
import net.szvoc.xsip.sip.common.CharacterType;
import net.szvoc.xsip.sip.common.Month;
import net.szvoc.xsip.sip.common.Weekday;
import net.szvoc.xsip.sip.parser.SyntaxException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.function.Consumer;

public class DateToken extends Token<ZonedDateTime> {
    public DateToken(String id, boolean required, Lexer lexer, Consumer<ZonedDateTime> matchHandler) {
        super(id, required, lexer, matchHandler);
    }

    public DateToken(boolean required, Lexer lexer, Consumer<ZonedDateTime> matchHandler) {
        super(required, lexer, matchHandler);
    }

    public DateToken(String id, boolean required, Lexer lexer) {
        super(id, required, lexer);
    }

    public DateToken(boolean required, Lexer lexer) {
        super(required, lexer);
    }

    @Override
    protected boolean doMatch() throws SyntaxException {
        // Sat, 13 Nov 2010 23:29:00 GMT
        DateSpec date = new DateSpec();
        return new ComplexToken(isRequired(), this.lexer, t -> this.setValue(date.toZonedDateTime()))
                // weekday
                .define(new EnumToken<>(Weekday.SUNDAY, true, this.lexer).withComparer((a, b) -> a.getAbbr().equalsIgnoreCase(b)))
                .define(new CharacterToken(true, this.lexer).expect(CharacterType.COMMA))
                // day
                .define(new NumericToken(true, this.lexer, t -> date.setDay(t.intValue())).allowDecimal(false).range(1, 31))
                // month
                .define(new EnumToken<>(Month.January, true, this.lexer, t -> date.setMonth(t.getValue().ordinal() + 1)).withComparer((a, b) -> a.getAbbr().equalsIgnoreCase(b)))
                // year
                .define(new NumericToken(true, this.lexer, t -> date.setYear(t.intValue())).allowDecimal(false).range(1, 9999))
                // hour
                .define(new NumericToken(true, this.lexer, t -> date.setHour(t.intValue())).allowDecimal(false).range(0, 23))
                .define(new CharacterToken(true, this.lexer).expect(CharacterType.COLON))
                // minute
                .define(new NumericToken(true, this.lexer, t -> date.setMinute(t.intValue())).allowDecimal(false).range(0, 59))
                .define(new CharacterToken(true, this.lexer).expect(CharacterType.COLON))
                // second
                .define(new NumericToken(true, this.lexer, t -> date.setSecond(t.intValue())).allowDecimal(false).range(0, 59))
                // GMT
                .define(new WordToken(true, this.lexer, t -> {
                    if (!t.equalsIgnoreCase("GMT")) {
                        this.lexer.throwSyntaxException();
                    }
                }))
                .match();
    }

    @Data
    @ToString
    private static class DateSpec {
        private int year;
        private int month;
        private int day;
        private int hour;
        private int minute;
        private int second;

        public ZonedDateTime toZonedDateTime() {
            return LocalDateTime.of(year, month, day, hour, minute, second).atZone(ZoneId.of("GMT"));
        }
    }
}
