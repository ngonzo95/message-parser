package model

import groovy.transform.EqualsAndHashCode

import java.time.Instant

@EqualsAndHashCode(includeFields=true)
class Guest implements Parseable{
    long id
    String firstName
    String lastName
    Reservation reservation

    @Override
    String toString() {
        return "Guest{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", reservation=" + reservation +
                '}'
    }


    static Guest parse(Map map){
        Reservation reservation = new Reservation(roomNumber: map.reservation.roomNumber,
                startTimestamp: Instant.ofEpochSecond(map.reservation.startTimestamp as long),
                endTimestamp: Instant.ofEpochSecond(map.reservation.endTimestamp as long))
        new Guest(id: map.id, firstName: map.firstName, lastName: map.lastName, reservation: reservation)
    }
}
