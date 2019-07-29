package model

import org.junit.Test

import java.time.Instant

class ReservationTest {
    @Test
    void whenGetStartTimeCallReturnsTheDateInTheCorrectTimeZoneAM(){
        Reservation reservation = new Reservation(startTimestamp: Instant.ofEpochSecond(1486654792),
                timezone: "US/Pacific")


        assert "$reservation.startTime" == "07:39 AM 02/09/17"

    }

    @Test
    void whenGetStartTimeCallReturnsTheDateInTheCorrectTimeZonePM(){
        Reservation reservation = new Reservation(startTimestamp: Instant.ofEpochSecond(1486654792),
                timezone:  "Europe/London")


        assert "$reservation.startTime" == "03:39 PM 02/09/17"
    }

    @Test
    void whenGetEndTimeCallReturnsTheDateInTheCorrectTimeZoneAM(){
        Reservation reservation = new Reservation(endTimestamp: Instant.ofEpochSecond(1486654792),
                timezone: "US/Pacific")


        assert "$reservation.endTime" == "07:39 AM 02/09/17"

    }

    @Test
    void whenGetEndTimeCallReturnsTheDateInTheCorrectTimeZonePM(){
        Reservation reservation = new Reservation(endTimestamp: Instant.ofEpochSecond(1486654792),
                timezone:  "Europe/London")


        assert "$reservation.endTime" == "03:39 PM 02/09/17"
    }
}
