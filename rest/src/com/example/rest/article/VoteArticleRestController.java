package com.example.rest.article;

import com.example.scenarios.dto.article.VoteArticleInput;
import com.example.scenarios.inbound.article.VoteArticle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
class VoteArticleRestController {

  private final VoteArticle voteArticle;

  VoteArticleRestController(VoteArticle voteArticle) {
    this.voteArticle = voteArticle;
  }

  @PostMapping("/{id}")
  public ResponseEntity<?> postArticle(@PathVariable("id") String id, @RequestBody VoteArticleWebModel webModel) {
    VoteArticleInput input = webModel.to();
    voteArticle.exucute(input);

    return ResponseEntity.ok().build();
  }
}
