package com.graphql.demo.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The Enum Rating.
 */
public enum Rating {

  /** The five stars. */
  FIVE_STARS("⭐️⭐️⭐️⭐️⭐️️️️"),

  /** The four stars. */
  FOUR_STARS("⭐️⭐️⭐️⭐️"),

  /** The three stars. */
  THREE_STARS("⭐️⭐️⭐️"),

  /** The two stars. */
  TWO_STARS("⭐️⭐️"),

  /** The one star. */
  ONE_STAR("⭐️");

  /** The star. */
  private String star;

  /**
   * Instantiates a new rating.
   *
   * @param star the star
   */
  Rating(String star) {
    this.star = star;
  }

  /**
   * Gets the star.
   *
   * @return the star
   */
  @JsonValue
  public String getStar() {
    return star;
  }

}
