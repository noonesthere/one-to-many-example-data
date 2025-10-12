package com.example.rest.article;

import com.example.scenarios.dto.article.DropParagraphInput;
import com.example.scenarios.inbound.article.DropParagraph;
import io.hypersistence.tsid.TSID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DropParagraphRestController {

  private final DropParagraph dropParagraph;

  DropParagraphRestController(DropParagraph dropParagraph) {
    this.dropParagraph = dropParagraph;
  }

  @DeleteMapping("/api/articles/{articleId}/paragraphs/{paragraphId}")
  public ResponseEntity<?> dropParagraph(
    @PathVariable("articleId") String articleId,
    @PathVariable("paragraphId") String paragraphId) {
    dropParagraph.execute(new DropParagraphInput(TSID.from(articleId).toLong(), TSID.from(paragraphId).toLong()));
    return ResponseEntity.ok().build();
  }
}
