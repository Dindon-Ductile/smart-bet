package com.smartbet.demo;

import okhttp3.Request;
import retrofit2.Response;

import java.io.IOException;

public class CallExecutionException extends Exception {
    private final Request request;
    private final Response<?> response;
    private final String body;

    private CallExecutionException(Request request, Response<?> response, String body) {
        super("Status: " + response.code() + " Body: " + body);
        this.request = request;
        this.response = response;
        this.body = body;
    }

    public Request getRequest() {
        return request;
    }

    public Response<?> getResponse() {
        return response;
    }

    public int getStatus() {
        return response.code();
    }

    public String getBody() {
        return body;
    }

    public static CallExecutionException build(Request request, Response<?> response) {
        String body = bodyToString(response);
        return new CallExecutionException(request, response, body);
    }

    private static String bodyToString(Response<?> response) {
        try {
            return response.errorBody().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
