package com.graphql.demo.model;

/**
 * The Record Book.
 *
 * @param id the id
 * @param title the title
 * @param pages the pages
 * @param rating the rating
 * @param author the author
 */
public record Book(Integer id, String title, Integer pages, Rating rating, Author author) {

}
