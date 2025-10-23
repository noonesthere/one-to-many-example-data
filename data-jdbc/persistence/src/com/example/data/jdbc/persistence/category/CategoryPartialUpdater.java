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


  @EventListener
  void renameCategory(CategoryRenamedEvent event) {
    final int update = jdbcClient.sql(
        "UPDATE CATEGORY SET NAME = :name, version = :version WHERE ID = :id AND version = :previous"
      ).param("name", event.categoryName()).
      param("version", event.previousVersion() + 1).
      param("id", event.categoryId()).
      param("previous", event.previousVersion()).update();

    if (update != 1) {
      throw new RuntimeException("Optimistic update failure");
    }
  }
}
