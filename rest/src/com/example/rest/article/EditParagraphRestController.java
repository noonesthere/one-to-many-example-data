package com.example.rest.article;

import com.example.scenarios.inbound.article.EditParagraph;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class EditParagraphRestController {

  private final EditParagraph editParagraph;

  EditParagraphRestController(EditParagraph editParagraph) {
    this.editParagraph = editParagraph;
  }

  @PostMapping("/api/articles/{articleId}/paragraphs/{paragraphId}")
  public ResponseEntity<?> editParagraph(
    @PathVariable("articleId") String articleId,
    @PathVariable("paragraphId") String paragraphId,
    @RequestBody EditParagraphWebModel webModel
  ) {
    editParagraph.execute(webModel.to());
    return ResponseEntity.ok().build();
  }
}
