package com.example.data.jdbc.presentation.article;


import com.example.scenarios.inbound.article.GetArticlesInPort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class IndexController {

  private final GetArticlesInPort getArticlesInPort;

  IndexController(GetArticlesInPort getArticlesInPort) {
    this.getArticlesInPort = getArticlesInPort;
  }

  @GetMapping("/")
  public String index(Model model) {
    final var articles = getArticlesInPort.execute();
    model.addAttribute("articles", ArticleViewWebModel.from(articles));

    return ArticlesViews.INDEX.templateName;
  }
}
