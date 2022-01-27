package at.htl.mealcounter.boundary;

import at.htl.mealcounter.control.ConsumationRepository;
import at.htl.mealcounter.control.NfcRepository;
import at.htl.mealcounter.control.PersonRepository;
import at.htl.mealcounter.entity.NfcCard;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("api/nfccard")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NfcEndpoint {

    @Inject
    NfcRepository nfcRepository;

    @Inject
    ConsumationRepository consumationRepository;

    @Inject
    PersonRepository personRepository;

    @GET
    public Response findAll() {
        return Response.ok(nfcRepository.findAll()).build();
    }


    @POST
    @Path("/nfc")
    public Response nfcCardDetected(NfcCard data, @Context UriInfo info) {
        if (nfcRepository.cardExists(data.nfcId)) {
            System.out.println("card exists");
        } else {
            nfcRepository.persist(data);
        }


        return Response.ok().build(); //(URI.create(info.getPath() + "/"+ data.nfcId)).build();
        // statt Response.ok(), sollte dann überprüft werden ob essen scho gegessen wurde:
        //    - wenn ja, rotes licht für raspberry pi
        //    - wenn nein, grünes licht für raspberry pi

    }

    @GET
    @Path("nfcid/{cardId}")
    public Response findByNfcId(@PathParam("cardId") String id) {
        return Response.ok(nfcRepository.findByNfcId(id)).build();

    }


//
//    @POST
//    @Path("assign-card/{cardId}")
//    public Response nfcCardDetected(@PathParam("cardId") String cardId, @Context UriInfo info) {
//        System.out.println("Card Id: " + cardId);
//
//
//        if (nfcRepository.cardExists(cardId)) {
//
//            System.out.println("Karte ist bereits im System");
//            System.out.println(nfcRepository.findCardOwner(cardId).toString());
//            LocalDate date = LocalDate.now();
//
//
//            if (consumationRepository.findByDateAndPerson(date, nfcRepository.findCardOwner(cardId)) == null) {
//                System.out.println("Die Person hat heute noch nichts konsumiert -> sie bekommt ein Essen");
//                Consumation consumation = new Consumation(nfcRepository.findCardOwner(cardId),date,true);
//                consumationRepository.persist(consumation);
//            } else {
//                System.out.println("Die Person hat heute schon ihr Essen bekommen");
//            }
//
//        } else {
//            nfcRepository.persist(new NfcCard(cardId));
//
//        }
//
//        UriBuilder uriBuilder = info.getBaseUriBuilder()
//                .path("nfccard")
//                .path("nfcid")
//                .path(cardId);
//
//        return Response.created(uriBuilder.build()).build();
//
//        //(URI.create(info.getPath() + "/"+ data.nfcId)).build();
//        // statt Response.ok(), sollte dann überprüft werden ob essen scho gegessen wurde:
//        //    - wenn ja, rotes licht für raspberry pi
//        //    - wenn nein, grünes licht für raspberry pi
//
//    }
//
//    @POST
//    @Path("assign-card/{cardId}/{personId}")
//    public Response assignCardToPerson(@PathParam("cardId") String cardId,
//                                       @PathParam("personId") String personId,
//                                       @Context UriInfo info) {
//        System.out.println("Card Id: " + cardId);
//        System.out.println("Person Id: " + personId);
//
//
//        if (nfcRepository.cardExists(cardId)) {
//
//            System.out.println("Karte ist bereits im System");
//
//            if(nfcRepository.hasCardOwner(cardId)){
//                System.out.println("Karte hat bereits einen Besitzer");
//                System.out.println(nfcRepository.findCardOwner(cardId).toString());
//
//            }else{
//                System.out.println("Karte wird Person zugewiesen");
//
//                Person person = personRepository.findById(Long.valueOf(personId));
//                person.setNfcCard(nfcRepository.findByNfcId(cardId));
//                personRepository.persist(person);
//            }
//
//
//
//        } else {
//
//            System.out.println("Karte exsistiert nicht -> wird der Datenbank eingetragen ");
//            System.out.println("Neue Karte wird Person zugewiesen");
//            nfcRepository.persist(new NfcCard(cardId));
//            Person person = personRepository.findById(Long.valueOf(personId));
//            person.setNfcCard(nfcRepository.findByNfcId(cardId));
//            personRepository.persist(person);
//
//        }
//
//        UriBuilder uriBuilder = info.getBaseUriBuilder()
//                .path("nfccard")
//                .path("nfcid")
//                .path(cardId);
//
//        return Response.created(uriBuilder.build()).build(); //(URI.create(info.getPath() + "/"+ data.nfcId)).build();
//        // statt Response.ok(), sollte dann überprüft werden ob essen scho gegessen wurde:
//    }


}
