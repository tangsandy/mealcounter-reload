package at.htl.mealcounter.control;


import at.htl.mealcounter.entity.NfcCard;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class InitBean {


    @Inject
    EntityManager em;

    @Inject
    PersonRepository personRepository;

    @Inject
    ConsumationRepository consumationRepository;

    @Inject
    NfcRepository nfcRepository;

    @Transactional
    public void startUp(@Observes StartupEvent startupEvent) {

        NfcCard nfcCard1 = new NfcCard("abc");
        nfcRepository.persist(nfcCard1);
    }

}
