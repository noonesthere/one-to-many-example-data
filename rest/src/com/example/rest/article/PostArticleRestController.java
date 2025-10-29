package com.example.rest.article;

import com.example.domain.article.ArticleId;
import com.example.scenarios.inbound.article.PostArticleInPort;
import io.hypersistence.tsid.TSID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articles")
class PostArticleRestController {

  private final PostArticleInPort postArticleInPort;

  PostArticleRestController(PostArticleInPort postArticleInPort) {
    this.postArticleInPort = postArticleInPort;
  }


  @PostMapping
  public ResponseEntity<String> postArticle(@RequestBody PostArticleWebModel webModel) {
    final ArticleId articleId = postArticleInPort.execute(webModel.to());
    return ResponseEntity.ok()
      .body(TSID.from(articleId.value()).toString());
  }
}
