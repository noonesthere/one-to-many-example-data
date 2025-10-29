package com.example.rest.article;

import com.example.scenarios.inbound.article.EditParagraphInPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class EditParagraphRestController {

  private final EditParagraphInPort editParagraphInPort;

  EditParagraphRestController(EditParagraphInPort editParagraphInPort) {
    this.editParagraphInPort = editParagraphInPort;
  }

  @PostMapping("/api/articles/{articleId}/paragraphs/{paragraphId}")
  public ResponseEntity<?> editParagraph(
    @PathVariable("articleId") String articleId,
    @PathVariable("paragraphId") String paragraphId,
    @RequestBody EditParagraphWebModel webModel
  ) {
    editParagraphInPort.execute(webModel.to());
    return ResponseEntity.ok().build();
  }
}
