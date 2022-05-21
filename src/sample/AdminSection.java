package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminSection {

    public static void display(Stage window) {

        Label adminSectionLabel = new Label("Admin Section");

        Button addLibrarian = new Button("Add Librarian");
        addLibrarian.setMinSize(300 , 50);
        addLibrarian.setOnAction(e -> AddLibrarian.display(window));

        Button viewLibrarian = new Button("View Librarian");
        viewLibrarian.setMinSize(300 , 50);
        viewLibrarian.setOnAction(e -> ViewLibrarian.display(window));

        Button deleteLibrarian = new Button("Delete Librarian");
        deleteLibrarian.setMinSize(300 , 50);
        deleteLibrarian.setOnAction(e -> DeleteLibrarian.display(window));

        Button addStudent = new Button("Add Student");
        addStudent.setMinSize(300 , 50);
        addStudent.setOnAction(e -> AddStudent.display(window));

        Button logout = new Button("Logout");
        logout.setMinSize(300 , 50);
        logout.setOnAction(e -> LibraryManagement.display(window));

        VBox buttonsLayout = new VBox(20);
        buttonsLayout.setAlignment(Pos.CENTER);
        buttonsLayout.getChildren().addAll(addLibrarian, viewLibrarian, deleteLibrarian, addStudent, logout);

        VBox layout = new VBox(40);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(adminSectionLabel, buttonsLayout);

        Scene scene = new Scene(layout, 600, 600);
        scene.getStylesheets().add("library.css");
        window.setScene(scene);

    }

}
