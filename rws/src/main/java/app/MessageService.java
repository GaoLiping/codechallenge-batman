package app;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service for crafting message replies.
 */

@Service
public class MessageService {

    /**
     * Get the message response for a given name.
     * @param name the input name to greet
     */
    public Map<String, Message> getMessage(String name) {
        Map<String, Message> out = new HashMap<>();
        out.put("message", new Message("Hello "+name));
        return out;
    }

}
