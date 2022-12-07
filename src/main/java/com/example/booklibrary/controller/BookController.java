package com.example.booklibrary.controller;

import com.example.booklibrary.model.Book;
import com.example.booklibrary.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService =  bookService;
    }

    @GetMapping
    public List<Book> get() {
        return bookService.getBookList();
    }

    @GetMapping("/{isbn}")
    public Book get(@PathVariable long isbn) {
        return this.bookService.getBookByISBN(isbn);
    }

    @PutMapping("/{isbn}")
    public List<Book> put(@PathVariable long isbn, @RequestBody Book newBook) {
        this.bookService.putNewBook(isbn, newBook);
        return this.bookService.getBookList();
    }

    @DeleteMapping("/{isbn}")
    public List<Book> delete(@PathVariable long isbn) {
        this.bookService.deleteBook(isbn);
        return this.bookService.getBookList();
    }

}
