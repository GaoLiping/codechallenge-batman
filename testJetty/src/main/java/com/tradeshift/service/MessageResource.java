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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Path("/names/{username}")
public class MessageResource {
    @Autowired
    private MessageService msgService;

    @GET
    @Produces("application/json")
    public String savePayment(@PathParam("username") String userName){
        System.out.println(userName);
        //return userName;
        String val = msgService.getRespose(userName);
        return val;
    }
}
