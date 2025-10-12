@import com.example.data.jdbc.presentation.article.ArticleViewWebModel

@param java.util.List<com.example.data.jdbc.presentation.article.ArticleViewWebModel> articles

<!DOCTYPE html>
<html lang="uk">
<head>
  <meta charset="UTF-8">
  <title>Oblik</title>
  <script src="/js/htmx.min.js"></script>
  <script src="/js/tailwind.min.js"></script>
  <%--  <script src="/js/utility.js"></script>--%>

  <link rel="stylesheet" href="/css/styles.css">
</head>
<body class="min-h-full">
<div id="main" class="hidden md:block"></div>
<nav class="bg-gray-800">
  <div class="mx-auto px-4 sm:px-6 lg:px-8">
    <div class="flex h-16 items-center justify-between">
      <div class="flex items-center">
        <div class="flex-shrink-0">
        </div>
        <div class="hidden md:block">
          <div class="ml-10 flex items-baseline space-x-4">
            <a
              hx-get="/articles/add"
              hx-target="#content"
              class="text-gray-300 cursor-pointer hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">
              Create Article
            </a>
            <a
              hx-get="/articles/edit"
              hx-target="#content"
              class="text-gray-300 cursor-pointer hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">
              Edit articles
            </a>

            <a
              hx-get="/categories/edit"
              hx-target="#content"
              class="text-gray-300 cursor-pointer hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">
              Categories
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</nav>

<div id="content" class="flex-1 w-full p-6 bg-white">
  @template.articles(articles = articles)
</div>
</body>
</html>
