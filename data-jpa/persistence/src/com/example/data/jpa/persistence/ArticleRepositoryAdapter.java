package com.example.data.jpa.persistence;

import jakarta.inject.Named;
import org.springframework.data.repository.CrudRepository;

@Named
public interface ArticleRepositoryAdapter extends CrudRepository<ArticleEntity, String> {

}
