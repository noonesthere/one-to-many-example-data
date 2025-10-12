package com.example.data.jdbc.persistence.article;

import com.example.common.types.DomainEvent;
import com.example.domain.article.Article;
import com.example.domain.article.events.ArticleRateChangedEvent;
import com.example.domain.article.events.CategoryChangedEvent;
import com.example.domain.article.events.ParagraphEditedEvent;
import com.example.domain.article.events.ParagraphRemovedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
class ArticlePartialUpdater {

  private final NamedParameterJdbcTemplate jdbcTemplate;
  private final ApplicationEventPublisher eventPublisher;

  ArticlePartialUpdater(
    NamedParameterJdbcTemplate jdbcTemplate, ApplicationEventPublisher eventPublisher
  ) {
    this.jdbcTemplate = jdbcTemplate;
    this.eventPublisher = eventPublisher;
  }

  @Transactional
  void update(DomainEvent event, Article article) {
    final var entity = ArticleEntity.from(article);

    final int result = switch (event) {
      case CategoryChangedEvent e -> changeCategory(e, entity);
      case ArticleRateChangedEvent e -> vote(e, entity);
      case ParagraphEditedEvent e -> updateParagraph(e, entity);
      case ParagraphRemovedEvent e -> deleteParagraph(e, entity);
      // use for testing purposes
      default -> throw new UnsupportedOperationException("Unsupported event " + event);
    };
    eventPublisher.publishEvent(event);
  }

  private int deleteParagraph(ParagraphRemovedEvent e, ArticleEntity entity) {
    final var mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("updatedAt", entity.updatedAt());
    mapSqlParameterSource.addValue("id", entity.id());
    mapSqlParameterSource.addValue("previous", entity.version());
    mapSqlParameterSource.addValue("version", entity.version() + 1);

    jdbcTemplate.update(
      "UPDATE ARTICLE SET UPDATED_AT = :updatedAt, VERSION = :version WHERE ID = :id AND VERSION = :previous",
      mapSqlParameterSource
    );

    return jdbcTemplate.update(
      "DELETE FROM PARAGRAPH WHERE ID  = :id", new MapSqlParameterSource("id", e.paragraphId())
    );
  }

  int updateParagraph(ParagraphEditedEvent e, ArticleEntity entity) {


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
      .filter(paragraph -> paragraph.id().equals(e.paragraphId()))
      .findFirst()
      .orElseThrow();

    final var params = new MapSqlParameterSource();
    params.addValue("id", paragraphEntity.id());
    params.addValue("text", paragraphEntity.text());
    params.addValue("previous", entity.version());
    params.addValue("version", entity.version() + 1);

    return jdbcTemplate.update(
      "UPDATE PARAGRAPH SET TEXT =:text, VERSION=:version WHERE ID = :id AND VERSION = :previous", params
    );
  }

  private int vote(ArticleRateChangedEvent e, ArticleEntity entity) {
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

  private int changeCategory(CategoryChangedEvent e, ArticleEntity entity) {
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
