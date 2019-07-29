package model

import groovy.transform.EqualsAndHashCode
import util.DateUtil

import java.time.Instant

@EqualsAndHashCode(includeFields=true)
class Reservation {
    int roomNumber
    Instant startTimestamp
    Instant endTimestamp
    String timezone


    @Override
    String toString() {
        return "Reservation{" +
                "roomNumber=" + roomNumber +
                ", startTimeStamp=" + startTimestamp +
                ", endTimestamp=" + endTimestamp +
                '}'
    }

    /**
     * Returns the start timestamp in a human readable format with timezone taken into account.
     * @return the date in a human readable format
     */
    String getStartTime(){
        DateUtil.getTimeBasedOnTimezone(startTimestamp, timezone)
    }

    /**
     * Returns the end timestamp in a human readable format with timezone taken into account.
     * @return the date in a human readable format
     */
    String getEndTime(){
        DateUtil.getTimeBasedOnTimezone(endTimestamp, timezone)
    }
}
