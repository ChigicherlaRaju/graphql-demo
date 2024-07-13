package com.graphql.demo.model;

/**
 * The Record Author.
 *
 * @param id the id
 * @param firstName the first name
 * @param lastName the last name
 */
public record Author(Integer id, String firstName, String lastName) {

  /**
   * Full name.
   *
   * @return the string
   */
  public String fullName() {
    return firstName + " " + lastName;
  }

}
