package com.example.rest.article;

import com.example.scenarios.dto.article.RenameTitleInput;
import com.example.scenarios.inbound.article.RenameTitle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
class ArticleTitleRenameRestController {

  private final RenameTitle renameTitle;

  ArticleTitleRenameRestController(RenameTitle renameTitle) {
    this.renameTitle = renameTitle;
  }

  @PostMapping("/{id}/title")
  public ResponseEntity<?> rename(@PathVariable("id") String id, @RequestBody RenameArticleWebModel webModel) {
    RenameTitleInput input = webModel.to();
    renameTitle.execute(input);

    return ResponseEntity.ok().build();
  }
}
