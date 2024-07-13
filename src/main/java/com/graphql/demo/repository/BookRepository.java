package com.graphql.demo.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.graphql.demo.model.Book;
import com.graphql.demo.model.Rating;
import jakarta.annotation.PostConstruct;

/**
 * The Class BookRepository.
 */
@Repository
public class BookRepository {

  /** The author repository. */
  private final AuthorRepository authorRepository;

  /** The books. */
  private List<Book> books = new ArrayList<>();

  /**
   * Instantiates a new book repository.
   *
   * @param authorRepository the author repository
   */
  public BookRepository(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  /**
   * Find all.
   *
   * @return the list
   */
  public List<Book> findAll() {
    return books;
  }

  /**
   * Find one.
   *
   * @param id the id
   * @return the book
   */
  public Book findOne(Integer id) {
    return books.stream().filter(book -> book.id() == id).findFirst()
        .orElseThrow(() -> new RuntimeException("Book not found"));
  }

  /**
   * Initialises the data after the application is started.
   */
  @PostConstruct
  private void init() {
    books.add(new Book(1, "Reactive Spring", 484, Rating.FIVE_STARS,
        authorRepository.findByName("Josh Long")));
    books.add(new Book(2, "Spring Boot Up & Running", 328, Rating.FIVE_STARS,
        authorRepository.findByName("Mark Heckler")));
    books.add(new Book(3, "Hacking with Spring Boot 2.3", 392, Rating.FIVE_STARS,
        authorRepository.findByName("Greg Turnquist")));
  }

}
