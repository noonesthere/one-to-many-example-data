package com.example.domain.category;

import com.example.domain.category.commands.CreateCategoryCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CategoryTest {


  @Test
  void testCreateCategory() {
    final var id = new CategoryId(1L);
    final var name = new CategoryName("Category");
    final var createCategoryCommand = new CreateCategoryCommand(id, name);
    final var category = Category.create(createCategoryCommand);

    Assertions.assertEquals(id, category.id);
    Assertions.assertEquals(name, category.name());

    final var events = category.popEvents();

    final var rebuilded = Category.rebuild(events);

    Assertions.assertEquals(category.id, rebuilded.id);
    Assertions.assertEquals(category.name(), rebuilded.name());
//    Assertions.assertEquals(category.version(), rebuilded.version()); TODO: fix me
  }
}
