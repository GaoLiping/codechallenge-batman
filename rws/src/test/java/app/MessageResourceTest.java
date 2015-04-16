package app;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test MessageResource
 */
public class MessageResourceTest {

    private static final String TEST_NAME="test";

    // Inject our mocks into the tested object
    @InjectMocks
    private MessageResource resourceObject;

    @Mock
    private MessageService messageService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testSayStaticHello() throws Exception {
        Message msg = resourceObject.sayStaticHello();
        assertEquals(msg.getMessage().getContent(), "static");
    }

    @Test
    public void testSayHello() throws Exception {
        resourceObject.sayHello(TEST_NAME);
        Mockito.verify(messageService).storeName(TEST_NAME);
    }

    @Test
    public void testGetCount() throws Exception {
        resourceObject.getCount();
        Mockito.verify(messageService).countMessages();
    }

    @Test
    public void testGetRecentMessages() throws Exception {
        resourceObject.getRecentMessages();
        Mockito.verify(messageService).getRecentMessages();
    }
}