package com.example.data.jdbc.log.persistence.article;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
interface ArticleEventPublicationRepository extends CrudRepository<ArticleEventPublicationEntity, UUID> {

  @Query("SELECT * FROM ARTICLE_EVENT_PUBLICATION ORDER BY PUBLICATION_DATE ASC")
  List<ArticleEventPublicationEntity> findAllOrderByPublicationDateDesc();
}
