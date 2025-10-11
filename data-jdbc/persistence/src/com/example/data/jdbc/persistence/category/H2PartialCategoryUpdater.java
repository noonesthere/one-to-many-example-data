package com.example.data.jdbc.persistence.category;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class H2PartialCategoryUpdater {

  private final NamedParameterJdbcTemplate jdbcTemplate;

  public H2PartialCategoryUpdater(JdbcTemplate template) {
    this.jdbcTemplate = new NamedParameterJdbcTemplate(template);

  }

  CategoryEntity renameCategory(CategoryEntity entity) {
    final var mapSqlParameterSource = new MapSqlParameterSource();

    mapSqlParameterSource.addValue("name", entity.name());
    mapSqlParameterSource.addValue("updatedAt", entity.updatedAt());
    mapSqlParameterSource.addValue("version", entity.version() + 1);
    mapSqlParameterSource.addValue("id", entity.getId());
    mapSqlParameterSource.addValue("previous", entity.version());

    final int update = jdbcTemplate.update(
      "UPDATE CATEGORY SET NAME = :name, UPDATED_AT = :updatedAt, version = :version WHERE ID = :id AND version = :previous",
      mapSqlParameterSource
    );

    if (update != 1) {
      throw new RuntimeException("Optimistic update failure");
    }
    return entity;
  }
}
