package app;

import org.springframework.stereotype.Service;

/**
 * Service for crafting message replies.
 */

@Service
public class MessageService {

    /**
     * Get the message response for a given name.
     * @param name the input name to greet
     */
    public Message getMessage(String name) {
        return new Message("Hello "+name);
    }

}
