package com.vttp.miniprojserver.models;

public class Response {
    private String code;
    private String message;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return String.format("{\"code\":\"%s\", \"message\":\"%s\"}",
                code, message);
    }
}
