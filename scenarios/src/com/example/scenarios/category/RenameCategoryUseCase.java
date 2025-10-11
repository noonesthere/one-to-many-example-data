package com.example.scenarios.category;

import com.example.domain.category.Category;
import com.example.domain.category.CategoryId;
import com.example.domain.category.CategoryName;
import com.example.domain.category.commands.RenameCategoryCommand;
import com.example.scenarios.dto.category.RenamingCategoryDto;
import com.example.scenarios.inbound.category.RenameCategory;
import com.example.scenarios.outbound.category.CategoryExtractor;
import com.example.scenarios.outbound.category.CategoryUpdater;
import jakarta.inject.Named;

@Named
class RenameCategoryUseCase implements RenameCategory {

  private final CategoryUpdater updater;
  private final CategoryExtractor extractor;

  RenameCategoryUseCase(CategoryUpdater updater, CategoryExtractor extractor) {
    this.updater = updater;
    this.extractor = extractor;
  }

  @Override
  public Category execute(RenamingCategoryDto dto) {
    final var id = new CategoryId(dto.id());
    final var name = new CategoryName(dto.name());
    final var category = extractor.get(id);

    category.rename(new RenameCategoryCommand(id, name));

    return updater.update(category); // I know that we can return just locator or not return anything but...
  }
}
