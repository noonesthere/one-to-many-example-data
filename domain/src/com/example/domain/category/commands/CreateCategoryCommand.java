package com.example.domain.category.commands;

import com.example.domain.category.CategoryId;
import com.example.domain.category.CategoryName;

public record CreateCategoryCommand(
  CategoryId categoryId,
  CategoryName categoryName
) {
}
