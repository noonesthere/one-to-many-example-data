package com.example.rest.article;

import com.example.scenarios.dto.article.VoteArticleInput;
import com.example.scenarios.inbound.article.VoteArticleInPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
class VoteArticleRestController {

  private final VoteArticleInPort voteArticleInPort;

  VoteArticleRestController(VoteArticleInPort voteArticleInPort) {
    this.voteArticleInPort = voteArticleInPort;
  }

  @PostMapping("/{id}")
  public ResponseEntity<?> postArticle(@PathVariable("id") String id, @RequestBody VoteArticleWebModel webModel) {
    VoteArticleInput input = webModel.to();
    voteArticleInPort.execute(input);

    return ResponseEntity.ok().build();
  }
}
