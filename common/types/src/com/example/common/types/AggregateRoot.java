package com.example.common.types;


public abstract class AggregateRoot<T> extends DomainEntity<T> {
  private T id;
  private Version version;

  protected AggregateRoot(T id, Version version) {
    this.id = id;
    this.version = version;
  }

  protected void updateVersion() {
    version = version.next();
  }

  public T id() {
    return id;
  }

  public Version version() {
    return version;
  }
}
