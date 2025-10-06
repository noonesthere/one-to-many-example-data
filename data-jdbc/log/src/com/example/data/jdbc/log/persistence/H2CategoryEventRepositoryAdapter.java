package com.example.data.jdbc.log.persistence;

import com.example.domain.category.events.CategoryEvent;
import com.example.domain.category.events.CategoryRenamedEvent;
import org.springframework.stereotype.Repository;

@Repository
public class H2CategoryEventRepositoryAdapter { // Skipped interface and other stuff for simplicity
  private final CategoryEventPublicationRepository repository;
  private final CategoryEventPublicationMapper mapper;

  H2CategoryEventRepositoryAdapter(
    CategoryEventPublicationRepository repository,
    CategoryEventPublicationMapper mapper
  ) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public void persist(CategoryEvent event) {
    repository.save(mapper.toEntity(event));
  }
}
