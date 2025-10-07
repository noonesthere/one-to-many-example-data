package com.example.data.jdbc.log.persistence;


import com.example.domain.category.events.CategoryEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Component
class CategoryEventPublicationMapper {

  private final ObjectMapper objectMapper;

  public CategoryEventPublicationMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public CategoryEventPublicationEntity toEntity(CategoryEvent event) {
    try {
      final String serializedEvent = objectMapper.writeValueAsString(event);

      return new CategoryEventPublicationEntity(
        UUID.randomUUID(),
        event.categoryId(),
        event.getClass().getCanonicalName(),
        OffsetDateTime.now(ZoneOffset.UTC),
        serializedEvent
      );
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Failed to serialize CategoryCreatedEvent", e);
    }
  }
}
