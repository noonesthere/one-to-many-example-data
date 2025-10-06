package com.example.plain.jdbc.persistence;

import com.example.domain.category.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class CategoryRepository {

  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert insert;

  CategoryRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    insert = new SimpleJdbcInsert(jdbcTemplate);
    insert.setTableName("CATEGORY");
  }

  public List<Category> selectAll() {
    final List<CategoryEntity> entities = jdbcTemplate.query("SELECT * FROM CATEGORY", new CategoryRowMapper());
    return CategoryEntity.from(entities);
  }

  public Category insert(Category category) {

    final MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue(CategoryRepositoryHelper.ID_COLUMN_NAME, category.id.asLongValue());
    parameters.addValue(CategoryRepositoryHelper.NAME_COLUMN_NAME, category.name.asStringValue());
    parameters.addValue(CategoryRepositoryHelper.UPDATED_AT_COLUMN_NAME, category.updatedAt());
    parameters.addValue(CategoryRepositoryHelper.DELETED_AT_COLUMN_NAME, category.deletedAt());
    parameters.addValue(CategoryRepositoryHelper.VERSION_COLUMN_NAME, category.version().value());

    insert.execute(parameters);

    return category;
  }
}
