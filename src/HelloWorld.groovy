import groovy.io.FileType

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

        Person maryHill = new Person("Mary", "Hill", 30)
        Person thomasMarks = new Person("Thomas", "Marks", 21)

        def range = 2..4
        println range.size()
        println range instanceof List

        def allPersons = [johnDoe, maryHill, thomasMarks]
        println allPersons instanceof List
        List personList = allPersons

        println allPersons.size()
        println allPersons[1]

        allPersons.each { println it }

        allPersons.each { Person it -> println it }

        allPersons.each { Person p -> println p }

        allPersons.eachWithIndex { Person entry, int i -> println i + ": " + entry }

        println allPersons.find { it.getLastName() == "Hill" }
        allPersons.sort { it.getAge() }.each { println it }
        allPersons.collect { it.getAge() <= 30 }.each { println it }

        Map<String, String> m = [:]
        m.put("1","2")
        println m.size()

        ArrayList <String> arrayList = []
        arrayList.add("RRRR")
        println arrayList.size()

        arrayList << "1"
        println arrayList



        File file = new File("resources/john-doe.txt")
        println file.getText("UTF-8")

        file.eachLine { it, no ->
            if (no == 1) {
                johnDoe.setFirstName(it)
            } else if (no == 2) {
                johnDoe.setLastName(it)
            } else if (no == 3) {
                johnDoe.setAge(it.toInteger())
            } else {
                throw new RuntimeException("File should have only 3 lines")
            }
        }

        def actualCount = 0
        file.withReader { reader ->
            while (reader.readLine()) {
                actualCount++
            }
        }
        println actualCount

        def outputPath = 'resources/ioOut.txt'
        def reader = new File('resources/ioInput.txt').newReader()
        new File(outputPath).append(reader)
        reader.close()


        byte[] data = []
        new File("resources/binaryExample.jpg").withInputStream { stream ->
            data = stream.getBytes()
        }

        outputPath = 'resources/binaryOut.jpg'
        def is = new File('resources/binaryExample.jpg').newInputStream()
        new File(outputPath).append(is)
        is.close()


        List<String> actualList = new File('resources/ioInput.txt').collect { it }

        String[] actualArray = new File('resources/ioInput.txt') as String[]

        String actualString = new File('resources/ioInput.txt').text

        byte[] contents = new File('resources/binaryExample.jpg').bytes


        File textFile = new File("resources/mary-hill.txt")
        textFile.withWriter("UTF-8") { writer ->
            writer.writeLine(maryHill.getFirstName())
            writer.write(maryHill.getLastName())
            writer.writeLine(maryHill.getAge().toString())
        }

        textFile.append(1)
        textFile.append(2)
        textFile << 3
        textFile.withWriterAppend("UTF-8") { out -> out.append("text") }
        textFile.withWriterAppend("UTF-8") { out -> out.println("text2") }
        textFile.withWriterAppend("UTF-8") { out -> out.println("text3") }

        def ln = System.getProperty('line.separator')
        textFile.append(ln + "Separate line")


        String message = 'This is a serialized string'
        int length = message.length()
        boolean valid = true

        new File('resources/ioData.txt').withDataOutputStream { out ->
            out.writeUTF(message)
            out.writeInt(length)
            out.writeBoolean(valid)
        }

        String loadedMessage = ""
        int loadedLength
        boolean loadedValid

        new File('resources/ioData.txt').withDataInputStream { inputStream ->
            loadedMessage = inputStream.readUTF()
            loadedLength = inputStream.readInt()
            loadedValid = inputStream.readBoolean()
        }


        // Person thomasMarks = new Person("Thomas", "Marks", 21)
        File binFile = new File("resources/thomas-marks.bin")
        binFile.withObjectOutputStream { out ->
            out.writeObject(thomasMarks)
        }

        new File('resources/thomas-marks.bin').withObjectInputStream { inputStream ->
            thomasMarks = inputStream.readObject()
        }

        //only list the contents of the top-level directory
        new File('resources').eachFile { fileInside ->
            println fileInside.name
        }
        //only list the contents of the top-level directory
        //only the files that start with “io” and end in “.txt”:

        new File('resources').eachFileMatch(~/io.*\.txt/) { fileInside ->
            println fileInside.name
        }

        //only list the contents of the top-level directory
        //The options are ANY, FILES, and DIRECTORIES.
        new File('resources').eachFileMatch(FileType.FILES, ~/io.*\.txt/) { fileInside ->
            println fileInside.name
        }

        //list all the contents of the top-level directory
        //working with only directories. We can use eachDir and eachFile with a FileType of DIRECTORIES.

        new File('src').eachFileRecurse(FileType.DIRECTORIES) { fileInside ->
            println "$fileInside.parent $fileInside.name"
        }

        new File('src').eachDirRecurse { dir ->
            println "$dir.parent $dir.name"
        }

        //provides the ability to return FileVisitResult objects to control the processing.
        // use traverse on our src/main directory and skip processing the tree under the groovy directory:

        new File('src').traverse { fileInside ->
            if (fileInside.directory && fileInside.name == 'groovy') {
                FileVisitResult.SKIP_SUBTREE
            } else {
                println "$fileInside.parent - $fileInside.name"
            }
        }


/*        List<File> fileList = []

        new File('exercise').eachFileRecurse(FileType.FILES) { fileInside ->
            fileList.add(fileInside)
        }
        println fileList.size()*/

        List<Double> numbersList = []

        new File('exercise').eachFileRecurse(FileType.FILES) { fileInside ->
            fileInside.eachLine {
                if (it.isNumber()) {
                    double d = it.toDouble()
                    numbersList.add(d)
                    println d
                }
            }
        }
        println numbersList.size()

        println "max = " + numbersList.max()
        println "sum = " + numbersList.sum()


    }

    static void handlePerson(Closure closure, Person person) {
        if (person == null) {
            throw new RuntimeException("A person instance is null")
        }
        closure(person)
    }
}
