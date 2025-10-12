package com.example.data.jdbc.persistence.article;

import org.springframework.jdbc.core.RowMapper;

final class ArticleReadModelRepositoryHelper {

  static final String SELECT_ARTICLES = """
    SELECT A.ID as ID, A.TITLE as TITLE, A.RATING as RATING, C.NAME AS NAME
     FROM ARTICLE A INNER JOIN CATEGORY C ON A.CATEGORY_ID = C.ID
    """;
  static final String SELECT_FROM_PARAGRAPH = "SELECT *  FROM PARAGRAPH";

  static final RowMapper<ParagraphEntity> PARAGRAPH_ROW_MAPPER = (rs, rowNum) -> new ParagraphEntity(
    rs.getLong("ARTICLE_ID"),
    rs.getLong("ID"),
    rs.getString("text"),
    rs.getLong("VERSION")
  );

  static final RowMapper<ArticleReadModelEntity> ARTICLE_ROW_MAPPER = (rs, rowNum) -> new ArticleReadModelEntity(
    rs.getLong("ID"),
    rs.getString("TITLE"),
    rs.getDouble("RATING"),
    rs.getString("NAME")
  );

  private ArticleReadModelRepositoryHelper() {
    throw new IllegalStateException("Utility class should not be instantiated");
  }
}
