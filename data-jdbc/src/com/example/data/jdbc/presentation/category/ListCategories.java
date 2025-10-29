package com.example.data.jdbc.presentation.category;


import com.example.data.jdbc.presentation.article.ArticlesViews;
import com.example.scenarios.inbound.category.GetCategoriesInPort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class ListCategories {

  private final GetCategoriesInPort getCategoriesInPort;

  ListCategories(GetCategoriesInPort getCategoriesInPort) {
    this.getCategoriesInPort = getCategoriesInPort;
  }

  @GetMapping("/categories")
  public String categories(Model model) {
    model.addAttribute("categories", CategoryViewWebModel.from(getCategoriesInPort.execute()));
    return ArticlesViews.CATEGORIES.templateName;
  }
}
