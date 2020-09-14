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

        Person person = new Person()
        person.setFirstName("John")
        person.setLastName("Doe")
        person.setAge(40)

        println(person.getFullName())
        println(person.getAge())
    }
}
