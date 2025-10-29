package com.example.scenarios.inbound.category;

import com.example.scenarios.dto.category.CategoryReadModel;

import java.util.List;

public interface GetCategoriesInPort {
  List<CategoryReadModel> execute();
}
