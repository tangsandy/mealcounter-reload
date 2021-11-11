package at.htl.mealcounter.control;

import at.htl.mealcounter.entity.NfcCard;
import at.htl.mealcounter.entity.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.System.out;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {

    @Inject
    EntityManager em;

    @Transactional
    public void readFromCsv() {

        URL url = Thread.currentThread().getContextClassLoader().getResource("names.csv");
        try (Stream<String> stream = Files.lines(Paths.get(url.getPath()), StandardCharsets.UTF_8)) {
            stream.skip(1)
                    .map(s -> s.split(";"))
                    .map(a -> new Person(
                            a[0],
                            a[1],
                            Integer.parseInt(a[2])))
                    .peek(out::println)
                    .forEach(em::merge);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public Person findByPersonId (Person person) {
        return em.createQuery("select p from Person p where p.id = :id", Person.class)
                .setParameter("id", person.getId()).getSingleResult();
    }

    public List<Person> findByEntryYear(int entryYear){
        return em.createQuery("select p from Person p where p.entryYear = :entryYear",Person.class)
                .setParameter("entryYear",entryYear).getResultList();
    }
}
