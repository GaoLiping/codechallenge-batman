package app;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

/**
 * Test MessagesDAO
 */
public class MessagesDAOTest {

    private static final String TEST_NAME="test";

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
        Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(0);
    }

    @Test
    public void testAdd() throws Exception {
        Message msg = new Message(TEST_NAME);
        messagesDAO.add(msg);
        Mockito.verify(jdbcTemplate).update(Mockito.anyString(), Mockito.anyObject());
    }

    @Test
    public void testGetRecent() throws Exception {
        messagesDAO.getRecent(10);
        Mockito.verify(jdbcTemplate).query(Mockito.anyString(), (String[])Mockito.anyObject(), (RowMapper<Message>) Mockito.anyObject());
    }

    @Test
    public void testCountRows() throws Exception {
        messagesDAO.countRows();
        Mockito.verify(jdbcTemplate).queryForObject(Mockito.anyString(), Mockito.any(Class.class));
    }


}