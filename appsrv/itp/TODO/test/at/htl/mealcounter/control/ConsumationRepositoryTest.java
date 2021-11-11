package at.htl.mealcounter.control;

import at.htl.mealcounter.entity.Consumation;
import at.htl.mealcounter.entity.Person;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.api.Assertions;
import org.assertj.db.output.impl.Output;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import static org.assertj.db.api.Assertions.*;


import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.*;
import javax.inject.Inject;
import javax.sql.DataSource;

import java.sql.Date;
import java.time.LocalDate;

@QuarkusTest
class ConsumationRepositoryTest {

    @Inject
    ConsumationRepository consumationRepository;

    @Inject
    PersonRepository personRepository;

    private static DataSource  dataSource;

    @BeforeAll
    static void Init() {
        dataSource = DatabaseHelper.getDatasource();
    }

    @Test
    @Order(1)
    void save() {

        Person person = new Person("Bradford", "Fair",2010);

        Consumation consumation = new Consumation(person, LocalDate.of(2021,1,8),true);

        consumationRepository.persist(consumation);

        Table table = new Table(dataSource, DatabaseHelper.CONSUMATION_TABLE);
        output(table).toConsole();

        Assertions.assertThat(table).row(3)
                .value()
                .value("DATE").isEqualTo(consumation.getDate())
                .value("HASCONSUMED").isEqualTo(consumation.isHasConsumed());
    }

    @Test
    @Order(2)
    void delete() {

        Person person = new Person("Bradford", "Fair",2010);

        Consumation consumation = new Consumation(person, LocalDate.of(2021,1,8),true);

        consumationRepository.persist(consumation);

        Table table = new Table(dataSource, DatabaseHelper.CONSUMATION_TABLE);
        output(table).toConsole();

        int rowsBefore = table.getRowsList().size();
        consumationRepository.delete(consumation);
        table = new Table(dataSource, DatabaseHelper.CONSUMATION_TABLE);
        output(table).toConsole();
        int rowsAfter = table.getRowsList().size();

        org.assertj.core.api.Assertions.assertThat(rowsBefore-1).isEqualTo(rowsAfter);

    }

    @Test
    @Order(3)
    void findAll() {


        int findAllRows = consumationRepository.listAll().size();

        Table table = new Table(dataSource, DatabaseHelper.CONSUMATION_TABLE);


        int tableRows = table.getRowsList().size();
        output(table).toConsole();

        org.assertj.core.api.Assertions.assertThat(findAllRows).isEqualTo(tableRows);
    }

    @Test
    @Order(4)
    void findById() {

        Table table = new Table(dataSource, DatabaseHelper.CONSUMATION_TABLE);

        Consumation consumation = consumationRepository.findById(2L);

        output(table).toConsole();

        Assertions.assertThat(table).row((int) (consumation.getId()-1))
                .value()
                .value("DATE").isEqualTo(consumation.getDate())
                .value("HASCONSUMED").isEqualTo(consumation.isHasConsumed());

    }

    @Test
    @Order(5)
    void findByDateAndPersonTest() {

        LocalDate date = LocalDate.of(2021,1,8);

        Person person =  personRepository.findById(Long.valueOf(99));

        Consumation findConsumation = consumationRepository.findByDateAndPerson(date,person);

        Table table = new Table(dataSource, DatabaseHelper.CONSUMATION_TABLE);

        output(table).toConsole();

        Assertions.assertThat(table).row(0)
                .value()
                .value("DATE").isEqualTo(date)
                .value("PERSON_ID").isEqualTo(99);

    }
}
