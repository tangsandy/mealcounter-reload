package at.htl.mealcounter.boundary;


import at.htl.mealcounter.control.ConsumationRepository;
import at.htl.mealcounter.entity.Consumation;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@RequestScoped
@Path("/consumation")
public class ConsumationEndpoint {

    @Inject
    ConsumationRepository consumationRepository;

    @GET
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {




        List<Consumation> consumationList = consumationRepository.findAll().list();

        for (int i = 0; i < consumationList.size(); i++) {
            System.out.println(consumationList.get(i).toString());
        }

        return Response.ok().build();
    }


//    @POST
//    @Path("/create")
//    @Transactional
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response create(Consumation consumation, @Context UriInfo info) {
//        consumationRepository.persist(consumation);
//        return Response.created(URI.create(info.getPath() + "/"+ consumation.getId())).build();
//    }
//
//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response findById(@PathParam("id") long id) {
//        return Response.ok( consumationRepository.findById(id)).build();
//    }
//
//    @DELETE
//    @Path("{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Transactional
//    public Response delete(@PathParam("id") Long id) {
//        try {
//            consumationRepository.deleteById(id);
//            return Response
//                    .noContent()
//                    .build();
//        } catch (IllegalArgumentException e) {
//            return Response
//                    .status(400)
//                    .header("Reason","Consumation with id" +id  + "does not exist")
//                    .build();
//        }
//    }
//
    @GET
    @Path("/importConsumations")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response importConsumations() {

        consumationRepository.fillWithTestData();

        return Response.ok().build();
    }

}
