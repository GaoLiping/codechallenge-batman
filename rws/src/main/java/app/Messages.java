package app;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * DTO Class for a list of messages
 */

public class Messages {

    private Integer messageCount;
    private Date lastMessage;
    private List<Message> messages;

    public Messages(List<Message> messages) {
        this.messages = messages;
        this.messageCount = messages.size();
        if (messages.size() > 0 && messages.get(0).getStamp() != null) {
            this.lastMessage = messages.get(0).getStamp();
        }
    }

    public Integer getMessageCount() {
        return messageCount;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="UTC")
    public Date getLastMessage() {
        return lastMessage;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
