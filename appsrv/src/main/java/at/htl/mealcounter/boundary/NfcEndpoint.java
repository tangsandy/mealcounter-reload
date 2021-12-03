package at.htl.mealcounter.boundary;

import at.htl.mealcounter.control.ConsumationRepository;
import at.htl.mealcounter.control.NfcRepository;
import at.htl.mealcounter.control.PersonRepository;
import at.htl.mealcounter.entity.Consumation;
import at.htl.mealcounter.entity.NfcCard;
import at.htl.mealcounter.entity.Person;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Path("/nfccard")
public class NfcEndpoint {

    @Inject
    NfcRepository nfcRepository;

    @Inject
    ConsumationRepository consumationRepository;

    @Inject
    PersonRepository personRepository;

    @POST
    @Path("/{cardId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response nfcCardDetected(@PathParam("cardId") String cardId, @Context UriInfo info) {
        System.out.println("Card Id: " + cardId);


        if (nfcRepository.cardExists(cardId)) {

            System.out.println("Karte ist bereits im System");
            System.out.println(nfcRepository.findCardOwner(cardId).toString());
            LocalDate date = LocalDate.now();


            if (consumationRepository.findByDateAndPerson(date, nfcRepository.findCardOwner(cardId)) == null) {
                System.out.println("Die Person hat heute noch nichts konsumiert -> sie bekommt ein Essen");
                Consumation consumation = new Consumation(nfcRepository.findCardOwner(cardId),date,true);
                consumationRepository.persist(consumation);
            } else {
                System.out.println("Die Person hat heute schon ihr Essen bekommen");
            }

        } else {
            nfcRepository.persist(new NfcCard(cardId));

        }

        return Response.ok().build(); //(URI.create(info.getPath() + "/"+ data.nfcId)).build();
        // statt Response.ok(), sollte dann überprüft werden ob essen scho gegessen wurde:
        //    - wenn ja, rotes licht für raspberry pi
        //    - wenn nein, grünes licht für raspberry pi

    }

    @POST
    @Path("assign-card/{cardId}/{personId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response assignCardToPerson(@PathParam("cardId") String cardId,
                                       @PathParam("personId") String personId,
                                       @Context UriInfo info) {
        System.out.println("Card Id: " + cardId);
        System.out.println("Person Id: " + personId);


        if (nfcRepository.cardExists(cardId)) {

            System.out.println("Karte ist bereits im System");

            if(nfcRepository.hasCardOwner(cardId)){
                System.out.println("Karte hat bereits einen Besitzer");
                System.out.println(nfcRepository.findCardOwner(cardId).toString());

            }else{
                System.out.println("Karte wird Person zugewiesen");

                Person person = personRepository.findById(Long.valueOf(personId));
                person.setNfcCard(nfcRepository.findByNfcId(cardId));
                personRepository.getEntityManager().persist(person);
            }



        } else {

            System.out.println("Karte exsistiert nicht -> wird der Datenbank engetragen ");
            System.out.println("Neue Karte wird Person zugewiesen");
            nfcRepository.persist(new NfcCard(cardId));
            Person person = personRepository.findById(Long.valueOf(personId));
            person.setNfcCard(nfcRepository.findByNfcId(cardId));
            personRepository.getEntityManager().persist(person);

        }

        return Response.ok().build(); //(URI.create(info.getPath() + "/"+ data.nfcId)).build();
        // statt Response.ok(), sollte dann überprüft werden ob essen scho gegessen wurde:
        //    - wenn ja, rotes licht für raspberry pi
        //    - wenn nein, grünes licht für raspberry pi

    }



    @GET
    @Path("nfcid/{cardId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findByNfcId(@PathParam("cardId") long id) {
        return Response.ok( nfcRepository.findById(id)).build();

    }


}
