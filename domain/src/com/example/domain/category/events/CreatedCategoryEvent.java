package com.example.domain.category.events;

import com.example.common.types.DomainEvent;

public class CreatedCategoryEvent extends DomainEvent {

  public final Long categoryId;
  public final String categoryName;

  public CreatedCategoryEvent(Long categoryId, String categoryName) {
    this.categoryId = categoryId;
    this.categoryName = categoryName;

  }
}
