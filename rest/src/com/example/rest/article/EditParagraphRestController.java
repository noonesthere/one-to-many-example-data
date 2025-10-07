package com.example.rest.article;

import com.example.scenarios.dto.article.EditParagraphInput;
import com.example.scenarios.inbound.article.EditParagraph;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles/paragraph")
class EditParagraphRestController {

  private final EditParagraph editParagraph;

  EditParagraphRestController(EditParagraph editParagraph) {
    this.editParagraph = editParagraph;
  }

  @PostMapping("/{id}")
  public ResponseEntity<?> postArticle(@PathVariable("id") String id, @RequestBody EditParagraphWebModel webModel) {
    final EditParagraphInput input = webModel.to();
    editParagraph.execute(input);

    return ResponseEntity.ok().build();
  }
}
