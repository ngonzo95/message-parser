package model

import groovy.transform.EqualsAndHashCode

import java.time.Instant

@EqualsAndHashCode(includeFields=true)
class Reservation {
    int roomNumber
    Instant startTimestamp
    Instant endTimestamp


    @Override
    String toString() {
        return "Reservation{" +
                "roomNumber=" + roomNumber +
                ", startTimeStamp=" + startTimestamp +
                ", endTimestamp=" + endTimestamp +
                '}'
    }
}
