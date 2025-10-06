package com.example.data.jdbc.log;

import com.example.data.jdbc.log.persistence.H2CategoryEventRepositoryAdapter;
import com.example.domain.category.events.CategoryEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
class CategoryEventListener {

  private final H2CategoryEventRepositoryAdapter adapter;

  CategoryEventListener(H2CategoryEventRepositoryAdapter adapter) {
    this.adapter = adapter;
  }

  @EventListener
  public void on(CategoryEvent event) {
    adapter.persist(event);

    System.out.println("Received event in right handler: " + event.getClass());
  }
}
