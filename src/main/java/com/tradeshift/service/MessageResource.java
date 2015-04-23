
package com.tradeshift.service;

/**
 * Created by liping on 14/04/15.
 */
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.tradeshift.message.Message;
import com.tradeshift.message.ResponseMessage;
import com.tradeshift.message.ResponseMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.core.MediaType;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component
@Path("messages")
public class MessageResource {
    @Autowired
    private MessageService msgService;

    @GET
    @Path("/names/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseMessage reply(@PathParam("username") String userName){
        return msgService.getResponse(userName);
    }

    @GET
    @Path("/recent")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseMessages replyRecency(){
        List<ResponseMessage> responseMsgs = msgService.getRecentResponses();
        if(!responseMsgs.isEmpty()){
            return new ResponseMessages(responseMsgs.size(),
                    responseMsgs.get(0).getMessage().getTimestamp(),
                    responseMsgs);
        }else{
            return new ResponseMessages(0, null, null);
        }
    }
}