import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TupleConstructor

/*@ToString
@EqualsAndHashCode
@TupleConstructor*/
@Canonical
class Person implements Serializable{
    String firstName
    String lastName
    int age

    String getFullName() {
        firstName + " " + lastName
    }
}

