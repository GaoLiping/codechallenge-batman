package app;

import java.util.HashMap;
import java.util.Map;

/**
 * DTO wrapper class for messages
 */
public class Message {

    private MessageContent message;

    public Message(String content) {
        message = new MessageContent(content);
    }

    public MessageContent getMessage() {
        return message;
    }
}
