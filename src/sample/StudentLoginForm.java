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

public class StudentLoginForm {

    private static TextField nameField;
    private static PasswordField passwordField;

    public static void display(Stage window) {

        Label studentLoginFormLabel = new Label("Student Login Form");

        Label enterNameLabel = new Label("Enter Name:");
        enterNameLabel.setMinSize(200, 10);

        Label enterPasswordLabel = new Label("Enter Password:");
        enterPasswordLabel.setMinSize(200, 10);

        nameField = new TextField();
        nameField.setPromptText("username");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");
        loginButton.setMinSize(200, 50);
        loginButton.setOnAction(e -> {

            if (isStudent()) {
                IssuedBook.readIssuedBooksFile();
                StudentSection.findStudentIndex(nameField.getText());
                StudentSection.display(window);
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
        backButton.setMinSize(150, 20);
        backButton.setOnAction(e -> LibraryManagement.display(window));

        VBox layout = new VBox(20);
        layout.getChildren().addAll(studentLoginFormLabel, informationLayout, loginButton, backButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 600, 600);
        scene.getStylesheets().add("library.css");
        window.setScene(scene);

    }

    private static boolean isStudent() {

        Student.readStudentsFile();

        for (int i = 0; i < Student.numberOfStudents; i++) {
            if (Student.names[i].equals(nameField.getText()) && Student.passwords[i].equals(passwordField.getText())) {
                return true;
            }
        }

        AlertBox.display("wrong input", "wrong username or password");
        return false;

    }
    
}
