package com.example.scenarios.article;

import com.example.domain.article.ParagraphId;
import com.example.domain.article.ParagraphIdProvider;
import io.hypersistence.tsid.TSID;
import jakarta.inject.Named;

@Named
class ParagraphTsIdProvider implements ParagraphIdProvider {

  @Override
  public ParagraphId provide() {
    return new ParagraphId(TSID.Factory.getTsid().toLong());
  }

}
