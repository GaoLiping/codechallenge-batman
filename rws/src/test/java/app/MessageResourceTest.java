package app;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Test MessageResource
 */
public class MessageResourceTest {

    private static final String TEST_NAME="test";
    private Message msg;
    private List<Message> messageStore;

    // Inject our mocks into the tested object
    @InjectMocks
    private MessageResource resourceObject;

    @Mock
    private MessageService messageService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        messageStore = new ArrayList<>();
        msg = new Message(TEST_NAME);
    }

    @Test
    public void testAll() throws Exception {
        // Get empty recent
        setMockAnswers();

        Map<String, Object> out = resourceObject.getRecentMessages();
        int count = resourceObject.getCount();
        assertEquals(0, count);
        assertEquals(messageStore.size(), out.get("messageCount"));

        // Add a thing, check that it's returned
        Message storedMessage = resourceObject.sayHello(TEST_NAME);
        assertEquals(storedMessage, msg);
        messageStore.add(storedMessage);
        setMockAnswers();

        out = resourceObject.getRecentMessages();
        count = resourceObject.getCount();
        assertEquals(1, count);
        assertEquals(messageStore.size(), out.get("messageCount"));
        assertEquals(messageStore, (List<Message>)out.get("messages"));
    }
    /**
     * Set the return answers to the content of the messageStore object
     */
    public void setMockAnswers() {
        Mockito.when(messageService.countMessages()).thenReturn(messageStore.size());
        Mockito.when(messageService.getRecentMessages()).thenReturn(messageStore);
        Mockito.when(messageService.storeName(Mockito.anyString())).thenReturn(msg);

    }
}