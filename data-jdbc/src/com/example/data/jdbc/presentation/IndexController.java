package com.example.data.jdbc.presentation;


import com.example.data.jdbc.presentation.article.ArticleViewWebModel;
import com.example.data.jdbc.presentation.article.ArticlesViews;
import com.example.scenarios.inbound.article.GetArticles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

  private final GetArticles getArticles;

  public IndexController(GetArticles getArticles) {
    this.getArticles = getArticles;
  }

  @GetMapping("/")
  public String listProtocols(Model model) {
    final var articles = getArticles.execute();
    model.addAttribute("articles", ArticleViewWebModel.from(articles));

    return ArticlesViews.INDEX.templateName;
  }
}
