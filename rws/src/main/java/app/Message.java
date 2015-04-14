package app;

import java.util.HashMap;
import java.util.Map;

/**
 * DTO Class for messages
 */
public class Message {

    private Map<String, String> message;

    public Message(String content) {
        message = new HashMap<>();
        message.put("content", content);
    }

    public Map<String, String> getMessage() {
        return message;
    }
}
