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
    public Message countMessages() {
        int count = messagesDAO.countRows();
        Message msg = new Message(String.format("%d rows found", count));
        return msg;
    }

    /**
     * Get the recently created messages as a map.
     */
    public Map<String, Object> getRecentMessages() {
        List<Message> messages = messagesDAO.getRecent(10);
        Map<String, Object> output = new HashMap<>();
        output.put("messageCount", messages.size());
        if (messages.size() > 0) {
            output.put("lastMessage", messages.get(0).getStamp());
            output.put("messages", messages);
        }
        return output;
    }

}
