package com.example.scenarios.inbound.category;

import com.example.domain.category.Category;
import com.example.scenarios.dto.category.CategoryReadModel;

import java.util.List;

public interface GetCategories {
  List<CategoryReadModel> execute();
}
