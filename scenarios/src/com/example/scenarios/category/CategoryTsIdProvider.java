package com.example.scenarios.category;

import com.example.domain.category.CategoryId;
import com.example.domain.category.CategoryIdProvider;
import io.hypersistence.tsid.TSID;
import jakarta.inject.Named;

@Named
class CategoryTsIdProvider implements CategoryIdProvider {
  @Override
  public CategoryId provide() {
    final long aLong = TSID.Factory.getTsid().toLong();
    return CategoryId.from(aLong);
  }

}
