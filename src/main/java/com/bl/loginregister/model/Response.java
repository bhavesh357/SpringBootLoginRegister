package com.bl.loginregister.model;

import java.util.Objects;

public class Response {

    private int statusCode;
    private String message;
    private Object object;

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return statusCode == response.statusCode &&
                message.equals(response.message) &&
                object.equals(response.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusCode, message, object);
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
