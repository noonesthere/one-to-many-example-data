package com.example.domain.category.commands;

import com.example.domain.category.CategoryId;

public record CreateCategoryCommand(
  CategoryId categoryId,
  String categoryName
) {
}
