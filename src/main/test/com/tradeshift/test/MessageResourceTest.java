package com.tradeshift.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.tradeshift.message.Message;
import com.tradeshift.message.ResponseMessage;
import com.tradeshift.message.ResponseMessages;
import com.tradeshift.service.MessageResource;
import com.tradeshift.service.MessageService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liping on 22/04/15.
 */

@RunWith(MockitoJUnitRunner.class)
public class MessageResourceTest {

    @InjectMocks
    MessageResource msgResource;

    @Mock
    MessageService msgService;

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
    public void testReply() throws Exception {
        String userName = "testUser";
        msgResource.reply(userName);
        verify(msgService).getResponse(userName);
    }

    @Test
    public void testReplyRecencyExist() throws Exception {;
        when(msgService.getRecentResponses()).thenReturn(responseMsgList);
        msgResource.replyRecency();
        verify(msgService).getRecentResponses();
    }

    @Test
    public void testReplyRecencyNotExist() throws Exception {
        responseMsgList.clear();
        when(msgService.getRecentResponses()).thenReturn(responseMsgList);
        ResponseMessages responseMsgs = msgResource.replyRecency();
        assertEquals(0,responseMsgs.getMessageCount());
    }

}
