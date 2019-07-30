package model

import groovy.transform.EqualsAndHashCode
import traits.Parseable

@EqualsAndHashCode(includeFields=true)
class Template implements Parseable{
    long id
    String template

    static Template parse(Map map){
        return new Template(id: map.id, template: map.template)

    }

    @Override
    String toString() {
        return "Template{" +
                "id=" + id +
                ", template='" + template + '\'' +
                '}'
    }
}
