package com.example.scenarios.inbound.article;

import com.example.scenarios.dto.article.RenameTitleInput;

public interface RenameTitleInPort {
  void execute(RenameTitleInput input);
}
