package com.example.data.jdbc.log.persistence;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryEventPublicationRepository extends ListCrudRepository<CategoryEventPublicationEntity, UUID> {

  @Query("SELECT * FROM CATEGORY_EVENT_PUBLICATION ORDER BY PUBLICATION_DATE DESC")
  List<CategoryEventPublicationEntity> findAllOrderByPublicationDateDesc();
}
