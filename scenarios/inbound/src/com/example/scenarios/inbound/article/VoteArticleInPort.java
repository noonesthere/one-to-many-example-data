package com.example.scenarios.inbound.article;

import com.example.scenarios.dto.article.VoteArticleInput;

public interface VoteArticleInPort {
  void execute(VoteArticleInput input);
}
