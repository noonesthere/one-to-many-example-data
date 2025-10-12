@import com.example.data.jdbc.presentation.category.CategoryViewWebModel

@param com.example.data.jdbc.presentation.category.CategoryViewWebModel category

<div id="category-${category.id()}"
     class="p-6 bg-white rounded-2xl shadow-sm hover:shadow-md transition-shadow duration-200">

  <div class="mb-3">
    <h3 class="text-xl font-semibold text-gray-900">${category.name()}</h3>
  </div>

  <div class="mt-4 flex items-center justify-between text-sm text-gray-500">
    <span class="font-medium">Count: ${category.counter()}</span>
    <div class="w-24 h-1.5 bg-gray-200 rounded-full overflow-hidden">
      <div class="h-full bg-blue-500" style="width:${category.counter()}%"></div>
    </div>
  </div>

</div>
