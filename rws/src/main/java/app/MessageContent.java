package app;

/**
 * DTO storing the content of a message
 */
public class MessageContent {

    private String content;

    public MessageContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
