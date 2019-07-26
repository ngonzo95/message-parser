package model

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includeFields=true)
class Guest {
    int id
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
}
