package com.example.data.jdbc.presentation.article;

public enum ArticlesViews {
  INDEX("index"),
  PAGE("articles");

  public final String templateName;

  ArticlesViews(String templateName) {
    this.templateName = templateName;
  }
}
