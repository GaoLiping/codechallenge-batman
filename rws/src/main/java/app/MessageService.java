package app;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by svi on 10/04/15.
 */

@Service
public class MessageService {

    public MessageService() {
    }

    public Map<String, Message> getMessage(String name) {
        Map<String, Message> out = new HashMap<>();
        out.put("message", new Message("Hello "+name));
        return out;
    }

}
