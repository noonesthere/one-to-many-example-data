package com.example.data.jdbc.persistence.article;

import com.example.common.utilities.CollectionsUtils;
import com.example.scenarios.dto.article.ArticleReadModel;
import com.example.scenarios.outbound.article.ArticlesExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.data.jdbc.persistence.article.ArticleReadModelRepositoryHelper.ARTICLE_ROW_MAPPER;
import static com.example.data.jdbc.persistence.article.ArticleReadModelRepositoryHelper.PARAGRAPH_ROW_MAPPER;
import static com.example.data.jdbc.persistence.article.ArticleReadModelRepositoryHelper.SELECT_ARTICLES;
import static com.example.data.jdbc.persistence.article.ArticleReadModelRepositoryHelper.SELECT_FROM_PARAGRAPH;

@Component
class ArticleReadModelRepository implements ArticlesExtractor {

  private final NamedParameterJdbcTemplate jdbcTemplate;

  ArticleReadModelRepository(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<ArticleReadModel> findAll() {

    final List<ArticleReadModelEntity> articles = jdbcTemplate.query(SELECT_ARTICLES, ARTICLE_ROW_MAPPER);

    final Map<Long, List<String>> paragraphsPerArticle = CollectionsUtils.streamOf(
      jdbcTemplate.query(SELECT_FROM_PARAGRAPH, PARAGRAPH_ROW_MAPPER)
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
