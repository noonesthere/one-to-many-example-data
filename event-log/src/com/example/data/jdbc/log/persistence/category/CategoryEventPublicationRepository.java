package com.example.data.jdbc.log.persistence.category;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryEventPublicationRepository extends CrudRepository<CategoryEventPublicationEntity, UUID> {

  @Query("SELECT * FROM CATEGORY_EVENT_PUBLICATION ORDER BY PUBLICATION_DATE ASC")
  List<CategoryEventPublicationEntity> findAllOrderByPublicationDateDesc();
}
