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

public class ViewIssuedBooks {

    public static void display(Stage window) {

        TableColumn<IssuedBook, String> idColumn  = new TableColumn<>("id");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<IssuedBook, String>("id"));

        TableColumn<IssuedBook, String> bookCallNumberColumn  = new TableColumn<>("Book Call Number");
        bookCallNumberColumn.setMinWidth(300);
        bookCallNumberColumn.setCellValueFactory(new PropertyValueFactory<IssuedBook, String>("bookCallNumber"));

        TableColumn<IssuedBook, String> studentIdColumn  = new TableColumn<>("Student id");
        studentIdColumn.setMinWidth(200);
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<IssuedBook, String>("studentId"));

        TableColumn<IssuedBook, String> studentNameColumn  = new TableColumn<>("Student Name");
        studentNameColumn.setMinWidth(250);
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<IssuedBook, String>("studentName"));

        TableColumn<IssuedBook, String> studentContactColumn  = new TableColumn<>("Student Contact");
        studentContactColumn.setMinWidth(250);
        studentContactColumn.setCellValueFactory(new PropertyValueFactory<IssuedBook, String>("studentContact"));

        TableColumn<IssuedBook, String> issuedDateColumn  = new TableColumn<>("Issued Date");
        issuedDateColumn.setMinWidth(200);
        issuedDateColumn.setCellValueFactory(new PropertyValueFactory<IssuedBook, String>("issuedDate"));


        TableView<IssuedBook> tableView = new TableView<>();
        tableView.setItems(getIssuedBooks());
        tableView.getColumns().addAll(idColumn, bookCallNumberColumn, studentIdColumn, studentNameColumn, studentContactColumn,
                issuedDateColumn);

        Button backButton = new Button("Back");
        backButton.setMinSize(150, 20);
        backButton.setOnAction(e -> LibrarianSection.display(window));

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(10,10,10,10));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(tableView, backButton);

        Scene scene = new Scene(layout, 1322,600);
        scene.getStylesheets().add("library.css");
        window.setScene(scene);

    }

    private static ObservableList<IssuedBook> getIssuedBooks() {

        ObservableList<IssuedBook> IssuedBooks = FXCollections.observableArrayList();

        IssuedBook.readIssuedBooksFile();
        
        for (int i = 0; i < IssuedBook.numberOfIssuedBooks; i++) {

            IssuedBooks.add(new IssuedBook(IssuedBook.ids[i], IssuedBook.bookCallNumbers[i],
                    IssuedBook.studentIds[i], IssuedBook.studentNames[i],
                    IssuedBook.studentContacts[i], IssuedBook.issuedDates[i]));

        }

        return IssuedBooks;
        
    }    
    
}
