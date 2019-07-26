package model

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includeFields=true)
class Company {
    long id
    String company
    String city
    String timezone

    @Override
    String toString() {
        return "Company{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", city='" + city + '\'' +
                ", timezone='" + timezone + '\'' +
                '}'
    }
}
