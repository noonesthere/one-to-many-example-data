package com.example.data.jdbc.presentation.article;

public enum ArticlesViews {
  INDEX("index"),
  PAGE("articles"),
  CATEGORIES("categories");

  public final String templateName;

  ArticlesViews(String templateName) {
    this.templateName = templateName;
  }
}
