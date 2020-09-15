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
        johnDoe.setFirstName("Johnny".dropRight(2))
        johnDoe.setLastName("Doe")
        johnDoe.setAge(40)


        try {
            println(johnDoe.getFullName().toLong())
        } catch (Exception e) {
            assert e instanceof NumberFormatException
            println("Cannot print it")
        } finally {
            println johnDoe.getFullName()
        }
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

        def calc = new Calculator()

        println(calc.addition(5d, 6L))
        println(calc.substraction(5, 6))
        println(calc.multiplication(5, 6))
        def div = calc.division(5, 6)
        div != null ? println(div) : println()

        println johnDoe.toString()

        Closure johnPrint = { println johnDoe.toString() }
        johnPrint()

        Closure personPrint = { Person person -> println person.toString() }
        Object resultClosure = personPrint(johnDoe)
        println resultClosure

        handlePerson(personPrint, johnDoe)

        Closure personFullName = { Person person -> println person.getFirstName() + " " + person.getLastName() }

        handlePerson(personFullName, johnDoe)
    }

    static void handlePerson(Closure closure, Person person) {
        if (person == null) {
            throw new RuntimeException("A person instance is null")
        }
        closure(person)
    }
}
