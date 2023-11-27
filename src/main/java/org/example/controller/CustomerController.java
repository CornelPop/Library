package org.example.controller;

import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import org.example.model.Book;
import org.example.model.CustomerModel;
import org.example.service.book.BookServiceImpl;
import org.example.service.user.AuthenticationService;
import org.example.view.CustomerView;

import java.util.List;

public class CustomerController {

    private AuthenticationService authenticationService;
    private CustomerModel customerModel;
    private CustomerView customerView;
    private BookServiceImpl bookService;

    private Stage customerStage;

    public CustomerController(CustomerView customerView, CustomerModel customerModel, BookServiceImpl bookService, AuthenticationService authenticationService, Stage customerStage) {
        this.customerView = customerView;
        this.customerModel = customerModel;
        this.bookService = bookService;
        this.authenticationService = authenticationService;
        this.customerStage = customerStage;

        this.customerView.addBuyButtonListener(new buyButtonListener());
        this.customerView.addRefreshButtonListener(new refreshButtonListener());
        this.customerView.addLogoutButtonListener(new logoutButtonListener());
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
                        if (customerView.bookSelected().getStock() - Integer.parseInt(customerView.getQuantityTextField().getText()) == 0)
                        {
                            bookService.deleteById(customerView.bookSelected(), customerView.bookSelected().getId().intValue());
                        }
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

    private class logoutButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(javafx.event.ActionEvent event) {
            //authenticationService.logout();
            customerStage.close();
        }
    }

    public boolean isNumeric(String string) {
        String numericRegex = "\\d+";
        return string.matches(numericRegex);
    }
}
