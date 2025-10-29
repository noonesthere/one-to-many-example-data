package com.example.scenarios.inbound.category;

import com.example.domain.category.Category;
import com.example.scenarios.dto.category.RenamingCategoryInput;

public interface RenameCategoryInPort {
  Category execute(RenamingCategoryInput dto);
}
