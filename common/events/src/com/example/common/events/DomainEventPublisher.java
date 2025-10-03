package com.example.common.events;

import com.example.common.types.DomainEvent;

import java.util.Collection;

public interface DomainEventPublisher {
  void publish(Collection<DomainEvent> events);

}
