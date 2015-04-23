package com.tradeshift.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import java.util.Date;
import java.util.List;

/**
 * Created by liping on 22/04/15.
 */

@JsonPropertyOrder({
        "messageCount",
        "lastMessage",
        "messages"
})
public class ResponseMessages {
    private int messageCount;


    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd,HH:00", timezone="CET")
    private Date lastMessage;


    private List<ResponseMessage> messages;

    public ResponseMessages(){

    }

    public ResponseMessages(int messageCount, Date lastMessage,
                            List<ResponseMessage> responseMsgs){
        this.messageCount = messageCount;
        this.lastMessage = lastMessage;
        this.messages = responseMsgs;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    public Date getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Date lastMessage) {
        this.lastMessage = lastMessage;
    }

    public List<ResponseMessage> getMessages() {
        return messages;
    }
    public void setMessages(List<ResponseMessage> responseMsgs) {
        this.messages = responseMsgs;
    }


}
