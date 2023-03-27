package com.budzko.identity.utils;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
    public static final ZoneId UTC_TIME_ZONE_ID = ZoneId.of("UTC");
    public static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone(UTC_TIME_ZONE_ID);

    /**
     * Local date time based on {@link DateUtils#UTC_TIME_ZONE} timezone
     */
    public static LocalDateTime now() {
        Clock clock = Clock.system(UTC_TIME_ZONE_ID);
        return LocalDateTime.now(clock);
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
    }
}
