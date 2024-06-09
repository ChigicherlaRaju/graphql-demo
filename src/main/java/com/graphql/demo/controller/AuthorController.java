package com.graphql.demo.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.graphql.demo.model.Author;
import com.graphql.demo.repository.AuthorRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthorController {

   private final AuthorRepository authorRepository;

   @QueryMapping
   public List<Author> allAuthors() {
      return authorRepository.findAll();
   }

}