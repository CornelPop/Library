package org.example.service.book;

import org.example.model.Bill;
import org.example.model.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book findById(Long id);

    boolean save(Book book);

    boolean saveBill(Bill bill);

    int getAgeOfBook(Long id);

    boolean updateStock(Book book, int newStock);

    boolean updateBook(Book book, String newAuthor, String newTitle, LocalDate newPublishedDate, int newPrice, int newStock);

    boolean updateBillBookId(Long billId, Long newBookId);
    boolean deleteById(Book book, int id);

}
