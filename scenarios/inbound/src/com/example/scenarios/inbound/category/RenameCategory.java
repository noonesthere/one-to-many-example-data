package com.example.scenarios.inbound.category;

import com.example.domain.category.Category;
import com.example.scenarios.dto.category.RenamingCategoryDto;

public interface RenameCategory {
  Category execute(RenamingCategoryDto dto);
}
