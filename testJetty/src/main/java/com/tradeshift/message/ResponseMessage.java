package com.tradeshift.message;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by liping on 15/04/15.
 */
public class ResponseMessage {

    private Message message;

    public ResponseMessage(){

    }

    public ResponseMessage(Message message){
        this.message = message;
    }

    public void setMessage(Message message){
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }
}
