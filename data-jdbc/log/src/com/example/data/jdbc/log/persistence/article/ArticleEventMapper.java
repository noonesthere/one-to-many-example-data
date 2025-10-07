package com.example.data.jdbc.log.persistence.article;


import com.example.domain.article.events.ArticleEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class ArticleEventMapper {

  private final ObjectMapper objectMapper;

  public ArticleEventMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public ArticleEventPublicationEntity toEntity(ArticleEvent event) {
    try {
      final String serializedEvent = objectMapper.writeValueAsString(event);
      // TODO: can be simplified by using instead ARTICLE_ID column or CATEGORY_ID COLUMN just DOMAIN_ID and reduce count of Mappers
      return new ArticleEventPublicationEntity(
        UUID.randomUUID(),
        event.domainId(),
        event.getClass().getCanonicalName(),
        event.createdAt(),
        serializedEvent
      );
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Failed to serialize CategoryCreatedEvent", e);
    }
  }
}
