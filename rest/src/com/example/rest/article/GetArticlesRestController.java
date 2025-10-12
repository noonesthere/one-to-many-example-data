package com.example.rest.article;

import com.example.scenarios.dto.article.ArticleDto;
import com.example.scenarios.inbound.article.GetArticles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
class GetArticlesRestController {

  private final GetArticles getArticles;

  GetArticlesRestController(GetArticles getArticles) {
    this.getArticles = getArticles;
  }


  @GetMapping
  public ResponseEntity<?> get() {
    final var dtos = getArticles.execute();
    return ResponseEntity.ok(GetArticleWebModel.from(dtos)); //Skipped mapping to WebModel
  }
}
