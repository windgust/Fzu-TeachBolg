package com.fzuTeachBolg.ask.message;

public class BaseMessage {
    public String msg;
    public String data;

    @Override
    public String toString() {
        return "BaseMessage{" +
                "msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
