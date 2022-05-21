package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddBooks {

    private static TextField callNumberField;
    private static TextField nameField;
    private static TextField authorField;
    private static TextField publisherField;
    private static TextField quantityField;

    public static void display(Stage window) {

        Label addBooksLabel = new Label("Add Books");

        Label callNumberLabel = new Label("Call NO.:");
        callNumberLabel.setMinSize(150, 10);

        Label nameLabel = new Label("Name:");
        nameLabel.setMinSize(150, 10);

        Label authorLabel = new Label("Author:");
        authorLabel.setMinSize(150, 10);

        Label publisherLabel = new Label("Publisher:");
        publisherLabel.setMinSize(150, 10);

        Label quantityLabel = new Label("Quantity:");
        quantityLabel.setMinSize(150, 10);

        VBox labelLayout = new VBox(18.7);
        labelLayout.getChildren().addAll(callNumberLabel, nameLabel, authorLabel, publisherLabel, quantityLabel);

        callNumberField = new TextField();
        callNumberField.setMinSize(200, 10);
        callNumberField.setPromptText("Call Number");

        nameField = new TextField();
        nameField.setMinSize(200, 10);
        nameField.setPromptText("Name");

        authorField = new TextField();
        authorField.setMinSize(200, 10);
        authorField.setPromptText("Author");

        publisherField = new TextField();
        publisherField.setMinSize(200, 10);
        publisherField.setPromptText("Publisher");

        quantityField = new TextField();
        quantityField.setMinSize(200, 10);
        quantityField.setPromptText("Quantity");

        VBox fieldLayout = new VBox(18);
        fieldLayout.getChildren().addAll(callNumberField, nameField, authorField, publisherField, quantityField);

        HBox informationLayout = new HBox(75);
        informationLayout.getChildren().addAll(labelLayout, fieldLayout);
        informationLayout.setAlignment(Pos.CENTER);

        Button addBooksButton = new Button("Add Books");
        addBooksButton.setMinSize(150, 50);
        addBooksButton.setOnAction(e -> {

            if (fieldsCheck()) {
                Book.readBooksFile();
                readNewBook();
                Book.writeBooksFile();
                AlertBox.display("Done", "Books added successfully!");
                LibrarianSection.display(window);
            }

        });


        VBox layout = new VBox(20);
        layout.getChildren().addAll(addBooksLabel, informationLayout, addBooksButton);
        layout.setAlignment(Pos.CENTER);

        Button backButton = new Button("Back");
        backButton.setMinSize(100, 50);
        backButton.setOnAction(e -> LibrarianSection.display(window));

        VBox finalLayout = new VBox(20);
        finalLayout.getChildren().addAll(layout, backButton);
        finalLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(finalLayout, 600, 600);
        scene.getStylesheets().add("library.css");
        window.setScene(scene);

    }

    public static void readNewBook() {

        Book.bookCallNumbers[Book.numberOfBooks] = callNumberField.getText();
        Book.names[Book.numberOfBooks] = nameField.getText();
        Book.authors[Book.numberOfBooks] = authorField.getText();
        Book.publishers[Book.numberOfBooks] = publisherField.getText();
        Book.quantities[Book.numberOfBooks] = quantityField.getText();

        Book.numberOfBooks++;

    }

    private static boolean fieldsCheck() {

        Book.readBooksFile();

        //call number check
        if (callNumberField.getText().equals("")) {
            AlertBox.display("Wrong Input", "enter book call number");
            return false;
        }
        if (callNumberField.getText().contains(" ")) {
            AlertBox.display("Wrong Input", "book call number can not have spaces");
            return false;
        }
        for (int i = 0; i < Book.numberOfBooks; i++) {
            if (callNumberField.getText().equals(Book.bookCallNumbers[i])){
                AlertBox.display("Wrong Input", "this book call number is already taken");
                return false;
            }
        }

        //book name check
        if (nameField.getText().equals("")) {
            AlertBox.display("Wrong Input", "enter book name");
            return false;
        }
        if (nameField.getText().contains(" ")) {
            AlertBox.display("Wrong Input", "book name can not have spaces");
            return false;
        }

        //author check
        if (authorField.getText().equals("")) {
            AlertBox.display("Wrong Input", "enter author");
            return false;
        }
        if (authorField.getText().contains(" ")) {
            AlertBox.display("Wrong Input", "author can not have spaces");
            return false;
        }

        //publisher check
        if (publisherField.getText().equals("")) {
            AlertBox.display("Wrong Input", "enter publisher");
            return false;
        }
        if (publisherField.getText().contains(" ")) {
            AlertBox.display("Wrong Input", "publisher can not have spaces");
            return false;
        }

        //contact number check
        if (quantityField.getText().equals("")) {
            AlertBox.display("Wrong Input", "enter quantity");
            return false;
        }
        if (!isNumeric(quantityField.getText())) {
            AlertBox.display("Wrong Input", "enter right quantity");
            return false;
        }
        if (quantityField.getText().contains(" ")) {
            AlertBox.display("Wrong Input", "quantity can not have spaces");
            return false;
        }

        return true;

    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        if (strNum.contains(".")) {
            return false;
        }
        try {
            Integer d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        if (Integer.parseInt(strNum) <= 0) {
            return false;
        }
        return true;
    }

}
