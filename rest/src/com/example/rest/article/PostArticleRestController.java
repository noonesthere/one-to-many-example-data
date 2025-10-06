package com.example.rest.article;

import com.example.scenarios.dto.ArticleDto;
import com.example.scenarios.inbound.article.PostArticle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articles")
class PostArticleRestController {

  private final PostArticle postArticle;

  PostArticleRestController(PostArticle postArticle) {
    this.postArticle = postArticle;
  }

  @PostMapping
  public ResponseEntity<?> postArticle(){
    postArticle.post(new ArticleDto());

    return ResponseEntity.ok().build();
  }
}
