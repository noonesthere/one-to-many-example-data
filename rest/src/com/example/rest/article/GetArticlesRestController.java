package com.example.rest.article;

import com.example.scenarios.inbound.article.GetArticlesInPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articles")
class GetArticlesRestController {

  private final GetArticlesInPort getArticlesInPort;

  GetArticlesRestController(GetArticlesInPort getArticlesInPort) {
    this.getArticlesInPort = getArticlesInPort;
  }


  @GetMapping
  public ResponseEntity<?> get() {
    final var dtos = getArticlesInPort.execute();
    return ResponseEntity.ok(GetArticleWebModel.from(dtos)); //Skipped mapping to WebModel
  }
}
