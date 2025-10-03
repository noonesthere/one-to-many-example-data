package com.example.common.events;

import com.example.common.types.DomainEvent;

public interface DomainEventListener<T extends DomainEvent> {

  Class<T> eventType();

  void handle(T event);
}
