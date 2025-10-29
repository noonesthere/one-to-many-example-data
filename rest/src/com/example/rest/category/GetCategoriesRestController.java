package com.example.rest.category;

import com.example.scenarios.inbound.category.GetCategoriesInPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class GetCategoriesRestController {
  private final GetCategoriesInPort getCategoriesInPort;

  public GetCategoriesRestController(GetCategoriesInPort getCategoriesInPort) {
    this.getCategoriesInPort = getCategoriesInPort;
  }

  @GetMapping
  public ResponseEntity<?> get() {
    return ResponseEntity.ok(CategoryWebModel.from(getCategoriesInPort.execute()));
  }
}
