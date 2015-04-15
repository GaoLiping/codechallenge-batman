package app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * DTO Class for a list of messages
 */
public class Messages {

    private Integer messageCount;
    private String lastMessage;
    private List<Message> messages;

    public Messages(List<Message> messages) {
        this.messages = messages;
        this.messageCount = messages.size();
        if (messages.size() > 0) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            this.lastMessage = df.format(messages.get(0).getStamp());
        }
    }

    public Integer getMessageCount() {
        return messageCount;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
