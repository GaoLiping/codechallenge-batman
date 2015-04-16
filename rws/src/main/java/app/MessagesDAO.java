package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO class for storing/retrieving messages to/from the database
 */
@Repository
public class MessagesDAO {

    private JdbcTemplate jdbcTemplate;

    /**
     * Initialization of DAO. Automatically executed by Spring.
     * See http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#dao-annotations
     * @param dataSource the datasource from context.xml
     */
    @Autowired
    public void init(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);

        // Make sure that the table is there..
        String tabledef = "CREATE TABLE IF NOT EXISTS messages(id SERIAL NOT NULL PRIMARY KEY, message text NOT NULL, ts timestamp with time zone default CURRENT_TIMESTAMP)";
        this.jdbcTemplate.execute(tabledef);
    }


    /**
     * Add a message to the database, remembering the timestamp of insertion
     * @param message the message to insert
     */
    public void add(Message message) {
        this.jdbcTemplate.update("INSERT INTO messages (message) VALUES (?)", message.getMessage().getContent());
    }

    /**
     * Get the recent messages from the database.
     * @param howMany how many messages should we return at most
     */
    public List<Message> getRecent(int howMany) {
        return this.jdbcTemplate.query("SELECT message, ts FROM messages ORDER BY ts DESC LIMIT ?", new Integer[] {howMany}, new MessageMapper());
    }

    /**
     * Count the rows in the messages table.
     */
    public int countRows() {
        return this.jdbcTemplate.queryForObject("SELECT count(*) FROM messages", Integer.class);
    }


    /**
     * Mapper for going from a list of row results to a list of Messages
     */
    private static final class MessageMapper implements RowMapper<Message> {
        public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
            Message msg = new Message(rs.getString("message"));
            msg.setStamp(rs.getTimestamp("ts"));
            return msg;
        }
    }

}
