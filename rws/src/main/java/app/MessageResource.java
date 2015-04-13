package app;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

/**
 * Created by svi on 10/04/15.
 */

@Path("/messages")
public class MessageResource extends Application {

    @GET
    @Path("/static")
    @Produces(MediaType.APPLICATION_JSON)
    public String sayStaticHello() {
        return "hello. this is static.";
    }


    @GET
    @Path("/names/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String sayHello(@PathParam("name") String name) {
        return "hello "+name+" .";
    }



}
