package com.graphql.demo.controller;

import java.util.List;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;
import com.graphql.demo.model.Author;
import com.graphql.demo.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;

/**
 * The Class AuthorController.
 */

@RestController
@RequiredArgsConstructor
public class AuthorController {

  /** The author repository. */
  private final AuthorRepository authorRepository;

  /**
   * All authors.
   *
   * @return the list
   */
  @QueryMapping
  public List<Author> allAuthors() {
    return authorRepository.findAll();
  }

}
