package com.tradeshift.test;

import com.tradeshift.dao.ResponseMessageDao;
import com.tradeshift.dao.impl.ResponseMessageDaoImpl;
import com.tradeshift.message.Message;
import com.tradeshift.message.ResponseMessage;
import com.tradeshift.message.ResponseMessages;
import com.tradeshift.service.MessageService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by liping on 22/04/15.
 */
public class MessageServiceTest {
    @InjectMocks
    MessageService msgService;

    @Mock
    ResponseMessageDao responseMsgDao;

    String userName;
    List<ResponseMessage> responseMsgList;

    private void init(){
        userName = "testUser";
        responseMsgList = new ArrayList<ResponseMessage>();
        ResponseMessage responseMsg = new ResponseMessage();
        responseMsg.setMessage(new Message(userName));
        responseMsgList.add(responseMsg);
    }

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        init();
    }


    @Test
    public void testGetResponse() throws Exception {
        ResponseMessage responseMsg = msgService.getResponse(userName);
        assertEquals("Hello " + userName, responseMsg.getMessage().getContent());
        verify(responseMsgDao).insert(responseMsg);
    }

    @Test
    public void testGetRecentResponses() throws Exception {
        when(msgService.getRecentResponses()).thenReturn(responseMsgList);

        List<ResponseMessage> returnMsgList = msgService.getRecentResponses();

        assertEquals(returnMsgList, responseMsgList);
        verify(responseMsgDao).findRecentMessage();
    }


}
