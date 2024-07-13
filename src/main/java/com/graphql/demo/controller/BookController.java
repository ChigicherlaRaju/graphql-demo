package com.graphql.demo.controller;

import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.web.bind.annotation.RestController;
import com.graphql.demo.model.Book;
import com.graphql.demo.repository.BookRepository;

/**
 * The Class BookController.
 */
@RestController
public class BookController {

  /** The book repository. */
  private final BookRepository bookRepository;

  /**
   * Instantiates a new book controller.
   *
   * @param bookRepository the book repository
   */
  public BookController(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  /**
   * Find all.
   *
   * @return the list
   */
  @SchemaMapping(typeName = "Query", value = "allBooks")
  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  /**
   * Find one.
   *
   * @param id the id
   * @return the book
   */
  @QueryMapping
  public Book findOne(@Argument Integer id) {
    return bookRepository.findOne(id);
  }

}
