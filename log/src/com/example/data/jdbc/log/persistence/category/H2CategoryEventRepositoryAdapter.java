package com.example.data.jdbc.log.persistence.category;


import com.example.domain.category.events.CategoryEvent;
import org.springframework.stereotype.Repository;

@Repository
public class H2CategoryEventRepositoryAdapter { // Skipped interface and other stuff for simplicity

  private final CategoryEventPublicationRepository repository;
  private final CategoryEventMapper mapper;

  public H2CategoryEventRepositoryAdapter(CategoryEventPublicationRepository repository, CategoryEventMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public void persist(CategoryEvent event) {
    repository.save(mapper.toEntity(event));
  }
}
