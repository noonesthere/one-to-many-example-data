package com.example.domain.category.events;

import com.example.common.types.DomainEvent;

public interface CategoryEvent extends DomainEvent {
  Long categoryId();
}
