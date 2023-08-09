package com.example;

import io.micronaut.context.BeanContext;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.ApplicationEventPublisher;
import io.micronaut.context.event.StartupEvent;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.UUID;

/**
 * Startup actions
 */
@Singleton
public class Start implements ApplicationEventListener<StartupEvent>
{

	@Inject
	private BeanContext beanContext;

	@Inject
	private ApplicationEventPublisher<Event> publisher;

	@Override
	@Transactional
	public void onApplicationEvent(StartupEvent event)
	{
		publisher.publishEventAsync(new Event(UUID.randomUUID().toString()));
	}


}
