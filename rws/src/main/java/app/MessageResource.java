package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * Created by svi on 10/04/15.
 */

@Component
@Path("/messages")
public class MessageResource extends Application {

    @Autowired
    private MessageService messageService;

    @GET
    @Path("/static")
    @Produces(MediaType.APPLICATION_JSON)
    public String sayStaticHello() {
        return "hello. this is static.";
    }


    @POST
    @Path("/names/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Message> sayHello(@PathParam("name") String name) {
        return messageService.getMessage(name);
    }



}
