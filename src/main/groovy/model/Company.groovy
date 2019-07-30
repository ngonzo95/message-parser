package model

import groovy.transform.EqualsAndHashCode
import traits.Parseable

@EqualsAndHashCode(includeFields=true)
class Company implements Parseable {
    int id
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

    static Company parse(Map map){
        return new Company(id: map.id, company: map.company,
                city: map.city, timezone: map.timezone)
    }
}
