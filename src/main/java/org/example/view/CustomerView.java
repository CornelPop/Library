package org.example.view;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.controller.CustomerController;
import org.example.model.Book;
import org.example.model.CustomerModel;
import org.example.model.builder.BookBuilder;
import org.example.service.book.BookServiceImpl;

import java.time.LocalDate;
import java.util.List;

public class CustomerView {

    private CustomerModel customerModel;
    private Button buyButton;
    private TableView<Book> table;

    private TextField quantityTextField;

    public CustomerView(Stage customerStage, CustomerModel customerModel) {

        this.customerModel = customerModel;

        customerStage.setTitle("Book Store");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 720, 480);
        customerStage.setScene(scene);

        initializeSceneTitle(gridPane);

        initializeFields(gridPane);

        customerStage.show();
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane) {
        Text sceneTitle = new Text("Welcome to CUSTOMER page");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 1, 1);
    }

    private void initializeFields(GridPane gridPane) {
        buyButton = new Button("Buy");
        HBox buyButtonHBox = new HBox(10);
        buyButtonHBox.getChildren().add(buyButton);
        gridPane.add(buyButtonHBox, 0, 1);

        TableColumn<Book, String> idColumn = new TableColumn<>("Id");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));

        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setMinWidth(100);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setMinWidth(100);
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, LocalDate> dateColumn = new TableColumn<>("Published Date");
        dateColumn.setMinWidth(100);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));

        TableColumn<Book, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Book, String> stockColumn = new TableColumn<>("Stock");
        stockColumn.setMinWidth(100);
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        table = new TableView<>();
        table.setItems(getBooks());
        table.getColumns().addAll(idColumn, titleColumn, authorColumn, dateColumn, priceColumn, stockColumn);

        quantityTextField = new TextField();
        quantityTextField.setPromptText("Insert quantity (e.g. 7)");
        gridPane.add(quantityTextField, 0, 2);

        gridPane.add(table, 0, 3);
    }

    public Book bookSelected() {
        return table.getSelectionModel().getSelectedItem();
    }

    public void addBuyButtonListener(EventHandler<ActionEvent> buyButtonListener) {
        buyButton.setOnAction(buyButtonListener);
    }

    public ObservableList<Book> getBooks() {
        ObservableList<Book> books = FXCollections.observableArrayList();

        List<Book> allBooks = customerModel.getBooks();

        books.addAll(allBooks);

        return books;
    }

    public TextField getQuantityTextField() {
        return quantityTextField;
    }
}
