@import com.example.data.jdbc.presentation.article.ArticleViewWebModel
@param com.example.data.jdbc.presentation.article.ArticleViewWebModel article

<div id="article-${article.id()}"
     class="p-6 bg-white rounded-2xl shadow-sm hover:shadow-md transition-shadow duration-200">

  <div class="mb-3">
    <h2 class="text-xl font-semibold text-gray-900">${article.title()}</h2>
    <p class="text-sm text-gray-500 mt-1">${article.categoryName()}</p>
  </div>

  <div class="prose prose-sm max-w-none text-gray-800 leading-relaxed">
    @for (String paragraph : article.paragraphs())
      <p>${paragraph}</p>
    @endfor
  </div>

  <div class="mt-4 flex items-center justify-between text-sm text-gray-500">
    <span class="font-medium">Rating: ${article.rating()}</span>
    <div class="w-24 h-1.5 bg-gray-200 rounded-full overflow-hidden">
      <div class="h-full bg-blue-500" style="width:@article.rating()%"></div>
    </div>
  </div>

</div>
