package com.tradeshift.dao;

import com.tradeshift.message.ResponseMessage;

import java.util.List;

/**
 * Created by liping on 22/04/15.
 */
public interface ResponseMessageDao {
    public void insert(ResponseMessage rmsg);
    public List<ResponseMessage> findRecentMessage();
 }
