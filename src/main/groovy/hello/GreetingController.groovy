package hello

import org.springframework.web.bind.annotation.*
import org.springframework.http.*
import static org.springframework.web.bind.annotation.RequestMethod.*

@RestController
class GreetingController {

    @RequestMapping(value = "/person/greet", method = GET)
    String greet(String firstName) {
        def p = Person.findByFirstName(firstName)
        return p ? "Hello ${p.firstName}!" : "Person not found"
    }

    @RequestMapping(value = '/person/add', method = POST)
    ResponseEntity addPerson(String firstName, String lastName) {
        Person.withTransaction {
            def p = new Person(firstName: firstName, lastName: lastName).save()
            if (p) {
                return new ResponseEntity(HttpStatus.CREATED)
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST)
            }
        }
    }

    @RequestMapping(value = "/task/display", method = GET)
    String displayTask(Long taskId) {
        def p = Task.findById(taskId)
        return p ? "Hello ${p.description}!" : "Person not found"
    }


    @RequestMapping(value = '/task/add', method = POST)
    ResponseEntity addTask(String name, String description, String amount) {
        Person.withTransaction {
            def p = new Task(name: name, description: description, amount: Float.valueOf(amount)).save()
            if (p) {
                return new ResponseEntity(HttpStatus.CREATED)
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST)
            }
        }
    }

    @RequestMapping(value = '/task/complete', method = POST)
    ResponseEntity completeTask(String taskId, String personId, String amount) {
        Person.withTransaction {
            Person person = Person.findById(personId)
            Task task = Task.findById(taskId)
            def p = new CompletedTask(completedBy: person, task: task, amount: Float.valueOf(amount), completedDate: new Date(), lastUpdated: new Date()).save()
            if (p) {
                return new ResponseEntity(HttpStatus.CREATED)
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST)
            }
        }
    }

}