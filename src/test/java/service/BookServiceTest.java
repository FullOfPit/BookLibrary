package service;

import com.example.booklibrary.model.Book;
import com.example.booklibrary.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.example.booklibrary.repo.BookRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class BookServiceTest {

    BookRepository bookRepo = mock(BookRepository.class);

    BookService bookService = new BookService(bookRepo);

    @Test
    void getEmptyBookList() {
        //Given
        BookService newBookService = new BookService(new BookRepository());
        //When
        List<Book> actual = newBookService.getBookList();
        //Then
        Assertions.assertEquals(new ArrayList<>(), actual);

    }


    @Test
    void getBookList_WithBook() {
        //Given
        Book book = new Book(9783110194470L, "Maßanalyse", "Gerhart Jander, Karl Friedrich Jahr");
        List<Book> bookList = new ArrayList<>(List.of(book));
        when(bookRepo.getBookList()).thenReturn(bookList);
        //When
        List<Book> actual = bookService.getBookList();
        //Then
        Assertions.assertEquals(List.of(book), actual);
        verify(bookRepo).getBookList();
    }

    @Test
    void getBookISBN() {
        //Given
        Book book = new Book(9783110194470L, "Maßanalyse", "Gerhart Jander, Karl Friedrich Jahr");
        when(bookRepo.getBookByISBN(9783110194470L)).thenReturn(book);
        //When
        Book actual = bookService.getBookByISBN(9783110194470L);
        //Then
        Assertions.assertEquals(new Book(9783110194470L, "Maßanalyse", "Gerhart Jander, Karl Friedrich Jahr"), actual);
        verify(bookRepo).getBookByISBN(9783110194470L);
    }

    @Test
    void putNewBook() {
        //Given
        Book book = new Book(9783110194470L, "Maßanalyse", "Gerhart Jander, Karl Friedrich Jahr");
        when(bookRepo.putNewBook(9783110194470L, book)).thenReturn(List.of(new Book(9783110194470L, "Maßanalyse", "Gerhart Jander, Karl Friedrich Jahr")));
        //When
        List<Book> actual = bookService.putNewBook(9783110194470L, book);
        //Then
        Assertions.assertEquals(List.of(new Book(9783110194470L, "Maßanalyse", "Gerhart Jander, Karl Friedrich Jahr")), actual);
        verify(bookRepo).putNewBook(9783110194470L, book);
    }

    @Test
    void deleteBook() {
        //Given
        Book book = new Book(9783110194470L, "Maßanalyse", "Gerhart Jander, Karl Friedrich Jahr");
        when(bookRepo.getBookByISBN(9783110194470L)).thenReturn(book);
        //When
        List<Book> actual = bookService.deleteBook(9783110194470L);
        //Then
        Assertions.assertEquals(List.of(), actual);
        verify(bookRepo).deleteBook(9783110194470L);

    }

}