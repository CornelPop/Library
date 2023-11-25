package org.example.repository.book;

import org.example.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    boolean save(Book book);

    void removeAll();

    boolean updateStock(Book book, int newStock);
}
