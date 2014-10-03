package hello

import grails.persistence.Entity

@Entity
class AssignedTask {

    Date assignedDate
    CompletedTask completedTask
    Task task
    Float amount
    Person assignedTo
    Date dueDate
    Person assignedBy

    static constraints = {
        assignedDate nullable: false, blank: false
        completedTask nullable: true
        amount nullable: false
        task nullable: false
        assignedTo nullable: false
        dueDate nullable: false
        assignedBy nullable: false
    }


}
