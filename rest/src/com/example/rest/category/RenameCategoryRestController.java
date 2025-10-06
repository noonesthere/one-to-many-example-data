package com.example.rest.category;

import com.example.scenarios.dto.RenamingCategoryDto;
import com.example.scenarios.inbound.category.RenameCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/categories")
class RenameCategoryRestController {

  private final RenameCategory renameCategory;

  RenameCategoryRestController(RenameCategory renameCategory) {
    this.renameCategory = renameCategory;
  }

  @PostMapping("/{id}")
  public ResponseEntity<?> rename(@PathVariable("id")  String id, @RequestBody RenameCategoryWebModel webModel) {
    Objects.requireNonNull(id, "id must not be null");

    if (!id.equals(webModel.id())) {
      return ResponseEntity.badRequest()
        .body("Path variable id does not match request body id");
    }

    RenamingCategoryDto dto = webModel.toDto();
    renameCategory.execute(dto);

    return ResponseEntity.ok().build();
  }
}
