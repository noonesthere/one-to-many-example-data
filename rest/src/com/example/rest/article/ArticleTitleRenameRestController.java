package com.example.rest.article;

import com.example.scenarios.dto.article.RenameTitleInput;
import com.example.scenarios.inbound.article.RenameTitleInPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
class ArticleTitleRenameRestController {

  private final RenameTitleInPort renameTitleInPort;

  ArticleTitleRenameRestController(RenameTitleInPort renameTitleInPort) {
    this.renameTitleInPort = renameTitleInPort;
  }

  @PostMapping("/{id}/title")
  public ResponseEntity<?> rename(@PathVariable("id") String id, @RequestBody RenameArticleWebModel webModel) {
    RenameTitleInput input = webModel.to();
    renameTitleInPort.execute(input);

    return ResponseEntity.ok().build();
  }
}
