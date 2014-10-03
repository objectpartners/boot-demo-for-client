package hello

import grails.persistence.Entity

@Entity
class Task {

    String name
    String description
    Float amount

    static constraints = {

        name blank: false, nullable: false
        description blank: false, nullable: false
        amount nullable: false

    }

}
