package model

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includeFields=true)
class Company {
    int id
    String company
    String city
    String timezone

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", city='" + city + '\'' +
                ", timezone='" + timezone + '\'' +
                '}';
    }
}
