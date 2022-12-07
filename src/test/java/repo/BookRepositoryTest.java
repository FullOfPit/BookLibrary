package repo;

import com.example.booklibrary.model.Book;
import com.example.booklibrary.repo.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class BookRepositoryTest {

    @Test
    void getEmptyBookList() {
        //Given
        BookRepository bookRepository = new BookRepository();
        //When
        List<Book> actual = bookRepository.getBookList();
        //Then
        Assertions.assertEquals(new ArrayList<>(), actual);
    }

    @Test
    void getBookListWithABook() {
        //Given
        BookRepository bookRepository = new BookRepository();
        bookRepository.getBookList().add(new Book());
        //When
        List<Book> actual = bookRepository.getBookList();
        //Then
        Assertions.assertEquals(new ArrayList<>(List.of(new Book())), actual);
    }

    @Test
    void getBookByISBN() {
        //Given
        BookRepository bookRepository = new BookRepository();
        bookRepository.getBookList().add(new Book(9783110194470L, "Maßanalyse", "Gerhart Jander, Karl Friedrich Jahr"));
        //When
        Book actual = bookRepository.getBookByISBN(9783110194470L);
        //Then
        Assertions.assertEquals(new Book(9783110194470L, "Maßanalyse", "Gerhart Jander, Karl Friedrich Jahr"), actual);
    }

    @Test
    void getBookByISBN_ThrowException() {
        //Given
        BookRepository bookRepository = new BookRepository();
        bookRepository.getBookList().add(new Book(9783110194470L, "Maßanalyse", "Gerhart Jander, Karl Friedrich Jahr"));
        //When - Then

        Assertions.assertThrows(ArrayStoreException.class, () -> bookRepository.getBookByISBN(1));
        /*
        try {
            Book actual = bookRepository.getBookByISBN(1);
            Assertions.fail();
        } catch (ArrayStoreException e) {
            Assertions.assertTrue(true);
        }
         */
    }

    @Test
    void putNewBook_NotInRepo() {
        //Given
        BookRepository bookRepository = new BookRepository();
        //When
        List<Book> actual = bookRepository.putNewBook(9783110194470L,new Book(9783110194470L, "Maßanalyse", "Gerhart Jander, Karl Friedrich Jahr"));
        //Then
        Assertions.assertEquals(List.of(new Book(9783110194470L, "Maßanalyse", "Gerhart Jander, Karl Friedrich Jahr")), actual);
    }

    @Test
    void putNewBook_throwsExceptionWhenBookAlreadyIn() {
        //Given
        BookRepository bookRepository = new BookRepository();
        bookRepository.getBookList().add(new Book(9783110194470L, "Maßanalyse", "Gerhart Jander, Karl Friedrich Jahr"));
        //When - Then
        try {
            List<Book> actual = bookRepository.putNewBook( 9783110194470L,new Book(9783110194470L, "Maßanalyse", "Gerhart Jander, Karl Friedrich Jahr"));
            Assertions.fail();
        } catch (ArrayStoreException e) {
            Assertions.assertTrue(true);
        }
    }
    @Test
    void deleteNewBook_InRepo() {
        //Given
        BookRepository bookRepository = new BookRepository();
        bookRepository.putNewBook(9783110194470L,new Book(9783110194470L, "Maßanalyse", "Gerhart Jander, Karl Friedrich Jahr"));
        //When
        List<Book> actual = bookRepository.deleteBook(9783110194470L);
        //Then
        Assertions.assertEquals(new ArrayList<>(), actual);
    }

    @Test
    void deleteNewBook_NotInRepo() {
        //Given
        BookRepository bookRepository = new BookRepository();
        bookRepository.getBookList().add(new Book(9783110194470L, "Maßanalyse", "Gerhart Jander, Karl Friedrich Jahr"));
        //When - Then
        try {
            List<Book> actual = bookRepository.deleteBook( 2);
            Assertions.fail();
        } catch (ArrayStoreException e) {
            Assertions.assertTrue(true);
        }
    }

}