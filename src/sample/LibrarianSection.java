package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LibrarianSection {

    public static void display(Stage window) {

        Label librarianSectionLabel = new Label("Librarian Section");

        Button addBooks = new Button("Add Books");
        addBooks.setMinSize(300 , 40);
        addBooks.setOnAction(e -> AddBooks.display(window));

        Button viewBooks = new Button("View Books");
        viewBooks.setMinSize(300 , 40);
        viewBooks.setOnAction(e -> ViewBooks.display(window));

        Button issueBook = new Button("Issue Book");
        issueBook.setMinSize(300 , 40);
        issueBook.setOnAction(e -> AddIssuedBooks.display(window));

        Button viewIssuedBooks = new Button("View Issued Books");
        viewIssuedBooks.setMinSize(300 , 40);
        viewIssuedBooks.setOnAction(e -> ViewIssuedBooks.display(window));

        Button returnBook = new Button("Return Book");
        returnBook.setMinSize(300 , 40);
        returnBook.setOnAction(e -> ReturnBook.display(window));

        Button logout = new Button("Logout");
        logout.setMinSize(300 , 40);
        logout.setOnAction(e -> LibraryManagement.display(window));

        VBox buttonsLayout = new VBox(20);
        buttonsLayout.setAlignment(Pos.CENTER);
        buttonsLayout.getChildren().addAll(addBooks, viewBooks, issueBook, viewIssuedBooks, returnBook, logout);

        VBox layout = new VBox(40);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(librarianSectionLabel, buttonsLayout);

        Scene scene = new Scene(layout, 600, 600);
        scene.getStylesheets().add("library.css");
        window.setScene(scene);

    }

}
