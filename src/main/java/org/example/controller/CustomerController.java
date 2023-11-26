package org.example.controller;

import javafx.collections.ObservableListBase;
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
        this.customerView.addRefreshButtonListener(new refreshButtonListener());
    }

    private class buyButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            if (customerView.bookSelected() == null)
            {
                customerView.showErrorBox("Select the book you want idiot!!");
            }
            else
            {
                if (isNumeric(customerView.getQuantityTextField().getText()))
                {
                    if (customerView.bookSelected().getStock() < Integer.parseInt(customerView.getQuantityTextField().getText()))
                    {
                        customerView.showErrorBox("You can not buy more books than there are in the store.");
                    }
                    else
                    {
                        bookService.updateStock(customerView.bookSelected(), customerView.bookSelected().getStock() - Integer.parseInt(customerView.getQuantityTextField().getText()));
                    }
                }
                else
                {
                    customerView.showErrorBox("Enter a valid number idiot!!!");
                }
            }
        }
    }

    private class refreshButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            customerView.emptyTableView();
            customerView.getTable().setItems(customerView.getBooks());
        }
    }

    public boolean isNumeric(String string) {
        String numericRegex = "\\d+";
        return string.matches(numericRegex);
    }
}
