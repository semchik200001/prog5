package se.ifmo.lab5.chat.io.transfer;

import se.ifmo.lab5.collection.UserCollection;

import java.util.ArrayDeque;
import java.util.Deque;

public class Response {
    private final String message;
    private final UserCollection userCollection;

    private final Deque<String> inboundRequests = new ArrayDeque<>();

    public Response(String message, UserCollection userCollection) {
        this.message = message;
        this.userCollection = userCollection;
    }

    public Response(String message) {
        this(message, null);
    }

    public void addInboundRequest(String request) {
        inboundRequests.add(request);
    }

    public void addInboundRequests(Deque<String> requests) {
        inboundRequests.addAll(requests);
    }

    public Deque<String> getInboundRequests() {
        return inboundRequests;
    }

    public String getMessage() {
        return message;
    }

    public UserCollection getUserCollection() {
        return userCollection;
    }
}
