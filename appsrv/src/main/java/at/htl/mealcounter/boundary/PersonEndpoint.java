package at.htl.mealcounter.boundary;


import at.htl.mealcounter.control.PersonRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/person")
public class PersonEndpoint {


    @Inject
    PersonRepository personRepository;


    @GET
    public Response findAll() {
        return Response.ok(personRepository.findAll()).build();
    }


    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") long id) {
        return Response.ok( personRepository.findById(id)).build();

    }


    @DELETE
    @Path("/{id}")
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


}
