package app;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test MessagesDAO
 */
public class MessagesDAOTest {

    private static final String TEST_NAME="test";
    private List<Message> messageStore;

    @Mock
    private DataSource dataSource;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private SimpleJdbcInsert insertMessage;

    // Inject our mocks into the tested object
    @InjectMocks
    private MessagesDAO messagesDAO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        messageStore = new ArrayList<>();
    }

    @Test
    public void testAll() throws Exception {
        setMockAnswers();
        assertEquals(0, messagesDAO.countRows());
        assertEquals(0, messagesDAO.getRecent(10).size());

        Message msg = new Message(TEST_NAME);
        messagesDAO.add(msg);
        messageStore.add(msg);
        setMockAnswers();

        assertEquals(1, messagesDAO.countRows());
        assertEquals(1, messagesDAO.getRecent(10).size());
    }

    /**
     * Set the return answers to the content of the messageStore object
     */
    public void setMockAnswers() {
        Mockito.when(jdbcTemplate.query(Mockito.anyString(), (RowMapper<Message>) Mockito.anyObject())).thenReturn(messageStore);
        Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(messageStore.size());
        Mockito.when(insertMessage.execute(Mockito.any(SqlParameterSource.class))).thenReturn(1);
    }
}