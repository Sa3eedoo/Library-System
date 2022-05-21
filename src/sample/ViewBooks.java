package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewBooks {

    public static void display(Stage window) {

        TableColumn<Book, String> bookCallNumberColumn  = new TableColumn<>("Book Call Number");
        bookCallNumberColumn.setMinWidth(300);
        bookCallNumberColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("bookCallNumber"));

        TableColumn<Book, String> nameColumn  = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));

        TableColumn<Book, String> authorColumn  = new TableColumn<>("Author");
        authorColumn.setMinWidth(200);
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));

        TableColumn<Book, String> publisherColumn  = new TableColumn<>("Publisher");
        publisherColumn.setMinWidth(200);
        publisherColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));

        TableColumn<Book, String> quantityColumn  = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(200);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("quantity"));

        TableView<Book> tableView = new TableView<>();
        tableView.setItems(getBooks());
        tableView.getColumns().addAll(bookCallNumberColumn, nameColumn, authorColumn, publisherColumn, quantityColumn);

        Button backButton = new Button("Back");
        backButton.setMinSize(150, 20);
        backButton.setOnAction(e -> LibrarianSection.display(window));

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(10,10,10,10));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(tableView, backButton);

        Scene scene = new Scene(layout, 1122,600);
        scene.getStylesheets().add("library.css");
        window.setScene(scene);

    }

    private static ObservableList<Book> getBooks() {

        ObservableList<Book> Books = FXCollections.observableArrayList();

        Book.readBooksFile();

        for (int i = 0;i < Book.numberOfBooks;i++) {

            Books.add(new Book(Book.bookCallNumbers[i], Book.names[i], Book.authors[i],
                    Book.publishers[i], Book.quantities[i]));

        }

        return Books;

    }

}
