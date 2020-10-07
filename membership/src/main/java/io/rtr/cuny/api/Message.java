package io.rtr.cuny.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
    private String data;

    public Message() {
    }

    public Message(String data) {
        this.data = data;
    }

    @JsonProperty
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
