package com.example.data.jdbc.persistence.category;

import com.example.domain.category.events.CategoryRenamedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

@Component
class CategoryPartialUpdater {

  private final JdbcClient jdbcClient;

  CategoryPartialUpdater(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }

  // STEP: 1 -> case 1: remove Category(id1, V1) case 2: rename Category(id1, V1) -> Category(id1, V1)
  // STEP: 2 -> case 1: remove Category(id1, V2, removed) case 2: rename Category(id1, V1) -> Category(id1, V2, removed) (update SET version = V2 where id = ? and version = V1(previous)
  // STEP: 3 -> case 1: (finished logic) case 2: rename Category(id1, V2, Name) -> Category(id1, V2, removed)  (update SET version = V2 where id = ? and version = V1(previous)
  @EventListener
  void renameCategory(CategoryRenamedEvent event) {
    final int update = jdbcClient.sql(
        "UPDATE CATEGORY SET NAME = :name, version = :version WHERE ID = :id AND version = :previous"
      ).param("name", event.categoryName()).
      param("version", event.version()).
      param("id", event.categoryId()).
      param("previous", event.version() - 1)
      .update();

    if (update!=1) {
      throw new RuntimeException("Optimistic update failure");
    }
  }
}
