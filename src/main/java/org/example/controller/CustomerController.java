package org.example.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.example.model.Book;
import org.example.model.CustomerModel;
import org.example.service.book.BookServiceImpl;
import org.example.view.CustomerView;

import java.util.List;

public class CustomerController {

    private CustomerModel customerModel;
    private CustomerView customerView;
    private BookServiceImpl bookService;

    public CustomerController(CustomerView customerView, CustomerModel customerModel, BookServiceImpl bookService) {
        this.customerView = customerView;
        this.customerModel = customerModel;
        this.bookService = bookService;

        this.customerView.addBuyButtonListener(new buyButtonListener());
    }

    private class buyButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
                bookService.updateStock(customerView.bookSelected(), customerView.bookSelected().getStock() - Integer.parseInt(customerView.getQuantityTextField().getText()));
        }

    }
}
