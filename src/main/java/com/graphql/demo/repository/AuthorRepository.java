package com.graphql.demo.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.graphql.demo.model.Author;
import jakarta.annotation.PostConstruct;

/**
 * The Class AuthorRepository.
 */
@Repository
public class AuthorRepository {

  /** The authors. */
  private List<Author> authors = new ArrayList<>();

  /**
   * Find all.
   *
   * @return the list
   */
  public List<Author> findAll() {
    return authors;
  }

  /**
   * Find by id.
   *
   * @param id the id
   * @return the author
   */
  public Author findById(int id) {
    return authors.stream().filter(author -> author.id() == id).findFirst()
        .orElseThrow(() -> new RuntimeException("Author not found"));
  }

  /**
   * Find by name.
   *
   * @param name the name
   * @return the author
   */
  public Author findByName(String name) {
    return authors.stream().filter(author -> author.fullName().equals(name)).findFirst()
        .orElseThrow(() -> new RuntimeException("Author not found"));
  }

  /**
   * Initialises the data after the application is started.
   */
  @PostConstruct
  private void init() {
    authors.add(new Author(1, "Josh", "Long"));
    authors.add(new Author(2, "Mark", "Heckler"));
    authors.add(new Author(3, "Greg", "Turnquist"));
  }

}
