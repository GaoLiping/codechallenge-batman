package app;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * DTO wrapper class for messages
 */
public class Message {

    private MessageContent message;

    // Field for storing a timestamp for when the message was created
    private String stamp;

    public Message(String content) {
        message = new MessageContent(content);
    }

    public MessageContent getMessage() {
        return message;
    }

    @JsonIgnore
    public String getStamp() {
        return stamp;
    }

    public void setStamp(String ts) {
        this.stamp = ts;
    }
}
