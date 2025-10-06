package com.example.rest.category;

import com.example.domain.category.Category;
import io.hypersistence.tsid.TSID;

import java.util.List;

public record CategoryWebModel(
  String id,
  String name
) {

  public static List<CategoryWebModel> from(List<Category> execute) {
    return execute.stream().map(CategoryWebModel::from).toList();
  }

  private static CategoryWebModel from(Category category) {
    final var id = category.id.asLongValue();
    return new CategoryWebModel(TSID.from(id).toString(), category.name.asStringValue());
  }

}
