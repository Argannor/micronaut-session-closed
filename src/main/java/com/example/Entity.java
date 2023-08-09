package com.example;

import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import java.util.UUID;

@javax.persistence.Entity
public class Entity {

    @Id
    private String id;

    @PrePersist
    @PreUpdate
    public void prePersist() {
        if(this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
