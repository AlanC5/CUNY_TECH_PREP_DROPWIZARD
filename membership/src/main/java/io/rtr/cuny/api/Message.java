package io.rtr.cuny.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {

    private long id;
    private String message;
    public Message() {
    }

    public Message(long id, String message) {
        this.id = id;
        this.message = message;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getMessage() {
        return message;
    }

}
