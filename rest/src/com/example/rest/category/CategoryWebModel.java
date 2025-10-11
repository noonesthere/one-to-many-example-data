package com.example.rest.category;

import com.example.scenarios.dto.category.CategoryReadModel;
import io.hypersistence.tsid.TSID;
import jakarta.annotation.Nullable;

import java.time.Instant;
import java.util.List;

public record CategoryWebModel(
  String id,
  String name,
  @Nullable Instant updatedAt,
  int counter
) {

  public static List<CategoryWebModel> from(List<CategoryReadModel> execute) {
    return execute.stream().map(CategoryWebModel::from).toList();
  }

  private static CategoryWebModel from(CategoryReadModel category) {
    final var id = category.id().value();
    return new CategoryWebModel(
      TSID.from(id).toString(),
      category.name().value(),
      category.updatedAt(),
      category.counter()
    );
  }

}
