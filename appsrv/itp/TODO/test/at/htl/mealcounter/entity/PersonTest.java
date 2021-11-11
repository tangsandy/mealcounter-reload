package at.htl.mealcounter.entity;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class PersonTest {

    @Test
    void t100_createPersoWithoutNfcCard() {
        //Brooke;Bradford
        Person person = new Person("Bradford", "Fair",2010);
        System.out.println(person);
    }

    @Test
    void t010_toString() {
        Person person = new Person("Bradford", "Fair",2010);

        assertThat(person.toString()).isEqualTo("Person{id=null, firstName='Bradford', lastName='Fair', year=2010}");

    }
}
