package org.example.repository.book;

import org.example.model.Book;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepositoryMock implements BookRepository{
    private List<Book> books;
    private final Connection connection;

    public BookRepositoryMock(Connection connection){
        this.connection = connection;
        books = new ArrayList<>();
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return null;
    }

    /*@Override
    public Optional<Book> findById(Long id) {
        return books.parallelStream()
                .filter(it -> it.getId().equals(id))
                .findFirst();
    }*/

    @Override
    public boolean save(Book book) {
        return books.add(book);
    }

    @Override
    public void removeAll() {
        books.clear();
    }

    @Override
    public boolean updateStock(Book book, int newStock) {
        return false;
    }

    @Override
    public boolean deleteById(Book book, int id) {
        return false;
    }
}
