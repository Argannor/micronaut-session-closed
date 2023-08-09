package com.example;

import io.micronaut.context.event.ApplicationEvent;

public class Event extends ApplicationEvent {

    private final String id;

    public Event(String id) {
        super(id);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
