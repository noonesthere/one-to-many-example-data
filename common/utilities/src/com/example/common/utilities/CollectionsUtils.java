package com.example.common.utilities;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

public final class CollectionsUtils {

  @Nonnull
  public static <T> Stream<T> streamOf(@Nullable final Collection<T> coll) {
    if (Objects.isNull(coll)) {
      return Stream.empty();
    }

    return coll.stream();
  }

  private CollectionsUtils() {
  }
}
