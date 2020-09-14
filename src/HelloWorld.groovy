import org.apache.groovy.jaxb.extensions.JaxbExtensions

class HelloWorld {
    static void main(String[] args) {
        /*       println "Hello World"

               int age = 40

               println(age)

               println(age.getClass())

               def name = "John"
               println name
               println name.getClass()*/

        Person johnDoe = new Person()
        johnDoe.setFirstName("John")
        johnDoe.setLastName("Doe")
        johnDoe.setAge(40)

        println(johnDoe.getFullName())
        println(johnDoe.getAge())

        if (johnDoe.getAge() >= 45 && johnDoe <= 65) {
            println(johnDoe.getFullName() + " is middle-aged")
        } else {
            println(johnDoe.getFullName() + " is " + johnDoe.getAge() + " years old")
        }

        def persons = [johnDoe, new Person(lastName: "Hill", age: 40, firstName: "Mary")]

        for (i in 0..<persons.size()) {
            println(persons.get(i).getFullName())
        }

        for (Person p : persons) {
            println(p.getFullName())
        }
    }
}
