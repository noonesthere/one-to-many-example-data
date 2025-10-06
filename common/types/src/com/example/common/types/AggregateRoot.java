package com.example.common.types;


public abstract class AggregateRoot<T> extends DomainEntity<T> {
  protected AggregateRoot(T id, Version version) {
    super(id, version);
  }
}
