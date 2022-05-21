package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentSection {

    public static int[] studentsIndex = new int[3];
    public static int numberOfIssuedBooks;

    private static Label bookOneCallNumberLabel;
    private static Label bookTwoCallNumberLabel;
    private static Label bookThreeCallNumberLabel;
    private static Label bookOneDateLabel;
    private static Label bookTwoDateLabel;
    private static Label bookThreeDateLabel;

    public static void display(Stage window) {

        Label studentIssuedBooksLabel = new Label("Student Issued Books");

        Label bookOneLabel = new Label("Book1 Call Number Name / Issue Date:");

        Label bookTwoLabel = new Label("Book2 Call Number Name / Issue Date:");

        Label bookThreeLabel = new Label("Book3 Call Number Name / Issue Date:");

        VBox labelsLayout = new VBox(10);
        labelsLayout.getChildren().addAll(bookOneLabel, bookTwoLabel, bookThreeLabel);

        bookOneCallNumberLabel = new Label("---");

        bookTwoCallNumberLabel = new Label("---");

        bookThreeCallNumberLabel = new Label("---");

        VBox callNumbersLayout = new VBox(10);
        callNumbersLayout.getChildren().addAll(bookOneCallNumberLabel, bookTwoCallNumberLabel, bookThreeCallNumberLabel);

        bookOneDateLabel = new Label("---");

        bookTwoDateLabel = new Label("---");

        bookThreeDateLabel = new Label("---");

        VBox datesLayout = new VBox(10);
        datesLayout.getChildren().addAll(bookOneDateLabel, bookTwoDateLabel, bookThreeDateLabel);

        HBox informationLayout = new HBox(20);
        informationLayout.setAlignment(Pos.CENTER);
        setLabels();
        informationLayout.getChildren().addAll(labelsLayout, callNumbersLayout, datesLayout);

        Label numberOfIssuedBooksLabel = new Label("number of Issued Books: " + numberOfIssuedBooks);

        Button logoutButton = new Button("Logout");
        logoutButton.setMinSize(100, 20);
        logoutButton.setOnAction(e -> LibraryManagement.display(window));

        VBox layout = new VBox(40);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(studentIssuedBooksLabel, informationLayout, numberOfIssuedBooksLabel, logoutButton);

        Scene scene = new Scene(layout, 1200, 600);
        scene.getStylesheets().add("library.css");
        window.setScene(scene);

    }

    public static void findStudentIndex(String studentName) {

        numberOfIssuedBooks = 0;
        for (int i =0; i < IssuedBook.numberOfIssuedBooks; i++) {
            if (IssuedBook.studentNames[i].equals(studentName)) {
                studentsIndex[numberOfIssuedBooks] = i;
                numberOfIssuedBooks++;
            }
        }

    }

    private static void setLabels() {

        if (numberOfIssuedBooks == 1){
            bookOneCallNumberLabel.setText(IssuedBook.bookCallNumbers[studentsIndex[0]]);

            bookOneDateLabel.setText(IssuedBook.issuedDates[studentsIndex[0]]);
        }

        if (numberOfIssuedBooks == 2){
            bookOneCallNumberLabel.setText(IssuedBook.bookCallNumbers[studentsIndex[0]]);
            bookTwoCallNumberLabel.setText(IssuedBook.bookCallNumbers[studentsIndex[1]]);

            bookOneDateLabel.setText(IssuedBook.issuedDates[studentsIndex[0]]);
            bookTwoDateLabel.setText(IssuedBook.issuedDates[studentsIndex[1]]);
        }

        if (numberOfIssuedBooks == 3){
            bookOneCallNumberLabel.setText(IssuedBook.bookCallNumbers[studentsIndex[0]]);
            bookTwoCallNumberLabel.setText(IssuedBook.bookCallNumbers[studentsIndex[1]]);
            bookThreeCallNumberLabel.setText(IssuedBook.bookCallNumbers[studentsIndex[2]]);

            bookOneDateLabel.setText(IssuedBook.issuedDates[studentsIndex[0]]);
            bookTwoDateLabel.setText(IssuedBook.issuedDates[studentsIndex[1]]);
            bookThreeDateLabel.setText(IssuedBook.issuedDates[studentsIndex[2]]);
        }

    }

}
