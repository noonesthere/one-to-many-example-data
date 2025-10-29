package com.example.rest.article;

import com.example.scenarios.dto.article.ChangeCategoryInput;
import com.example.scenarios.inbound.article.ChangeCategoryInPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
class CategoryChangeRestController {

  private final ChangeCategoryInPort changeCategoryInPort;

  CategoryChangeRestController(ChangeCategoryInPort changeCategoryInPort) {
    this.changeCategoryInPort = changeCategoryInPort;
  }

  @PostMapping("/{id}/category")
  public ResponseEntity<?> rename(@PathVariable("id") String id, @RequestBody ChangeCategoryWebModel webModel) {
    ChangeCategoryInput input = webModel.to();
    changeCategoryInPort.execute(input);

    return ResponseEntity.ok().build();
  }
}
