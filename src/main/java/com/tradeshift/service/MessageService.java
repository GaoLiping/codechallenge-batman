package com.tradeshift.service;

import com.sun.jersey.spi.resource.Singleton;
import com.tradeshift.dao.ResponseMessageDao;
import com.tradeshift.message.ResponseMessage;
import com.tradeshift.message.ResponseMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tradeshift.message.Message;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by liping on 15/04/15.
 */

@Singleton
@Service
public class MessageService {

    @Autowired
    private ResponseMessageDao msgDao;

    public ResponseMessage getResponse(String userName){
        ResponseMessage responseMsg = new ResponseMessage();
        responseMsg.setMessage(new Message(userName));
        msgDao.insert(responseMsg);
        return responseMsg;
    }
    //todo
    public List<ResponseMessage> getRecentResponses(){
         return msgDao.findRecentMessage();
    }
}
