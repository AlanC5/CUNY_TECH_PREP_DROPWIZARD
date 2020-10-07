package io.rtr.cuny.resources;

import io.rtr.cuny.api.Message;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
    private static long lastId = 0;
    private final String message;

    public MessageResource(String message) {
        this.message = message;
    }

    @GET
    public Message getMessage() {
        return new Message(++lastId, message);
    }
}
