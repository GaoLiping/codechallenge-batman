package app;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;

/**
 * DTO wrapper class for messages
 */
public class Message {

    private MessageContent message;

    // Field for storing a timestamp for when the message was created
    private Date stamp;

    public Message(String content) {
        message = new MessageContent(content);
    }

    public MessageContent getMessage() {
        return message;
    }

    @JsonIgnore
    public Date getStamp() {
        return stamp;
    }

    public void setStamp(Date ts) {
        this.stamp = ts;
    }
}
