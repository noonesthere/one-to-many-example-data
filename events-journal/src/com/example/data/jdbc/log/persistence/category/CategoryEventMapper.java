package com.example.data.jdbc.log.persistence.category;


import com.example.common.types.DomainEvent;
import com.example.domain.category.events.CategoryEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class CategoryEventMapper {

  private final ObjectMapper objectMapper;

  CategoryEventMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public CategoryEventPublicationEntity toEntity(CategoryEvent event) {
    try {
      final String serializedEvent = objectMapper.writeValueAsString(event);

      return new CategoryEventPublicationEntity(
        UUID.randomUUID(),
        event.categoryId(),
        event.getClass().getCanonicalName(),
        event.createdAt(), // OffsetDateTime.now(ZoneOffset.UTC),
        serializedEvent
      );
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Failed to serialize CategoryCreatedEvent", e);
    }
  }

  public DomainEvent fromEntity(CategoryEventPublicationEntity entity) {
    try {
      String eventType = entity.eventType();
      Class<?> aClass = Class.forName(eventType);
      Object o = objectMapper.readValue(entity.serializedEvent(), aClass);
      return (CategoryEvent) o;
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Failed to serialize CategoryCreatedEvent", e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

}
