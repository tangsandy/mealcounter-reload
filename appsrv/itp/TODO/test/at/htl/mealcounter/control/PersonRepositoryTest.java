package at.htl.mealcounter.control;

import at.htl.mealcounter.entity.Person;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import javax.sql.DataSource;

import java.util.List;

import static org.assertj.db.output.Outputs.output;


@QuarkusTest
class PersonRepositoryTest {

    @Inject
    PersonRepository personRepository;

    private static DataSource dataSource;

    @BeforeAll
    static void Init() {
        dataSource = DatabaseHelper.getDatasource();
    }

    @Test
    @Order(1)
    void save() {

        Person person =  personRepository.findById(Long.valueOf(99));
        personRepository.persist(person);

        Table table = new Table(dataSource, DatabaseHelper.PERSON_TABLE);
        output(table).toConsole();

        Assertions.assertThat(table).row(2)
                .value("FIRST_NAME").isEqualTo(person.getFirstName())
                .value("LAST_NAME").isEqualTo(person.getLastName())
                .value("ENTRY_YEAR").isEqualTo(person.getentryYear());
    }


    @Test
    @Order(2)
    void delete() {

        Person person =  personRepository.findById(Long.valueOf(99));
        personRepository.persist(person);

        Table table = new Table(dataSource, DatabaseHelper.PERSON_TABLE);
        output(table).toConsole();

        int rowsBefore = table.getRowsList().size();
        personRepository.delete(person);
        table = new Table(dataSource, DatabaseHelper.PERSON_TABLE);
        output(table).toConsole();
        int rowsAfter = table.getRowsList().size();

        org.assertj.core.api.Assertions.assertThat(rowsBefore-1).isEqualTo(rowsAfter);

    }

    @Test
    @Order(4)
    void findAll() {


        int findAllRows = personRepository.listAll().size();

        Table table = new Table(dataSource, DatabaseHelper.PERSON_TABLE);


        int tableRows = table.getRowsList().size();
        output(table).toConsole();

        org.assertj.core.api.Assertions.assertThat(findAllRows).isEqualTo(tableRows);
    }

    @Test
    @Order(5)
    void findById() {

        Table table = new Table(dataSource, DatabaseHelper.PERSON_TABLE);

        Person person = personRepository.findById(Long.valueOf(2));


        Assertions.assertThat(table).row((int) (person.getId()-1));

    }

//    @Test
//    @Order(5)
//    void findByNfcId() {
//
//        Table table = new Table(dataSource, DatabaseHelper.PERSON_TABLE);
//
//        Person person = personRepository.findByNfcId("")
//
//
//        Assertions.assertThat(table).row((int) (person.getId()-1))
//                .value("FIRST_NAME").isEqualTo(person.getFirstName())
//                .value("LAST_NAME").isEqualTo(person.getLastName())
//                .value("ENTRY_YEAR").isEqualTo(person.getentryYear());
//
//    }

    @Test
    @Order(6)
    void findByEntryYear() {

        Table table = new Table(dataSource, DatabaseHelper.PERSON_TABLE);

        int personRepositoryByEntryYear = personRepository.findByEntryYear(2016).size();


        org.assertj.core.api.Assertions.assertThat(personRepositoryByEntryYear).isEqualTo(18);
    }


}
