package com.example.rest.category;

import com.example.domain.category.CategoryId;
import com.example.scenarios.inbound.category.CreateCategory;
import io.hypersistence.tsid.TSID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
class CreateCategoryRestController {

  private final CreateCategory createCategory;

  CreateCategoryRestController(CreateCategory createCategory) {
    this.createCategory = createCategory;
  }

  @PostMapping
  public ResponseEntity<String> create(@RequestBody CreateCategoryWebModel webModel) {
    final CategoryId categoryId = createCategory.execute(webModel.name());
    final var body = "Location link: " + TSID.from(categoryId.value()).toString();

    return ResponseEntity.ok().body(body);
  }
}
