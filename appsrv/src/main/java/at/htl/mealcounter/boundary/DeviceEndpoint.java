package at.htl.mealcounter.boundary;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@RequestScoped
@Path("/api/device")
public class DeviceEndpoint {

    @POST
    @Path("/device")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiveDate() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        return Response.status(202).entity(date).build();
    }

}
