package com.example.data.jdbc.persistence.category;

import com.example.domain.category.events.CategoryRenamedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
class CategoryPartialUpdater {

  private final NamedParameterJdbcTemplate jdbcTemplate;

  CategoryPartialUpdater(JdbcTemplate template) {
    this.jdbcTemplate = new NamedParameterJdbcTemplate(template);

  }

  @EventListener
  void renameCategory(CategoryRenamedEvent event) {
    final var mapSqlParameterSource = new MapSqlParameterSource();

    mapSqlParameterSource.addValue("name", event.categoryName());
    mapSqlParameterSource.addValue("version", event.previousVersion() + 1);
    mapSqlParameterSource.addValue("id", event.categoryId());
    mapSqlParameterSource.addValue("previous", event.previousVersion());

    final int update = jdbcTemplate.update(
      "UPDATE CATEGORY SET NAME = :name, version = :version WHERE ID = :id AND version = :previous",
      mapSqlParameterSource
    );

    if (update != 1) {
      throw new RuntimeException("Optimistic update failure");
    }
  }
}
