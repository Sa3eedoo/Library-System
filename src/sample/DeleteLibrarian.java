package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeleteLibrarian {

    private static TextField nameField;
    private static PasswordField passwordField;

    public static void display(Stage window) {

        Label deleteLibrarianLabel = new Label("Delete Librarian");

        Label enterNameLabel = new Label("Enter Name:");
        enterNameLabel.setMinSize(200, 10);

        Label enterPasswordLabel = new Label("Enter Password:");
        enterPasswordLabel.setMinSize(200, 10);

        nameField = new TextField();
        nameField.setPromptText("username");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button deleteButton = new Button("Delete");
        deleteButton.setMinSize(200, 50);
        deleteButton.setOnAction(e -> {

            if (fieldsCheck()) {
                Librarian.readLibrariansFile();
                deleteLibrarian();
                Librarian.writeLibrariansFile();
                Librarian.readLibrariansFile();
                Librarian.writeLibrariansFile();
                AdminSection.display(window);
            }

        });

        VBox labelLayout = new VBox(18);
        labelLayout.getChildren().addAll(enterNameLabel,enterPasswordLabel);

        VBox fieldLayout = new VBox(10);
        fieldLayout.getChildren().addAll(nameField, passwordField);

        HBox informationLayout = new HBox(50);
        informationLayout.getChildren().addAll(labelLayout, fieldLayout);
        informationLayout.setAlignment(Pos.CENTER);

        Button backButton = new Button("Back");
        backButton.setMinSize(100, 20);
        backButton.setOnAction(e -> AdminSection.display(window));

        VBox layout = new VBox(20);
        layout.getChildren().addAll(deleteLibrarianLabel, informationLayout, deleteButton, backButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 600, 600);
        scene.getStylesheets().add("library.css");
        window.setScene(scene);

    }

    private static void deleteLibrarian() {

        boolean found = false;

        Librarian.readLibrariansFile();

        for (int i = 0; i < Librarian.numberOfLibrarians; i++) {
            if (nameField.getText().equals(Librarian.names[i])) {
                if (passwordField.getText().equals(Librarian.passwords[i])) {
                    Librarian.ids[i] = "";
                    Librarian.names[i] = "";
                    Librarian.passwords[i] = "";
                    Librarian.emails[i] = "";
                    Librarian.addresses[i] = "";
                    Librarian.cities[i] = "";
                    Librarian.contacts[i] = "";

                    AlertBox.display("Done", "Librarian deleted successfully!");
                    found = true;
                }
            }
        }

        if (!found)
            AlertBox.display("error", "Librarian not found");

    }

    private static boolean fieldsCheck() {

        Librarian.readLibrariansFile();

        //name check
        if (nameField.getText().equals("")) {
            AlertBox.display("Wrong Input", "enter username");
            return false;
        }
        if (nameField.getText().contains(" ")) {
            AlertBox.display("Wrong Input", "username can not have spaces");
            return false;
        }

        //password check
        if (passwordField.getText().equals("")) {
            AlertBox.display("Wrong Input", "enter password");
            return false;
        }
        if (passwordField.getText().contains(" ")) {
            AlertBox.display("Wrong Input", "password can not have spaces");
            return false;

        }

        return true;

    }
    
}
