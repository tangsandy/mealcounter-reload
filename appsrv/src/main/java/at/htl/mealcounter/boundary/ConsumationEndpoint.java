package at.htl.mealcounter.boundary;


import at.htl.mealcounter.control.ConsumationRepository;
import at.htl.mealcounter.entity.Consumation;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@RequestScoped
@Path("/api/consumation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConsumationEndpoint {

    @Inject
    ConsumationRepository consumationRepository;

    @GET
    public Response findAll() {
        List<Consumation> consumationList = consumationRepository.findAll().list();
        for (int i = 0; i < consumationList.size(); i++) {
            System.out.println(consumationList.get(i).toString());
        }
        return Response.ok().build();
    }


    @POST
    public Response create(Consumation consumation, @Context UriInfo info) {
        consumationRepository.persist(consumation);
        return Response.created(URI.create(info.getPath() + "/"+ consumation.getId())).build();
    }

}
