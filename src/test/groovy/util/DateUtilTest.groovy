package util

import Enums.TimeOfDayEnumeration
import org.junit.Test

import java.time.Instant

class DateUtilTest {

    @Test
    void whenGetTimeBasedOnTimezoneReturnsTheDateInTheCorrectTimeZoneAM() {
        String result = DateUtil.getTimeBasedOnTimezone(Instant.ofEpochSecond(1486654792),
                "US/Pacific")

        assert result == "07:39 AM 02/09/17"
    }

    @Test
    void whenGetTimeOfDayEnumCalledInEveningReturnsEveningEnum(){
        TimeOfDayEnumeration result = DateUtil.getTimeOfDayEnum(Instant.ofEpochSecond(1564445651),
                "US/Pacific")

        assert result == TimeOfDayEnumeration.EVENING
    }

    @Test
    void whenGetTimeOfDayEnumCalledInAfternoonReturnsAfternoonEnum(){
        TimeOfDayEnumeration result = DateUtil.getTimeOfDayEnum(Instant.ofEpochSecond(1564441051),
                "US/Pacific")

        assert result == TimeOfDayEnumeration.AFTERNOON
    }

    @Test
    void whenGetTimeOfDayEnumCalledInMorningReturnsMorningEnum(){
        TimeOfDayEnumeration result = DateUtil.getTimeOfDayEnum(Instant.ofEpochSecond(1560604541),
                "US/Pacific")

        assert result == TimeOfDayEnumeration.MORNING
    }

    @Test
    void whenGetTimeOfDayEnumCalledNearMidnightReturnsEveningEnum(){
        TimeOfDayEnumeration result = DateUtil.getTimeOfDayEnum(Instant.ofEpochSecond(1560585601),
                "US/Pacific")

        assert result == TimeOfDayEnumeration.EVENING
    }
}
