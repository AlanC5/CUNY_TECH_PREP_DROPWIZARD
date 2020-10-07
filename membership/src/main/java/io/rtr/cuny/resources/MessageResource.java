package io.rtr.cuny.resources;

import io.rtr.cuny.api.Message;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/message")
public class MessageResource {
    private final String message;

    public MessageResource(String message) {
        this.message = message;
    }

    @GET
    public Message getMessage() {
        return new Message(this.message);
    }
}
