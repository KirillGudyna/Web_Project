package com.gudyna.webproject.response;

public class ResponseWrapper {
    private final String messageType;
    private final Object data;
    private final String message;

    private ResponseWrapper(String messageType, Object data, String message) {
        this.messageType = messageType;
        this.data = data;
        this.message = message;
    }

    public static ResponseWrapper setError(String message) {
        return new ResponseWrapper("error", null, message);
    }

    public static ResponseWrapper setSuccess(String message, Object data) {
        return new ResponseWrapper("Success", data, message);
    }
}
