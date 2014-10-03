package hello

import grails.persistence.Entity

@Entity
class CompletedTask {

    Date completedDate
    Task task
    Float amount = 0.00
    Date lastUpdated
    Person completedBy

    static constraints = {
        completedDate nullable: false
        task nullable: false
        amount nullable: false
        completedBy nulalble: false
        lastUpdated nullable: false
    }

}
