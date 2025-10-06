package com.example.data.jdbc.persistence.article;

import org.springframework.data.repository.ListCrudRepository;

public interface ArticleRepository extends ListCrudRepository<ArticleEntity, Long> {
}
