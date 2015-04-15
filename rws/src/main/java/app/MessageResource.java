package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
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
    public Message sayStaticHello() {
        return new Message("static string");
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
    public Map<String, Object> getRecentMessages() {
        List<Message> messages = messageService.getRecentMessages();
        Map<String, Object> output = new HashMap<>();
        output.put("messageCount", messages.size());
        if (messages.size() > 0) {
            output.put("lastMessage", messages.get(0).getStamp());
            output.put("messages", messages);
        }
        return output;
    }

}
