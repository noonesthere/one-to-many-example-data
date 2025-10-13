package com.example.plain.jdbc.persistence;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Objects;

import static com.example.plain.jdbc.persistence.CategoryRepositoryHelper.DELETED_AT_COLUMN_NAME;
import static com.example.plain.jdbc.persistence.CategoryRepositoryHelper.ID_COLUMN_NAME;
import static com.example.plain.jdbc.persistence.CategoryRepositoryHelper.NAME_COLUMN_NAME;
import static com.example.plain.jdbc.persistence.CategoryRepositoryHelper.VERSION_COLUMN_NAME;

public class CategoryRowMapper implements RowMapper<CategoryEntity> {

  @Override
  public CategoryEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
    final long id = rs.getLong(ID_COLUMN_NAME);
    final String name = rs.getString(NAME_COLUMN_NAME);
    final Instant deletedAt = Objects.nonNull(rs.getTimestamp(DELETED_AT_COLUMN_NAME))
      ? rs.getTimestamp(DELETED_AT_COLUMN_NAME).toInstant()
      : null;
    final long version = rs.getLong(VERSION_COLUMN_NAME);

    return new CategoryEntity(id, name, deletedAt, version);
  }
}
