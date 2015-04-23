package com.tradeshift.test;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.tradeshift.dao.ResponseMessageDao;
import com.tradeshift.dao.impl.ResponseMessageDaoImpl;
import com.tradeshift.message.Message;
import com.tradeshift.message.ResponseMessage;
import com.tradeshift.service.MessageService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liping on 22/04/15.
 */
public class ResponseMessageDaoTest {
    @InjectMocks
    ResponseMessageDaoImpl responseMsgDao;

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    DataSource dataSource;

    ResponseMessage responseMsg;
    private List<ResponseMessage> responseMsgList;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        init();
    }

    private void init(){
        responseMsgList = new ArrayList<ResponseMessage>();
        String userName = "testName";
        responseMsg = new ResponseMessage();
        responseMsg.setMessage(new Message(userName));
        responseMsgList.add(responseMsg);

       when(jdbcTemplate.query(Mockito.anyString(), (String[]) Mockito.anyObject(), (RowMapper<ResponseMessage>) Mockito.anyObject())).thenReturn(responseMsgList);

        when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(responseMsgList.size());
    }


    @Test
    public void testFindRecentMessage() throws Exception{
        List<ResponseMessage> returnMsgs = responseMsgDao.findRecentMessage();
        assertEquals(returnMsgs, responseMsgList);
        verify(jdbcTemplate).query(Mockito.anyString(), (String[]) Mockito.anyObject(), (RowMapper<ResponseMessage>) Mockito.anyObject());
    }

    @Test
    public void testInsert() throws Exception{
        responseMsgDao.insert(responseMsg);
        verify(jdbcTemplate).update(Mockito.anyString(), Mockito.any(Class.class));
    }

}
