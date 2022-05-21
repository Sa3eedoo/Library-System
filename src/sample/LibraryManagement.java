package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LibraryManagement {

    public LibraryManagement(Stage window) {

        LibraryManagement.display(window);
        window.setTitle("Library System");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram(window);
        });

    }

    public static void display(Stage window) {

        Label libraryManagementLabel = new Label("Library Management");

        Button adminButton = new Button("Admin Login");
        adminButton.setMinSize(250, 50);
        adminButton.setOnAction(e -> AdminLoginForm.display(window));

        Button librarianButton = new Button("Librarian Login");
        librarianButton.setMinSize(250, 50);
        librarianButton.setOnAction(e-> LibrarianLoginForm.display(window));

        Button studentButton = new Button("Student Login");
        studentButton.setMinSize(250, 50);
        studentButton.setOnAction(e-> StudentLoginForm.display(window));


        VBox buttonLayout = new VBox(20);
        buttonLayout.getChildren().addAll(adminButton, librarianButton, studentButton);
        buttonLayout.setAlignment(Pos.CENTER);

        VBox layout = new VBox(50);
        layout.getChildren().addAll(libraryManagementLabel, buttonLayout);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 600, 600);
        scene.getStylesheets().add("library.css");

        window.setScene(scene);

    }

    private void closeProgram(Stage window) {

        boolean answer = ConfirmBox.display("Exit", "are you sure?");

        if (answer)
            window.close();

    }

}
