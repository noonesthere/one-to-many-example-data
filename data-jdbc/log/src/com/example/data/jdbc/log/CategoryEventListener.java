package com.example.data.jdbc.log;

import com.example.data.jdbc.log.persistence.H2CategoryEventRepositoryAdapter;
import com.example.domain.category.events.CategoryCreatedEvent;
import com.example.domain.category.events.CategoryEvent;
import com.example.domain.category.events.CategoryRenamedEvent;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;


@Service
class CategoryEventListener {

  private final H2CategoryEventRepositoryAdapter adapter;

  CategoryEventListener(H2CategoryEventRepositoryAdapter adapter) {
    this.adapter = adapter;
  }

  @ApplicationModuleListener
  public void on(CategoryCreatedEvent event) {
    handle(event);
  }

  @ApplicationModuleListener
  public void on(CategoryRenamedEvent event) {
    handle(event);
  }

  private void handle(CategoryEvent event) {
    adapter.persist(event);

    System.out.println("Received event in right handler: " + event.getClass());
  }
}
