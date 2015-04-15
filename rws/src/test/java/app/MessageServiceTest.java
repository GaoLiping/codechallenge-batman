package app;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Testing MessageService
 */
public class MessageServiceTest {

    private static final String TEST_NAME="test";

    // Inject our mocks into the tested object
    @InjectMocks
    private MessageService serviceObject;

    @Mock
    private MessagesDAO messagesDAO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testStoreName() throws Exception {
        Message msg = serviceObject.storeName(TEST_NAME);
        assertEquals("Hello "+TEST_NAME, msg.getMessage().getContent());
    }

    @Test
    public void testCountMessages() throws Exception {
        // Get empty count
        int count = serviceObject.countMessages();
        assertEquals(0, count);

        // Add a thing, check that it's returned
        serviceObject.storeName("name");
        Mockito.when(messagesDAO.countRows()).thenReturn(1);

        count = serviceObject.countMessages();
        assertEquals(1, count);
    }

    @Test
    public void testGetRecentMessages() throws Exception {
        // Get empty recent
        List<Message> recent = new ArrayList<>();
        Mockito.when(messagesDAO.getRecent(10)).thenReturn(recent);
        List<Message> out = serviceObject.getRecentMessages();
        assertEquals(recent, out);

        // Add a thing, check that it's returned
        Message msg = serviceObject.storeName(TEST_NAME);
        recent.add(msg);
        Mockito.when(messagesDAO.getRecent(10)).thenReturn(recent);

        out = serviceObject.getRecentMessages();
        assertEquals(recent, out);
    }
}