package at.htl.mealcounter.boundary;


import at.htl.mealcounter.control.PersonRepository;
import at.htl.mealcounter.entity.NfcCard;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


@RequestScoped
@Path("/person")
public class PersonEndpoint {


    @Inject
    PersonRepository personRepository;


    @GET
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(personRepository.findAll()).build();
    }


    @POST
    @Path("/nfc")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response nfcCardDeteteced(NfcCard data, @Context UriInfo info) {
        System.out.println("Data" + data);
        return Response.ok().build(); //(URI.create(info.getPath() + "/"+ data.nfcId)).build();
        // statt Response.ok(), sollte dann überprüft werden ob essen scho gegessen wurde:
        //    - wenn ja, rotes licht für raspberry pi
        //    - wenn nein, grünes licht für raspberry pi

    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") long id) {
        return Response.ok( personRepository.findById(id)).build();

    }



    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            personRepository.deleteById(id);
            return Response
                    .noContent()
                    .build();
        } catch (IllegalArgumentException e) {
            return Response
                    .status(400)
                    .header("Reason","Person with id" + id + "does not exist")
                    .build();
        }
    }

    @GET
    @Path("importPersons")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response importPersons() {

        personRepository.readFromCsv();

       return Response.ok().build();
    }



}
