package com.tradeshift.dao.impl;

import com.tradeshift.dao.ResponseMessageDao;
import com.tradeshift.message.Message;
import com.tradeshift.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by liping on 22/04/15.
 */

@Repository
public class ResponseMessageDaoImpl implements ResponseMessageDao {
    public static final int MSG_COUNT = 10;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        initTable();
    }
    public void initTable(){
        String sqlCreate = "CREATE TABLE IF NOT EXISTS responsemessage (\n"
                + "content varchar(80) NOT NULL,\n " +
                "timestamp timestamptz DEFAULT current_timestamp)";
        jdbcTemplate.update(sqlCreate);
    }

    public void insert(ResponseMessage rmsg){
        String sql = "INSERT INTO responsemessage " +
                "(content) VALUES (?)";

        jdbcTemplate.update(sql, rmsg.getMessage().getContent());
    }

    public List<ResponseMessage> findRecentMessage(){
        List<ResponseMessage> responseMsgs = jdbcTemplate.query(
                "SELECT * FROM responsemessage ORDER BY timestamp DESC LIMIT ?",
                new Object[] { MSG_COUNT },new ResponseMessageMapper());

        return responseMsgs;
    }

    private static final class ResponseMessageMapper implements RowMapper<ResponseMessage> {
        public ResponseMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
            Message msg = new Message();
            msg.setContent(rs.getString("content"));
            msg.setTimestamp(rs.getTimestamp("timestamp"));
            ResponseMessage responseMsg = new ResponseMessage();
            responseMsg.setMessage(msg);
            return responseMsg;
        }
    }
}
