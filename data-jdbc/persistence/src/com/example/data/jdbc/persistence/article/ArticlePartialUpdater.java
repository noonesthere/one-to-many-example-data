package com.example.data.jdbc.persistence.article;

import com.example.common.types.DomainEvent;
import com.example.domain.article.events.ArticleRateChangedEvent;
import com.example.domain.article.events.CategoryChangedEvent;
import com.example.domain.article.events.ParagraphEditedEvent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class ArticlePartialUpdater {

  private final NamedParameterJdbcTemplate jdbcTemplate;

  ArticlePartialUpdater(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  void update(List<DomainEvent> events, ArticleEntity entity) {
    for (DomainEvent event : events) {
      update(event, entity);
    }
  }


  private void update(DomainEvent event, ArticleEntity entity) {
    final int result = switch (event) {
      case CategoryChangedEvent e -> changeCategory(entity);
      case ArticleRateChangedEvent e -> vote(entity);
      case ParagraphEditedEvent e -> updateParagraph(entity, e.paragraphId());
      default -> 1;
    };

    System.out.println(result);
  }

  private int updateParagraph(ArticleEntity entity, Long paragraphId) {

    final var mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("updatedAt", entity.updatedAt());
    mapSqlParameterSource.addValue("id", entity.id());
    mapSqlParameterSource.addValue("previous", entity.version());
    mapSqlParameterSource.addValue("version", entity.version() + 1);

    jdbcTemplate.update(
      "UPDATE ARTICLE SET UPDATED_AT = :updatedAt, VERSION = :version WHERE ID = :id AND VERSION = :previous",
      mapSqlParameterSource
    );

    final ParagraphEntity paragraphEntity = entity.paragraphs().stream()
      .filter(paragraph -> paragraph.id().equals(paragraphId))
      .findFirst()
      .orElseThrow();

    final var params = new MapSqlParameterSource();
    params.addValue("id", paragraphEntity.id());
    params.addValue("text", paragraphEntity.text());
    params.addValue("previous", entity.version());
    params.addValue("version", entity.version() + 1);

    return jdbcTemplate.update(
      "UPDATE PARAGRAPH SET `TEXT`=:text, VERSION=:version WHERE ID = :id AND VERSION = :previous", params
    );
  }

  private int vote(ArticleEntity entity) {
    final var mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("rating", entity.rating());
    mapSqlParameterSource.addValue("count", entity.voteCount());
    mapSqlParameterSource.addValue("updatedAt", entity.updatedAt());
    mapSqlParameterSource.addValue("version", entity.version() + 1);

    mapSqlParameterSource.addValue("id", entity.id());
    mapSqlParameterSource.addValue("previous", entity.version());

//    RATING
//      VOTE_COUNT
    return jdbcTemplate.update(
      """
        UPDATE ARTICLE SET RATING = :rating, VOTE_COUNT = :count, UPDATED_AT = :updatedAt, VERSION = :version
        WHERE ID = :id AND VERSION = :previous
        """,
      mapSqlParameterSource
    );
  }

  private int changeCategory(ArticleEntity entity) {
    final var mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("id", entity.id());
    mapSqlParameterSource.addValue("categoryId", entity.categoryId());
    mapSqlParameterSource.addValue("updatedAt", entity.updatedAt());
    mapSqlParameterSource.addValue("version", entity.version() + 1);
    mapSqlParameterSource.addValue("previous", entity.version());

    return jdbcTemplate.update(
      """
        UPDATE ARTICLE SET CATEGORY_ID = :categoryId, UPDATED_AT = :updatedAt, VERSION = :version
        WHERE ID = :id AND VERSION = :previous
        """,
      mapSqlParameterSource
    );
  }
}
