package com.example.domain.category.commands;

import com.example.domain.category.CategoryId;
import com.example.domain.category.CategoryName;

public record RenameCategoryCommand(
  CategoryId categoryId,
  CategoryName name
) {
}
