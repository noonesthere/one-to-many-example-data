package com.example.rest.category;

import com.example.scenarios.inbound.category.RenameCategoryInPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/categories")
class RenameCategoryRestController {

  private final RenameCategoryInPort renameCategoryInPort;

  RenameCategoryRestController(RenameCategoryInPort renameCategoryInPort) {
    this.renameCategoryInPort = renameCategoryInPort;
  }

  @PostMapping("/{id}")
  public ResponseEntity<?> rename(@PathVariable("id")  String id, @RequestBody RenameCategoryWebModel webModel) {
    Objects.requireNonNull(id, "id must not be null");

    if (!id.equals(webModel.id())) {
      return ResponseEntity.badRequest()
        .body("Path variable id does not match request body id");
    }

    renameCategoryInPort.execute(webModel.toDto());

    return ResponseEntity.ok().build();
  }
}
