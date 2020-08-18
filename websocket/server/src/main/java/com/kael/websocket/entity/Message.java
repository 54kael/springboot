package com.kael.websocket.entity;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/17 0017
 */
public class Message {
    private String from;
    private String content;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from='" + from + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
