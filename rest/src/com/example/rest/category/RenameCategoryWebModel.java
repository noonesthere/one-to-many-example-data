package com.example.rest.category;

import com.example.scenarios.dto.category.RenamingCategoryInput;
import io.hypersistence.tsid.TSID;

public record RenameCategoryWebModel(String id, String name) {
  RenamingCategoryInput toDto() {
    final TSID tsid = TSID.from(id);
    return new RenamingCategoryInput(tsid.toLong(), name);
  }
}
