package com.example.common.types;

import java.time.Instant;
import java.util.UUID;

public abstract class DomainEvent {

  public final Instant createdAt;
  public final UUID id;

  protected DomainEvent() {
    this.id = UUID.randomUUID();
    this.createdAt = Instant.now();
  }
}
