package com.api.book.bookapi.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.api.book.bookapi.dao.BookRepository;
import com.api.book.bookapi.entities.Book;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // static List<Book> list = new ArrayList<>();

    // static {
    //     list.add(new Book(1, "Java Complete Reference", "ABC"));
    //     list.add(new Book(2, "Python Complete Reference", "XYZ"));
    //     list.add(new Book(3, "JavaScript Complete Reference", "MNQ"));
    // }

    public List<Book> getAllBooks() {
        List<Book> list = (ArrayList<Book>) bookRepository.findAll();
        return list;
    }

    // Book result;
    // public Book getBookById(int id) {
    // list.forEach(e -> {
    // if(e.getId() == id){
    // result = e;
    // }
    // });
    // return result;
    // }

    public Book getBookById(int id) {
        Book book = null;
        try {
            // book = list.stream().filter(e -> e.getId() == id).findFirst().get();
            book = bookRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;

    }

    public Book addBook(Book book) {
        // list.add(b);
        Book result = bookRepository.save(book);
        return result;
    }

    public void deleteBook(int id) {
        // list = list.stream().filter(e -> e.getId() != id).collect(Collectors.toList());
        bookRepository.deleteById(id);
    }

    // public void deleteBook(int id) {
    // list.forEach(e -> {
    // if(e.getId() == id) {
    // list.remove(e);
    // }
    // });
    // }

    // public Book updateBook(Book book, int id) {
    // list = list.stream().map(e -> {
    // if(e.getId() == id) {
    // e.setAuthor(book.getAuthor());
    // e.setName(book.getName());
    // }
    // }).collect(Collectors.toList());
    // }

    public Book updateBook(Book book, int id) {
        // for (Book b : list) {
        //     if (b.getId() == id) {
        //         b.setAuthor(book.getAuthor());
        //         b.setName(book.getName());
        //     }
        // }
        // return book;

        book.setId(id);
        Book result = bookRepository.save(book);
        return result;

    }

}
