package com.example.domain.article;

import java.util.List;
import java.util.function.Function;

@FunctionalInterface
public interface ParagraphsSupplier extends Function<Article, List<Paragraph>> {
}
