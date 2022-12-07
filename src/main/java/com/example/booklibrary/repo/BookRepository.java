package com.example.booklibrary.repo;

import com.example.booklibrary.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    List<Book> bookList = new ArrayList<>(
            List.of(
                    new Book(9783110194470L, "Maßanalyse", "Gerhart Jander, Karl Friedrich Jahr"),
                    new Book(9783110189032L, "Anorganische Chemie", "Erwin Riedel, Christoph Janiak"),
                    new Book(9783827416148L, "Naturstoffe der chemischen Industrie", "Bernd Schäfer")
            )



    );

    public List<Book> getBookList() {
        return this.bookList;
    }

    public Book getBookByISBN(long ISBN) {
        for (Book book : this.bookList) {
            if (book.getISBN() == ISBN) {
                return book;
            }
        }
        throw new ArrayStoreException("Book not registered");
    }

    public List<Book> putNewBook(long ISBN, Book newBook) {
        for (Book book : this.bookList) {
            if (book.getISBN() == ISBN) {
                throw new ArrayStoreException("Book already in library");
            }
        }
        this.bookList.add(newBook);
        return this.bookList;

        //bookList.contains(newBook);
    }

    public List<Book> deleteBook(long ISBN) {
        for(Book book : this.bookList) {
            if (book.getISBN() == ISBN) {
                this.bookList.remove(book);
                return this.bookList;
            }
        }
       throw new ArrayStoreException("ISBN not in library");
    }
}
