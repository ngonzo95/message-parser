package util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class DateUtil {
    /**
     * Helper method for formatting dates
     * @param instant the time you want to display
     * @param timezone the timezone you are in
     * @return date formatted as hh:mm a MM/dd/yy
     */
    static String getTimeBasedOnTimezone(Instant instant, String timezone){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a MM/dd/yy").
                withZone(ZoneId.of(timezone))
        dateTimeFormatter.format(instant)
    }
}
