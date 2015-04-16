package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service for crafting message replies.
 */

@Service
public class MessageService {

    @Autowired
    private MessagesDAO messagesDAO;

    /**
     * Store the message response for a given name.
     * @param name the input name to greet
     */
    public Message storeName(String name) {
        Message msg = new Message("Hello "+name);
        messagesDAO.add(msg);

        return msg;
    }

    /**
     * Count the messages stored
     */
    public Integer countMessages() {
        return messagesDAO.countRows();
    }

    /**
     * Get the recently created messages as a map.
     */
    public List<Message> getRecentMessages() {
        return messagesDAO.getRecent(10);
    }

}
