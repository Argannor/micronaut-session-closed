package com.example;

import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.Async;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class Listener {

    @Inject
    private EntityRepository repository;

    private final static Logger log = LoggerFactory.getLogger(Listener.class);

    @EventListener
    @Async
    @ExecuteOn(TaskExecutors.IO)
    public void listen(Event event) {
        var entity = repository.findById(event.getId());
        log.info("found: {}", entity);
    }
}
