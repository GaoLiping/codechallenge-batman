package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Message REST resource.
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
    public Message sayStaticHello() {
        return new Message("static");
    }


    /**
     * Store a greeting message for the name received as a parameter.
     * Also output the greeting message.
     */
    @POST
    @Path("/names/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Message sayHello(@PathParam("name") String name) {
        return messageService.storeName(name);
    }

    /**
     * Get a count of the number of messages in the system
     */
    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getCount() {
        return messageService.countMessages();
    }

    /**
     * Get a static test string, for test purposes.
     */
    @GET
    @Path("/recent")
    @Produces(MediaType.APPLICATION_JSON)
    public Messages getRecentMessages() {
        List<Message> messages = messageService.getRecentMessages();
        return new Messages(messages);
    }

}
