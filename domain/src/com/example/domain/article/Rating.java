package com.example.domain.article;

public record Rating(Double value) {

  public static Rating newRating() {
    return new Rating(0d);
  }

  public static Rating from(Double value) {
    //validation
    return new Rating(value);
  }

  public Double asDoubleValue(){
    return value;
  }
}
