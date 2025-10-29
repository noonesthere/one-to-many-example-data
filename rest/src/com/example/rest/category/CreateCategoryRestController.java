package com.example.rest.category;

import com.example.domain.category.CategoryId;
import com.example.scenarios.inbound.category.CreateCategoryInPort;
import io.hypersistence.tsid.TSID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
class CreateCategoryRestController {

  private final CreateCategoryInPort createCategoryInPort;

  CreateCategoryRestController(CreateCategoryInPort createCategoryInPort) {
    this.createCategoryInPort = createCategoryInPort;
  }

  @PostMapping
  public ResponseEntity<String> create(@RequestBody CreateCategoryWebModel webModel) {
    final CategoryId categoryId = createCategoryInPort.execute(webModel.name());
    final var body = "Location link: " + TSID.from(categoryId.value()).toString();

    return ResponseEntity.ok().body(body);
  }
}
