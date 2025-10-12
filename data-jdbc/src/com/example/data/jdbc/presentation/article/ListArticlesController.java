package com.example.data.jdbc.presentation.article;


import com.example.scenarios.inbound.article.GetArticles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListArticlesController {

  private final GetArticles getArticles;

  public ListArticlesController(GetArticles getArticles) {
    this.getArticles = getArticles;
  }

  @GetMapping("/articles")
  public String listProtocols(Model model) {
    final var articles = getArticles.execute();
    model.addAttribute("articles", ArticleViewWebModel.from(articles));
    return ArticlesViews.PAGE.templateName;
  }
}
