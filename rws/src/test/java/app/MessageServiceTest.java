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
    private List<Message> messages;

    // Inject our mocks into the tested object
    @InjectMocks
    private MessageService serviceObject;

    @Mock
    private MessagesDAO messagesDAO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        messages = new ArrayList<>();

        messages.add(new Message(TEST_NAME));

        // Count
        Mockito.when(messagesDAO.countRows()).thenReturn(messages.size());

        // Recent
        Mockito.when(messagesDAO.getRecent(10)).thenReturn(messages);
    }

    @Test
    public void testStoreName() throws Exception {
        Message msg = serviceObject.storeName(TEST_NAME);
        assertEquals("Hello "+TEST_NAME, msg.getMessage().getContent());
        Mockito.verify(messagesDAO).add(msg);
    }

    @Test
    public void testCountMessages() throws Exception {
        int count = serviceObject.countMessages();
        assertEquals(messages.size(), count);
        Mockito.verify(messagesDAO).countRows();
    }

    @Test
    public void testGetRecentMessages() throws Exception {
        List<Message> out = serviceObject.getRecentMessages();
        assertEquals(messages, out);
        Mockito.verify(messagesDAO).getRecent(10);
    }
}