package com.example;

import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.UUID;

@jakarta.persistence.Entity
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
