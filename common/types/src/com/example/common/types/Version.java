package com.example.common.types;

public record Version(long value) {

  public Version next() {
    return new Version(value + 1);
  }

  public Version previous() {
    return new Version(value - 1);
  }

  public static Version newVersion() {
    return new Version(1L);
  }

  public static Version from(Long value) {
    return new Version(value);
  }
}

