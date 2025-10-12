package com.example.data.jdbc.presentation.category;


import com.example.data.jdbc.presentation.article.ArticlesViews;
import com.example.scenarios.inbound.category.GetCategories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class ListCategories {

  private final GetCategories getCategories;

  ListCategories(GetCategories getCategories) {
    this.getCategories = getCategories;
  }

  @GetMapping("/categories")
  public String categories(Model model) {
    model.addAttribute("categories", CategoryViewWebModel.from(getCategories.execute()));
    return ArticlesViews.CATEGORIES.templateName;
  }
}
