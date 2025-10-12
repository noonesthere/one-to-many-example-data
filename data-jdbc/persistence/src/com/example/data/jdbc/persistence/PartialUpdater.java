package com.example.data.jdbc.persistence;

import com.example.common.types.DomainEvent;

public interface PartialUpdater<T> {
  int update(DomainEvent event, T aggregateRoot);
}
