package at.htl.mealcounter.control;


import at.htl.mealcounter.entity.Consumation;
import at.htl.mealcounter.entity.Person;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class InitBean {


    @Inject
    EntityManager em;

    @Inject
    PersonRepository personRepository;

    @Inject
    ConsumationRepository consumationRepository;

    @Transactional
    public void startUp(@Observes StartupEvent startupEvent) {
       // personRepository.readFromCsv();



       // fillWithTestData();
    }



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
