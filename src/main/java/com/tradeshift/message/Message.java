package com.tradeshift.message;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * Created by liping on 15/04/15.
 */
public class Message {
    private String content;
    private Date    timestamp;
    public Message(){

    }

    public Message(String userName){
        this.content = "Hello " + userName;
    }
    public void setContent(String content){
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @JsonIgnore
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /*
    @PostConstruct
    public void init(){
        System.out.println("message 在初始化！");
    }
    */
}