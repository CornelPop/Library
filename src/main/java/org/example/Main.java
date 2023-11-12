package org.example;

import org.example.database.DatabaseConnectionFactory;
import org.example.database.JDBConnectionWrapper;
import org.example.model.AudioBook;
import org.example.model.Book;
import org.example.model.builder.AudioBookBuilder;
import org.example.model.builder.BookBuilder;
import org.example.repository.BookRepository;
import org.example.repository.BookRepositoryCacheDecorator;
import org.example.repository.BookRepositoryMySQL;
import org.example.repository.Cache;
import org.example.service.BookService;
import org.example.service.BookServiceImpl;

import java.time.LocalDate;
public class Main {

    public static void main(String[] args){
        System.out.println("Hello world!");

        BookRepository bookRepository = new BookRepositoryCacheDecorator(
                new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection()),
                new Cache<>()
        );

        BookService bookService = new BookServiceImpl(bookRepository);

        Book book = new BookBuilder()
                .setAuthor("Cezar Petrescu")
                .setTitle("Fram Ursul Polar")
                .setPublishedDate(LocalDate.of(2010, 6, 2))
                .build();
        Book book1 = new AudioBookBuilder()
                .setAuthor("Pop Cornel")
                .setTitle("Comoara din sertaru gol")
                .setRunTime(120)
                .setPublishedDate(LocalDate.of(2023, 12, 15))
                .build();

        //bookService.save(book);

        System.out.println(bookService.findAll());

        System.out.println(bookService.findAll());
        System.out.println(bookService.getAgeOfBook(22L));
    }
}
