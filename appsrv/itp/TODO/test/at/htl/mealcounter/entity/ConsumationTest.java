package at.htl.mealcounter.entity;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class ConsumationTest {

    @Inject

    @Test
    void t100_createConsumation() {
        Person person = new Person("Bradford", "Fair",2010);

       Consumation consumation = new Consumation(person, LocalDate.of(2021,1,8),true);
       System.out.println(consumation);

    }

    @Test
    void t010_toString() {
        Person person = new Person("Bradford", "Fair",2010);
        System.out.println(person);
        Consumation consumation1 = new Consumation(person, LocalDate.of(2021,1,8),true);

        assertThat(consumation1.toString()).isEqualTo("Consumation{person=Person{id=null, firstName='Bradford', lastName='Fair', year=2010}, date=2021-01-08, hasConsumed=true}");

    }

}
