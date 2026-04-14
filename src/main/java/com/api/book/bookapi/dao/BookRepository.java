package com.api.book.bookapi.dao;

import org.springframework.data.repository.CrudRepository;

import com.api.book.bookapi.entities.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {

    public Book findById(int id);
    
}
