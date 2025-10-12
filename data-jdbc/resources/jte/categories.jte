@import com.example.data.jdbc.presentation.category.CategoryViewWebModel

@param java.util.List<com.example.data.jdbc.presentation.category.CategoryViewWebModel> categories

<header class="bg-white">
  <h1> Categories</h1>
</header>

<main>
  <div class="mx-auto py-6 sm:px-6 lg:px-8">
    <div class="overflow-x-auto">
      @for(CategoryViewWebModel category: categories)
        @template.category-part(category = category)
      @endfor
    </div>
  </div>
</main>
