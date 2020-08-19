package com.kael.websocket.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Author kael
 * @Description
 * @Date 2020/8/17 0017
 */
public class Message {
    private String from;
    private String content;
    private String to;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    @Override
    public String toString() {
        return "Message{" +
                "from='" + from + '\'' +
                ", content='" + content + '\'' +
                ", to='" + to + '\'' +
                ", date=" + date +
                '}';
    }

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

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
