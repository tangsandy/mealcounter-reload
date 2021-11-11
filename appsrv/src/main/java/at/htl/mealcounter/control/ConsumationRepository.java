package at.htl.mealcounter.control;

import at.htl.mealcounter.entity.Consumation;
import at.htl.mealcounter.entity.NfcCard;
import at.htl.mealcounter.entity.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;


@ApplicationScoped
public class ConsumationRepository implements PanacheRepository<Consumation> {

    @Inject
    EntityManager em;

    @Inject
    PersonRepository personRepository;


    public Consumation findByDateAndPerson(LocalDate myDate, Person person) {

        Consumation consumation;

        try {
            consumation = em.createQuery("select c from Consumation c where " +
                    "c.date = :DATE AND " +
                    "c.person.id = :ID", Consumation.class)
                    .setParameter("DATE", myDate)
                    .setParameter("ID", person.getId())
                    .getSingleResult();

        }catch (NoResultException e){
            consumation = null;
        }

        return consumation;
    }


    @Transactional
    public void readFromCsv() {
        URL url = Thread.currentThread().getContextClassLoader().getResource("consumations.csv");
        try (Stream<String> stream = Files.lines(Paths.get(url.getPath()), StandardCharsets.UTF_8)) {
            stream.skip(1)
                    .map(c -> c.split(";"))
                    .map(c -> new Consumation(
                            personRepository.findById(Long.parseLong(c[2])),
                            LocalDate.parse(c[0]),
                            Boolean.parseBoolean(c[1])))
                    .peek(out::println)
                    .forEach(em::merge);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void fillWithTestData() {


        for (int i = 1; i <= personRepository.findAll().list().size(); i++) {


            for (int j = 1; j <= 12; j++) {

                YearMonth yearMonthObject = YearMonth.of(2020, j);
                int daysInMonth = yearMonthObject.lengthOfMonth();

                for (int k = 1; k <= daysInMonth; k++) {

                    String month = String.valueOf(j);
                    String day= String.valueOf(k);
                    String date = "";
                    boolean consumed = true;
                    int zahl = (int)((Math.random()) * 2 + 1);

                    if(j < 10){
                        month = "0"+j;
                    }

                    if(k < 10){
                        day = "0"+k;
                    }

                    date = "2020-"+month+"-"+day;
                    consumed = zahl == 1;
                    Consumation consumation = new Consumation(personRepository.findById((long)i),LocalDate.parse(date),consumed);

                    em.merge(consumation);
                }

            }



        }

    }





}
