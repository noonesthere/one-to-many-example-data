package com.example.data.jdbc.log.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

@Table("CATEGORY_EVENT_PUBLICATION")
public class CategoryEventPublicationEntity implements Persistable<UUID> {

  @Id
  private UUID id;

  @Column("EVENT_TYPE")
  private String eventType;

  @Column("PUBLICATION_DATE")
  private OffsetDateTime publicationDate;

  @Column("SERIALIZED_EVENT")
  private String serializedEvent;

  public CategoryEventPublicationEntity(UUID id, String eventType, OffsetDateTime publicationDate, String serializedEvent) {
    this.id = id;
    this.eventType = eventType;
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

  public String getEventId() {
    return eventType;
  }

  public void setEventId(String eventId) {
    this.eventType = eventId;
  }
}
