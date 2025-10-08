package com.example.domain.article;

public record Rating(
  Double value,
  Integer count
) {
  public static Rating newRating() {
    return new Rating(0d, 0);
  }

  public static Rating from(Double value, Integer count) {
    // validation count should not be less then 0 rating cant be negative
    return new Rating(value, count);
  }

  public Rating addVote(Integer grade) {
    if (grade < 0 || grade > 10) {
      throw new IllegalArgumentException("Vote value must be between 0 and 10");
    }

    final double newValue = ((value * count) + grade) / (count + 1);
    final int newCount = count + 1;

    return new Rating(newValue, newCount);
  }
}
