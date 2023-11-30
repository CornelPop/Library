package org.example.service.book;

import org.example.model.Bill;
import org.example.model.Book;
import org.example.repository.book.BookRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book with id: %d not found".formatted(id)));
    }

    @Override
    public boolean save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public boolean saveBill(Bill bill){
        return bookRepository.saveBill(bill);
    }

    @Override
    public int getAgeOfBook(Long id) {
        Book book = this.findById(id);

        LocalDate now = LocalDate.now();

        return (int) ChronoUnit.YEARS.between(book.getPublishedDate(), now);
    }

    @Override
    public boolean updateStock(Book book, int newStock) {
        return bookRepository.updateStock(book, newStock);
    }

    @Override
    public boolean updateBook(Book book, String newAuthor, String newTitle, LocalDate newPublishedDate, int newPrice, int newStock) {
        return bookRepository.updateBook(book, newAuthor, newTitle, newPublishedDate, newPrice, newStock);
    }

    @Override
    public boolean updateBillBookId(Long billId, Long newBookId) {
        return bookRepository.updateBillBookId(billId, newBookId);
    }

    @Override
    public boolean deleteById(Book book, int id)
    {
        return bookRepository.deleteById(book, id);
    }

    public List<Bill> findAllBills() {
        return bookRepository.findAllBills();
    }
}
