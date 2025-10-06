package com.example.plain.jdbc.persistence;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CategoryRepository extends ListCrudRepository<CategoryEntity, Long> {
}
