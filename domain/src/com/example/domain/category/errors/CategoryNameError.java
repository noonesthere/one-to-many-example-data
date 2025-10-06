package com.example.domain.category.errors;

import com.example.common.types.BusinessError;

public sealed class CategoryNameError
  implements BusinessError
  permits EmptyCategoryNameError, CategoryNameLengthOverflowError {
}
