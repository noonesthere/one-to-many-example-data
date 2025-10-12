@import com.example.data.jdbc.presentation.article.ArticleViewWebModel

@param java.util.List<com.example.data.jdbc.presentation.article.ArticleViewWebModel> articles


@for(ArticleViewWebModel article: articles)
  @template.article-part(article = article)
@endfor
