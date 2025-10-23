package com.example.common.types;

import java.util.ArrayList;
import java.util.List;

public abstract class DomainEntity<T> {

  public final T id;
  private Version version;

  private List<DomainEvent> events = new ArrayList<>();

  protected DomainEntity(T id, Version version) {
    this.id = id;
    this.version = version;
  }

  protected void addEvent(DomainEvent event) {
    // TODO: think about it
    events.add(event);
  }

  public List<DomainEvent> popEvents() {
    List<DomainEvent> result = events;
    events = new ArrayList<>();
    return result;
  }

  public Version version() {
    return version;
  }

  protected void setVersion(Version version) {
    this.version = version;
  }

  protected void updateVersion() {
    version = version.next();
  }

}
