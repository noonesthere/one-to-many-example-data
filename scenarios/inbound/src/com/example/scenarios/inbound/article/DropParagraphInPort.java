package com.example.scenarios.inbound.article;

import com.example.scenarios.dto.article.DropParagraphInput;

public interface DropParagraphInPort {
  void execute(DropParagraphInput dropParagraphInput);
}
