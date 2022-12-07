package com.example.booklibrary.service;

import com.example.booklibrary.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.booklibrary.repo.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    public List<Book> getBookList() {
        return bookRepository.getBookList();
    }

    public Book getBookByISBN(long ISBN) {
        return this.bookRepository.getBookByISBN(ISBN);
    }

    public List<Book> putNewBook(long ISBN, Book newBook) {
        return this.bookRepository.putNewBook(ISBN, newBook);
    }

    public List<Book> deleteBook(long ISBN) {
        this.bookRepository.deleteBook(ISBN);
        return bookRepository.getBookList();

    }
}
