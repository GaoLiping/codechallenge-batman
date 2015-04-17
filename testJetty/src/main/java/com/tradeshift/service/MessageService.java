package com.tradeshift.service;

import com.sun.jersey.spi.resource.Singleton;
import com.tradeshift.message.ResponseMessage;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;
import com.tradeshift.message.Message;

import java.io.IOException;

/**
 * Created by liping on 15/04/15.
 */

@Singleton
@Service
public class MessageService {

    public static final ObjectMapper mapper = new ObjectMapper();

    private ResponseMessage createResponse(String userName){
        ResponseMessage responseMsg = new ResponseMessage();
        Message msg = new Message(userName);
        responseMsg.setMessage(msg);
        return responseMsg;
    }

    private String toJson(ResponseMessage resMsg){
        String result = "";
        try{
            result = mapper.writeValueAsString(resMsg);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public String getRespose(String userName){
        ResponseMessage resMsg = createResponse(userName);
        return toJson(resMsg);
    }
}
