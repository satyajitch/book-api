package com.api.book.bookapi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bookapi.Services.BookService;
import com.api.book.bookapi.entities.Book;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController 
public class BookController {

    @Autowired
    private BookService bookService;

    // Getting all books handler
    @GetMapping("/books") 
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> list = bookService.getAllBooks();
        if (list.size() <= 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            // return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    // Getting single book handler
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book result = bookService.getBookById(id);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    // Adding new book handler
    @PostMapping("/books")
    public Object addBook(@RequestBody Book book) {
        try {
            bookService.addBook(book);
            // return ResponseEntity.status(200);
            return ResponseEntity.status(HttpStatus.CREATED).body(book);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some exception occurs");
        }
    }

    // deleting a single book handler
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // updating book handler
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable int id) {
        try {
            book = bookService.updateBook(book, id);
            return ResponseEntity.ok(book);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        
    }

}
