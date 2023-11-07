package org.example;

import org.example.database.JDBConnectionWrapper;
import org.example.model.Book;
import org.example.model.builder.BookBuilder;
import org.example.repository.BookRepository;
import org.example.repository.BookRepositoryMySQL;

import java.time.LocalDate;
import java.util.Date;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args){
        System.out.println("Hello world!");

        JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper("test_library");



        BookRepository bookRepository = new BookRepositoryMySQL(connectionWrapper.getConnection());

        Book book1 = new BookBuilder()
                .setAuthor("', '', null); SLEEP(20); --")
                .setTitle("Fram Ursul Polar")
                .setPublishedDate(LocalDate.of(2010, 6, 2))
                .build();
        Book book2 = new BookBuilder()
                .setAuthor("', '', null); SLEEP(20); --")
                .setTitle("Ion")
                .setPublishedDate(LocalDate.of(1986, 12, 15))
                .build();

        bookRepository.save(book1);
        bookRepository.save(book2);

        System.out.println(bookRepository.findAll() + "\n");
        System.out.println(bookRepository.findById(22L) + "\n"); //merge

        //bookRepository.removeAll(); //merge

        System.out.println(bookRepository.findAll());
    }
}