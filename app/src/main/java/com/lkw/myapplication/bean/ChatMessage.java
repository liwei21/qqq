package com.lkw.myapplication.bean;

/**
 * Created by LKW on 2015/5/5.
 */
public class ChatMessage {
    //收到的
    public static final int SOURCE_TYPE_RECEIVED=1;
    //发出去的
    public static final int SOURCE_TYPE_SEND=0;
    //发消息的人
    private String from;
    //消息发给谁
    private String to;
    //消息内容
    private String body;
    //收到时间
    private long time;
    //消息的来源类型，代表是 发出去的，还是收到的
//    可选值：0：发出去，1：收到
    private int sourceType;

    @Override
    public String toString() {
        return "ChatMessage{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", body='" + body + '\'' +
                ", time=" + time +
                ", sourceType=" + sourceType +
                '}';
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }
}
