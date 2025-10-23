package com.example.domain.category;

import com.example.common.types.DomainEvent;
import com.example.domain.category.commands.CreateCategoryCommand;
import com.example.domain.category.commands.RenameCategoryCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CategoryTest {


  @Test
  void testCreateCategory() {

    final List<DomainEvent> events = new ArrayList<>();
    // A
    final var id = new CategoryId(1L);
    final var name = new CategoryName("Category");
    final var createCategoryCommand = new CreateCategoryCommand(id, name);

    // A
    final var category = Category.create(createCategoryCommand);
    events.addAll(category.popEvents());

    // A
    Assertions.assertEquals(id, category.id);
    Assertions.assertEquals(name, category.name());

    CategoryName rEnamedCategoryName = new CategoryName("REnamed Category Name");
    category.rename(new RenameCategoryCommand(id, rEnamedCategoryName));
    events.addAll(category.popEvents());

    CategoryName rEnamedCategoryName1 = new CategoryName("REnamed Category Name");
    category.rename(new RenameCategoryCommand(id, rEnamedCategoryName1));
    events.addAll(category.popEvents());

    final var rebuilded = Category.rebuild(events);

    Assertions.assertEquals(category.id, rebuilded.id);
    Assertions.assertEquals(category.name(), rebuilded.name());
    Assertions.assertEquals(category.version(), rebuilded.version());
  }
}
