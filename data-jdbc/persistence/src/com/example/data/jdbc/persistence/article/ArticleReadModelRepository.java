package com.example.data.jdbc.persistence.article;

import com.example.common.utilities.CollectionsUtils;
import com.example.scenarios.dto.article.ArticleReadModel;
import com.example.scenarios.outbound.article.ArticlesExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
class ArticleReadModelRepository implements ArticlesExtractor {

  private final NamedParameterJdbcTemplate jdbcTemplate;

  ArticleReadModelRepository(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<ArticleReadModel> findAll() {

    final List<ArticleReadModelEntity> articles = jdbcTemplate.query("""
        SELECT A.ID as ID, A.TITLE as TITLE, A.RATING as RATING, C.NAME AS NAME
         FROM ARTICLE A INNER JOIN CATEGORY C ON A.CATEGORY_ID = C.ID
        """,
      (rs, rowNum) -> new ArticleReadModelEntity(
        rs.getLong("ID"),
        rs.getString("TITLE"),
        rs.getDouble("RATING"),
        rs.getString("NAME")
      )
    );

    final Map<Long, List<String>> paragraphsPerArticle = CollectionsUtils.streamOf(
      jdbcTemplate.query("SELECT *  FROM PARAGRAPH", (rs, rowNum) -> new ParagraphEntity(
        rs.getLong("ARTICLE_ID"),
        rs.getLong("ID"),
        rs.getString("text"),
        rs.getLong("VERSION")
      ))
    ).collect(
      Collectors.groupingBy(
        ParagraphEntity::articleId,
        Collectors.mapping(
          ParagraphEntity::text,
          Collectors.toList()
        )
      )
    );

    return CollectionsUtils.streamOf(articles)
      .map(a -> new ArticleReadModel(
          a.id(),
          a.title(),
          paragraphsPerArticle.get(a.id()),
          a.rating(),
          a.categoryName()
        )
      ).toList();
  }
}
