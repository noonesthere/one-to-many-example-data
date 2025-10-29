package com.example.common.types;

import java.util.ArrayList;
import java.util.List;

public abstract class DomainEntity<T> {

  private List<DomainEvent> events = new ArrayList<>();

  protected void addEvent(DomainEvent event) {
    events.add(event);
  }

  public List<DomainEvent> popEvents() {
    List<DomainEvent> result = events;
    events = new ArrayList<>();
    return result;
  }

}
