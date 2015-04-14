package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * Message REST resource.
 * Answers on paths:
 *  GET /messages/static replies with a static JSON-string
 *  POST /messages/names/$name replies with a Message object and a welcome message for $name
 */
@Component
@Path("/messages")
public class MessageResource extends Application {

    @Autowired
    private MessageService messageService;


    /**
     * Get a static test string, for test purposes.
     */
    @GET
    @Path("/static")
    @Produces(MediaType.APPLICATION_JSON)
    public String sayStaticHello() {
        return "hello. this is static.";
    }


    /**
     * Post the name received as a parameter as output in a welcome message.
     */
    @POST
    @Path("/names/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Message> sayHello(@PathParam("name") String name) {
        return messageService.getMessage(name);
    }


}
