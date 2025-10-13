package com.example.data.jdbc.persistence.article;

import com.example.domain.article.events.ArticleRateChangedEvent;
import com.example.domain.article.events.ArticleTitleRenamedEvent;
import com.example.domain.article.events.CategoryChangedEvent;
import com.example.domain.article.events.ParagraphEditedEvent;
import com.example.domain.article.events.ParagraphRemovedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
class ArticlePartialUpdater {

  private final NamedParameterJdbcTemplate jdbcTemplate;

  ArticlePartialUpdater(
    NamedParameterJdbcTemplate jdbcTemplate
  ) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @EventListener
  int updateTitle(ArticleTitleRenamedEvent event) {
    final var mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("title", event.title());
    mapSqlParameterSource.addValue("id", event.articleId());
    mapSqlParameterSource.addValue("previous", event.previousVersion());
    mapSqlParameterSource.addValue("version", event.previousVersion() + 1);

    return jdbcTemplate.update(
      """
        UPDATE ARTICLE SET TITLE = :title  VERSION = :version
        WHERE ID = :id AND VERSION = :previous
        """,
      mapSqlParameterSource
    );
  }

  @EventListener
  int deleteParagraph(ParagraphRemovedEvent event) {
    final var mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("id", event.articleId());
    mapSqlParameterSource.addValue("previous", event.previousVersion());
    mapSqlParameterSource.addValue("version", event.previousVersion() + 1);

    jdbcTemplate.update(
      "UPDATE ARTICLE SET VERSION = :version WHERE ID = :id AND VERSION = :previous",
      mapSqlParameterSource
    );

    return jdbcTemplate.update(
      "DELETE FROM PARAGRAPH WHERE ID  = :id", new MapSqlParameterSource("id", event.paragraphId())
    );
  }

  @EventListener
  int updateParagraph(ParagraphEditedEvent event) {
    final var mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("id", event.articleId());
    mapSqlParameterSource.addValue("previous", event.previousVersion());
    mapSqlParameterSource.addValue("version", event.previousVersion() + 1);

    jdbcTemplate.update(
      "UPDATE ARTICLE SET VERSION = :version WHERE ID = :id AND VERSION = :previous",
      mapSqlParameterSource
    );

    final var params = new MapSqlParameterSource();
    params.addValue("id", event.paragraphId());
    params.addValue("text", event.text());
    //TODO: we can add Version and o/l for paragraphs skipped for simplicity

    return jdbcTemplate.update(
      "UPDATE PARAGRAPH SET TEXT =:text WHERE ID = :id", params
    );
  }

  @EventListener
  int updateRate(ArticleRateChangedEvent event) {
    final var mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("rating", event.rating());
    mapSqlParameterSource.addValue("count", event.count());
    mapSqlParameterSource.addValue("version", event.previousVersion() + 1);

    mapSqlParameterSource.addValue("id", event.articleId());
    mapSqlParameterSource.addValue("previous", event.previousVersion());

    return jdbcTemplate.update(
      """
        UPDATE ARTICLE SET RATING = :rating, VOTE_COUNT = :count, VERSION = :version
        WHERE ID = :id AND VERSION = :previous
        """,
      mapSqlParameterSource
    );
  }

  @EventListener
  int updateCategory(CategoryChangedEvent event) {
    final var mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("id", event.articleId());
    mapSqlParameterSource.addValue("categoryId", event.categoryId());
    mapSqlParameterSource.addValue("previous", event.previousVersion());
    mapSqlParameterSource.addValue("version", event.previousVersion() + 1);

    return jdbcTemplate.update(
      """
        UPDATE ARTICLE SET CATEGORY_ID = :categoryId, VERSION = :version
        WHERE ID = :id AND VERSION = :previous
        """,
      mapSqlParameterSource
    );
  }
}
