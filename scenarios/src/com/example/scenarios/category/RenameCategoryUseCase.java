package com.example.scenarios.category;

import com.example.domain.category.Category;
import com.example.domain.category.CategoryId;
import com.example.domain.category.CategoryName;
import com.example.domain.category.commands.RenameCategoryCommand;
import com.example.scenarios.dto.category.RenamingCategoryInput;
import com.example.scenarios.inbound.category.RenameCategoryInPort;
import com.example.scenarios.outbound.category.CategoryExtractorOutPort;
import com.example.scenarios.outbound.category.CategoryUpdaterOutPort;
import jakarta.inject.Named;

@Named
class RenameCategoryUseCase implements RenameCategoryInPort {

  private final CategoryUpdaterOutPort updater;
  private final CategoryExtractorOutPort extractor;

  RenameCategoryUseCase(CategoryUpdaterOutPort updater, CategoryExtractorOutPort extractor) {
    this.updater = updater;
    this.extractor = extractor;
  }

  @Override
  public Category execute(RenamingCategoryInput input) {
    final var id = new CategoryId(input.id());
    final var name = new CategoryName(input.name());
    final var command = new RenameCategoryCommand(id, name);
    final var category = extractor.get(id);

    category.handle(command);

    return updater.update(category); // I know that we can return just locator or not return anything but...
  }
}
