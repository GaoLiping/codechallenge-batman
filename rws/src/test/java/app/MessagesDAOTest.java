package app;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test MessagesDAO
 */
public class MessagesDAOTest {

    private static final String TEST_NAME="test";
    private List<Message> messages;


    @Mock
    private DataSource dataSource;

    @Mock
    private JdbcTemplate jdbcTemplate;

    // Inject our mocks into the tested object
    @InjectMocks
    private MessagesDAO messagesDAO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        messages = new ArrayList<>();

        messages.add(new Message(TEST_NAME));

        // Recent
        Mockito.when(jdbcTemplate.query(Mockito.anyString(), (String[]) Mockito.anyObject(), (RowMapper<Message>) Mockito.anyObject())).thenReturn(messages);

        // Count
        Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(messages.size());
    }

    @Test
    public void testAdd() throws Exception {
        Message msg = new Message(TEST_NAME);
        messagesDAO.add(msg);
        Mockito.verify(jdbcTemplate).update(Mockito.anyString(), Mockito.anyObject());
    }

    @Test
    public void testGetRecent() throws Exception {
        List<Message> out = messagesDAO.getRecent(10);
        assertEquals(out, messages);
        Mockito.verify(jdbcTemplate).query(Mockito.anyString(), (String[])Mockito.anyObject(), (RowMapper<Message>) Mockito.anyObject());
    }

    @Test
    public void testCountRows() throws Exception {
        int count = messagesDAO.countRows();
        assertEquals(messages.size(), count);
        Mockito.verify(jdbcTemplate).queryForObject(Mockito.anyString(), Mockito.any(Class.class));
    }


}