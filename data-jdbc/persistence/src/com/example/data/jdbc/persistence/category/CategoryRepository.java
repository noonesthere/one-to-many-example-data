package com.example.data.jdbc.persistence.category;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CategoryRepository extends ListCrudRepository<CategoryEntity, Long> {
}
