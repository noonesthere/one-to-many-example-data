package com.example.data.jdbc.log.persistence.category;


import com.example.common.utilities.CollectionsUtils;
import com.example.domain.category.Category;
import com.example.domain.category.CategoryId;
import com.example.domain.category.events.CategoryEvent;
import com.example.scenarios.outbound.category.CategoryExtractorOutPort;
import org.springframework.stereotype.Component;

@Component
public class H2CategoryEventRepositoryAdapter implements CategoryExtractorOutPort { // Skipped interface and other stuff for simplicity

  private final CategoryEventPublicationRepository repository;
  private final CategoryEventMapper mapper;

  public H2CategoryEventRepositoryAdapter(
    CategoryEventPublicationRepository repository,
    CategoryEventMapper mapper
  ) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public void persist(CategoryEvent event) {
    repository.save(mapper.toEntity(event));
  }

  @Override
  public Category get(CategoryId id) {
    final var events = CollectionsUtils.streamOf(repository.findAllByIdOrderByPublicationDateDesc(id.value()))
      .map(mapper::fromEntity)
      .toList();

    return Category.rebuild(events);
  }
}
