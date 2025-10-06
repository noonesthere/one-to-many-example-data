package com.example.rest.category;

import com.example.scenarios.inbound.category.CreateCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
class CreateCategoryRestController {

  private final CreateCategory createCategory;

  CreateCategoryRestController(CreateCategory createCategory) {
    this.createCategory = createCategory;
  }

  @PostMapping
  public ResponseEntity<?> createCategory(@RequestParam String name) {
    createCategory.execute(name);
    //TODO: handle exceptions
    return ResponseEntity.ok().build();
  }
}
