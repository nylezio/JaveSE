package javase.bio;

import java.io.Serializable;

/**
 * @author: codeJerry
 * @description:
 * @date: 2020/08/11 13:21
 */
public class Message implements Serializable {

    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
