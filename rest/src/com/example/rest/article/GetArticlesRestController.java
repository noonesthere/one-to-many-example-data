package com.example.rest.article;

import com.example.scenarios.inbound.article.GetArticles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articles")
class GetArticlesRestController {

  private final GetArticles getArticles;

  GetArticlesRestController(GetArticles getArticles) {
    this.getArticles = getArticles;
  }


  @GetMapping
  public ResponseEntity<?> get() {
    return ResponseEntity.ok(getArticles.execute()); //Skipped mapping to WebModel
  }
}
