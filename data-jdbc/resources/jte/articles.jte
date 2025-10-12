@import com.example.data.jdbc.presentation.article.ArticleViewWebModel

@param java.util.List<com.example.data.jdbc.presentation.article.ArticleViewWebModel> articles

<header class="bg-white">
  <h1> Articles</h1>
</header>

<main>
  <div class="mx-auto py-6 sm:px-6 lg:px-8">
    <div class="overflow-x-auto">
      @for(ArticleViewWebModel article: articles)
        @template.article-part(article = article)
      @endfor
    </div>
  </div>
</main>
