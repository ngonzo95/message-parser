package util

import enums.TimeOfDayEnumeration

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

import static enums.TimeOfDayEnumeration.*

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

    /**
     * Helper method for getting the time of day
     * @param instant The time you are checking
     * @param timezone The timezone you are in
     * @return An enum giving the time of day for that time and timezone
     */
    static TimeOfDayEnumeration getTimeOfDayEnum(Instant instant, String timezone){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH").
                withZone(ZoneId.of(timezone))
        int hourOfDay = Integer.parseInt(dateTimeFormatter.format(instant))

        if (hourOfDay < 4){
            return EVENING
        }
        else if (hourOfDay < 11){
            return MORNING
        }
        else if (hourOfDay < 17){
            return AFTERNOON
        } else {
            EVENING
        }
    }
}
