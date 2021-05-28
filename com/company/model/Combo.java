package com.company.model;

public class Combo {
    private Item object;
    private Item subject;
    private Item result;
    private String message;

    public Item getObject() {
        return object;
    }

    public Item getSubject() {
        return subject;
    }

    public Item getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public Combo(Item object, Item subject, Item result, String message) {
        this.object = object;
        this.subject = subject;
        this.result = result;
        this.message = message;
    }
}
