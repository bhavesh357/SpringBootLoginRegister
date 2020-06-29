package com.bl.loginregister.model;

public class Response {

    private int statusCode;
    private String message;
    private Object object;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Response(int statusCode, String message, Object object) {
        this.statusCode = statusCode;
        this.message = message;
        this.object = object;
    }
}
