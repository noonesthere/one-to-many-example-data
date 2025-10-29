package com.example.scenarios.inbound.article;

import com.example.scenarios.dto.article.EditParagraphInput;

public interface EditParagraphInPort {
  void execute(EditParagraphInput input);
}
