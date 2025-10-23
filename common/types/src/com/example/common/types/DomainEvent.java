package com.example.common.types;

import java.time.Instant;
import java.util.UUID;

public interface DomainEvent {
  UUID id();

  Instant createdAt();

  Long domainId();
}
