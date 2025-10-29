package com.example.data.jdbc.log.persistence.category;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CategoryEventPublicationRepository extends ListCrudRepository<CategoryEventPublicationEntity, UUID> {

  @Query("SELECT * FROM CATEGORY_EVENT_PUBLICATION ORDER BY PUBLICATION_DATE ASC")
  List<CategoryEventPublicationEntity> findAllOrderByPublicationDateDesc();

  @Query("SELECT * FROM CATEGORY_EVENT_PUBLICATION WHERE CATEGORY_ID = :categoryId ORDER BY PUBLICATION_DATE ASC")
  List<CategoryEventPublicationEntity> findAllByIdOrderByPublicationDateDesc(@Param("categoryId") Long categoryId);
}
