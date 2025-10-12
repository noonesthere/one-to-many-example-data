package com.example.data.jdbc.presentation.category;

import com.example.scenarios.dto.category.CategoryReadModel;
import io.hypersistence.tsid.TSID;

import java.util.List;

public record CategoryViewWebModel(String id, String name, int counter) {

  static List<CategoryViewWebModel> from(List<CategoryReadModel> categories) {
    return categories.stream().map(CategoryViewWebModel::from).toList();
  }

  private static CategoryViewWebModel from(CategoryReadModel category) {
    return new CategoryViewWebModel(
      TSID.from(category.id().value()).toString(),
      category.name().value(),
      category.counter()
    );
  }
}
