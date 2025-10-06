package com.example.data.jdbc.log.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

@Table("CATEGORY_EVENT_PUBLICATION")
public class CategoryEventPublicationEntity implements Persistable<UUID> {

  @Id
  private UUID id;

  private OffsetDateTime publicationDate;

  private String serializedEvent;

  public CategoryEventPublicationEntity(UUID id, OffsetDateTime publicationDate, String serializedEvent) {
    this.id = id;
    this.publicationDate = publicationDate;
    this.serializedEvent = serializedEvent;
  }

  public UUID getId() {
    return id;
  }

  @Override
  public boolean isNew() {
    return true;
  }

  public OffsetDateTime getPublicationDate() {
    return publicationDate;
  }

  public String getSerializedEvent() {
    return serializedEvent;
  }
}
