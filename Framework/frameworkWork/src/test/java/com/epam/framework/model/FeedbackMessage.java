package com.epam.framework.model;

public class FeedbackMessage {
    private String name;
    private String mobile;
    private String message;

    public FeedbackMessage(String name, String mobile, String message) {
        this.name = name;
        this.mobile = mobile;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getMessage() {
        return message;
    }
}
