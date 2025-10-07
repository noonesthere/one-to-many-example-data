package com.example.data.jdbc.log.persistence.article;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table("ARTICLE_EVENT_PUBLICATION")
public record ArticleEventPublicationEntity(
  @Id @Column("ID") UUID id,
  @Column("ARTICLE_ID") Long categoryId,
  @Column("EVENT_TYPE") String eventType,
  @Column("PUBLICATION_DATE") Instant publicationDate,
  @Column("SERIALIZED_EVENT") String serializedEvent

) implements Persistable<UUID> {

  @Override
  public UUID getId() {
    return id;
  }

  @Override
  public boolean isNew() {
    return true; // prevent updating
  }
}
