package app;

/**
 * Created by svi on 10/04/15.
 */
public class Message {

    private String content;

    public Message(String content) {
        setContent(content);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
