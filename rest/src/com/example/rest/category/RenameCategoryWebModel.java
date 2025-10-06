package com.example.rest.category;

import com.example.scenarios.dto.RenamingCategoryDto;
import io.hypersistence.tsid.TSID;

public record RenameCategoryWebModel(String id, String name) {
  RenamingCategoryDto toDto() {
    final TSID tsid = TSID.from(id);
    return new RenamingCategoryDto(tsid.toLong(), name);
  }
}
