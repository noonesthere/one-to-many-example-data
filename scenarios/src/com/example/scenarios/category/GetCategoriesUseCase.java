package com.example.scenarios.category;

import com.example.common.utilities.CollectionsUtils;
import com.example.domain.category.CategoryCounter;
import com.example.scenarios.dto.category.CategoryReadModel;
import com.example.scenarios.inbound.category.GetCategoriesInPort;
import com.example.scenarios.outbound.category.CategoriesCounterExtractor;
import com.example.scenarios.outbound.category.CategoriesExtractor;
import jakarta.inject.Named;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Named
class GetCategoriesUseCase implements GetCategoriesInPort {

  private final CategoriesExtractor extractor;
  private final CategoriesCounterExtractor counterExtractor;

  GetCategoriesUseCase(
    CategoriesExtractor extractor,
    CategoriesCounterExtractor counterExtractor
  ) {
    this.extractor = extractor;
    this.counterExtractor = counterExtractor;
  }

  @Override
  public List<CategoryReadModel> execute() {
    final var categories = extractor.getAll();
    final var countersByCategoryId = CollectionsUtils.streamOf(counterExtractor.getAll())
      .collect(
        Collectors.toMap(
          CategoryCounter::categoryId,
          Function.identity()
        )
      );

    return CollectionsUtils.streamOf(categories)
      .map(category -> {
        final var counter = countersByCategoryId.getOrDefault(category.id, CategoryCounter.newCounter(category.id));
        return CategoryReadModel.from(
          category,
          counter
        );
      })
      .toList();
  }

}
