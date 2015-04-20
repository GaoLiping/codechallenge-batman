
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.core.MediaType;

import java.io.IOException;

@Component
@Path("/names/{username}")
public class MessageResource {
    @Autowired
    private MessageService msgService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseMessage reply(@PathParam("username") String userName){
        return msgService.createResponse(userName);
    }
}
