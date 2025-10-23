package com.example.data.jdbc.persistence.article;

import com.example.domain.article.events.ArticleRateChangedEvent;
import com.example.domain.article.events.ArticleTitleRenamedEvent;
import com.example.domain.article.events.CategoryChangedEvent;
import com.example.domain.article.events.ParagraphEditedEvent;
import com.example.domain.article.events.ParagraphRemovedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

@Component
class ArticlePartialUpdater {

  private final JdbcClient jdbcClient;

  ArticlePartialUpdater(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }

  @EventListener
  int updateTitle(ArticleTitleRenamedEvent event) {
    int update = jdbcClient.sql(
        """
          UPDATE ARTICLE SET TITLE = :title  VERSION = :version
          WHERE ID = :id AND VERSION = :previous
          """
      ).param("title", event.title())
      .param("id", event.articleId())
      .param("previous", event.previousVersion())
      .param("version", event.previousVersion() + 1).update();
    if (update != 1) {
      throw new RuntimeException("Article title update failed");
    }
    return update;
  }

  @EventListener
  int deleteParagraph(ParagraphRemovedEvent event) {
    final int update = jdbcClient.sql(
        "UPDATE ARTICLE SET VERSION = :version WHERE ID = :id AND VERSION = :previous"
      ).param("id", event.articleId())
      .param("previous", event.previousVersion())
      .param("version", event.previousVersion() + 1)
      .update();

    return jdbcClient.sql(
      "DELETE FROM PARAGRAPH WHERE ID  = :id"
    ).param("id", event.paragraphId()).update();
  }

  @EventListener
  int updateParagraph(ParagraphEditedEvent event) {
    int update = jdbcClient.sql(
        "UPDATE ARTICLE SET VERSION = :version WHERE ID = :id AND VERSION = :previous"
      ).param("id", event.articleId())
      .param("previous", event.previousVersion())
      .param("version", event.previousVersion() + 1).update();

    //TODO: we can add Version and o/l for paragraphs skipped for simplicity
    return jdbcClient.sql("UPDATE PARAGRAPH SET TEXT =:text WHERE ID = :id")
      .param("id", event.paragraphId())
      .param("text", event.text())
      .update();
  }

  @EventListener
  int updateRate(ArticleRateChangedEvent event) {
    return jdbcClient.sql(
        """
          UPDATE ARTICLE SET RATING = :rating, VOTE_COUNT = :count, VERSION = :version
          WHERE ID = :id AND VERSION = :previous
          """
      )
      .param("rating", event.rating())
      .param("count", event.count())
      .param("version", event.previousVersion() + 1)
      .param("id", event.articleId())
      .param("previous", event.previousVersion())
      .update();
  }

  @EventListener
  int updateCategory(CategoryChangedEvent event) {

    return jdbcClient.sql(
        """
          UPDATE ARTICLE SET CATEGORY_ID = :categoryId, VERSION = :version
          WHERE ID = :id AND VERSION = :previous
          """
      )
      .param("id", event.articleId())
      .param("categoryId", event.categoryId())
      .param("previous", event.previousVersion())
      .param("version", event.previousVersion() + 1)
      .update();
  }
}
