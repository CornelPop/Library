package org.example.repository.book;

import org.example.model.Bill;
import org.example.model.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    boolean save(Book book);

    boolean saveBill(Bill bill);

    void removeAll();

    boolean updateBillBookId(Long billId, Long newBookId);

    boolean updateStock(Book book, int newStock);

    boolean updateBook(Book book, String newAuthor, String newTitle, LocalDate newPublishedDate, int newPrice, int newStock);

    boolean deleteById(Book book, int id);

    List<Bill> findAllBills();
}
