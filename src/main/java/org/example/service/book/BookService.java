package org.example.service.book;

import org.example.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book findById(Long id);

    boolean save(Book book);

    int getAgeOfBook(Long id);

    boolean updateStock(Book book, int newStock);

    boolean deleteById(Book book, int id);

}
