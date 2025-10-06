package com.example.rest.category;

import com.example.scenarios.inbound.category.GetCategories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class GetCategoriesRestController {
  private final GetCategories getCategories;

  public GetCategoriesRestController(GetCategories getCategories) {
    this.getCategories = getCategories;
  }

  @GetMapping
  public ResponseEntity<?> get() {
    return ResponseEntity.ok(CategoryWebModel.from(getCategories.execute()));
  }
}
