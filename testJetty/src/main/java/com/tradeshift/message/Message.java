package com.tradeshift.message;

import javax.annotation.PostConstruct;
/**
 * Created by liping on 15/04/15.
 */
public class Message {
    private String content;

    public Message(){

    }

    public Message(String userName){
        this.content = "Hello " + userName;
    }
    public void setContent(String userName){
        content = "Hello " + userName;
    }

    public String getContent() {
        return content;
    }

    @PostConstruct
    public void init(){
        System.out.println("message 在初始化！");
    }
}
