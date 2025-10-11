package com.example.rest.article;

import com.example.scenarios.dto.article.ChangeCategoryInput;
import com.example.scenarios.dto.article.RenameTitleInput;
import com.example.scenarios.inbound.article.ChangeCategory;
import com.example.scenarios.inbound.article.RenameTitle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
class CategoryChangeRestController {

  private final ChangeCategory changeCategory;

  CategoryChangeRestController(ChangeCategory changeCategory) {
    this.changeCategory = changeCategory;
  }

  @PostMapping("/{id}/category")
  public ResponseEntity<?> rename(@PathVariable("id") String id, @RequestBody ChangeCategoryWebModel webModel) {
    ChangeCategoryInput input = webModel.to();
    changeCategory.execute(input);

    return ResponseEntity.ok().build();
  }
}
