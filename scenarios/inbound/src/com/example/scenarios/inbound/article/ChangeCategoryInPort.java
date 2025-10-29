package com.example.scenarios.inbound.article;

import com.example.scenarios.dto.article.ChangeCategoryInput;

public interface ChangeCategoryInPort {
  void execute(ChangeCategoryInput input);
}
