package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddIssuedBooks {

    private static TextField idField;
    private static TextField callNumberField;
    private static TextField studentIdField;
    private static TextField studentNameField;
    private static TextField studentContactField;
    private static DatePicker issuedDateField;

    public static void display(Stage window) {

        Label issueBookLabel = new Label("Issue Book");

        Label idLabel = new Label("id:");
        idLabel.setMinSize(150, 10);

        Label callNumberLabel = new Label("Call NO.:");
        callNumberLabel.setMinSize(150, 10);

        Label studentIdLabel = new Label("Student Id:");
        studentIdLabel.setMinSize(150, 10);

        Label studentNameLabel = new Label("Student Name:");
        studentNameLabel.setMinSize(150, 10);

        Label studentContactLabel = new Label("Student Contact:");
        studentContactLabel.setMinSize(150, 10);

        Label issuedDateLabel = new Label("issuedDate:");
        issuedDateLabel.setMinSize(150, 10);

        VBox labelLayout = new VBox(18.7);
        labelLayout.getChildren().addAll(idLabel, callNumberLabel, studentIdLabel, studentNameLabel, studentContactLabel, issuedDateLabel);

        idField = new TextField();
        idField.setMinSize(200, 10);
        idField.setPromptText("id");

        callNumberField = new TextField();
        callNumberField.setMinSize(200, 10);
        callNumberField.setPromptText("Call Number");

        studentIdField = new TextField();
        studentIdField.setMinSize(200, 10);
        studentIdField.setPromptText("Student id");

        studentNameField = new TextField();
        studentNameField.setMinSize(200, 10);
        studentNameField.setPromptText("Student Name");

        studentContactField = new TextField();
        studentContactField.setMinSize(200, 10);
        studentContactField.setPromptText("Student Contact");

        issuedDateField = new DatePicker();
        issuedDateField.setMinSize(200, 10);
        issuedDateField.setPromptText("day/month/year");
        issuedDateField.setEditable(false);

        VBox fieldLayout = new VBox(18);
        fieldLayout.getChildren().addAll(idField, callNumberField, studentIdField,
                studentNameField, studentContactField, issuedDateField);

        HBox informationLayout = new HBox(40);
        informationLayout.getChildren().addAll(labelLayout, fieldLayout);
        informationLayout.setAlignment(Pos.CENTER);

        Button issueBookButton = new Button("Issue Book");
        issueBookButton.setMinSize(150, 50);
        issueBookButton.setOnAction(e -> {

            if (fieldsCheck()) {
                if (checkStudent()) {
                    if (checkNumberOfBooks()) {
                        IssuedBook.readIssuedBooksFile();
                        readNewIssuedBook();
                        IssuedBook.writeIssuedBooksFile();
                        decrementQuantity();
                        AlertBox.display("Done", "Book Issued successfully!");
                        LibrarianSection.display(window);
                    }
                }
            }

        });


        VBox layout = new VBox(20);
        layout.getChildren().addAll(issueBookLabel, informationLayout, issueBookButton);
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

    public static void readNewIssuedBook() {

        IssuedBook.ids[IssuedBook.numberOfIssuedBooks] = idField.getText();
        IssuedBook.bookCallNumbers[IssuedBook.numberOfIssuedBooks] = callNumberField.getText();
        IssuedBook.studentIds[IssuedBook.numberOfIssuedBooks] = studentIdField.getText();
        IssuedBook.studentNames[IssuedBook.numberOfIssuedBooks] = studentNameField.getText();
        IssuedBook.studentContacts[IssuedBook.numberOfIssuedBooks] = studentContactField.getText();
        System.out.println(issuedDateField.getAccessibleText());
        IssuedBook.issuedDates[IssuedBook.numberOfIssuedBooks] = issuedDateField.getEditor().getText();

        IssuedBook.numberOfIssuedBooks++;

    }

    public static boolean checkStudent() {

        IssuedBook.readIssuedBooksFile();

        int numberOfIssuedBooks = 0;

        for (int i =0; i < IssuedBook.numberOfIssuedBooks; i++) {
            if (IssuedBook.studentNames[i].equals(studentNameField.getText()))
                numberOfIssuedBooks++;
        }

        if (numberOfIssuedBooks < 3)
            return true;

        AlertBox.display("error", "this student can not issue anymore books");
        return false;

    }

    private static boolean fieldsCheck() {

        Book.readBooksFile();
        IssuedBook.readIssuedBooksFile();
        Student.readStudentsFile();
        boolean foundCallNumber = false;
        boolean foundStudentId = false;
        int studentIndex = 0;

        //id check
        if (idField.getText().equals("")) {
            AlertBox.display("Wrong Input", "enter id");
            return false;
        }
        if (!isNumeric(idField.getText())) {
            AlertBox.display("Wrong Input", "enter right id");
            return false;
        }
        if (idField.getText().contains(" ")) {
            AlertBox.display("Wrong Input", "id can not have spaces");
            return false;
        }
        for (int i = 0; i < IssuedBook.numberOfIssuedBooks; i++) {
            if (idField.getText().equals(IssuedBook.ids[i])){
                AlertBox.display("Wrong Input", "this id is already taken");
                return false;
            }
        }

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
            if (callNumberField.getText().equals(Book.bookCallNumbers[i]))
                foundCallNumber = true;
        }
        if (!foundCallNumber) {
            AlertBox.display("Wrong Input", "book not found");
            return false;
        }

        //student id check
        if (studentIdField.getText().equals("")) {
            AlertBox.display("Wrong Input", "enter student id");
            return false;
        }
        if (studentIdField.getText().contains(" ")) {
            AlertBox.display("Wrong Input", "student id can not have spaces");
            return false;
        }
        if (!isNumeric(studentIdField.getText())) {
            AlertBox.display("Wrong Input", "enter right student id");
            return false;
        }
        for (int i = 0; i < Student.numberOfStudents; i++) {
            if (studentIdField.getText().equals(Student.ids[i])) {
                foundStudentId = true;
                studentIndex = i;
            }
        }
        if (!foundStudentId) {
            AlertBox.display("Wrong Input", "student id not found");
            return false;
        }

        //student name check
        if (studentNameField.getText().equals("")) {
            AlertBox.display("Wrong Input", "enter student username");
            return false;
        }
        if (studentNameField.getText().contains(" ")) {
            AlertBox.display("Wrong Input", "student username can not have spaces");
            return false;
        }
        if (!studentNameField.getText().equals(Student.names[studentIndex])) {
            AlertBox.display("Wrong Input", "student username does not match student id");
            return false;
        }

        //student contact check
        if (studentContactField.getText().equals("")) {
            AlertBox.display("Wrong Input", "enter student contact");
            return false;
        }
        if (studentContactField.getText().contains(" ")) {
            AlertBox.display("Wrong Input", "student contact can not have spaces");
            return false;
        }
        if (!isNumeric(studentContactField.getText())) {
            AlertBox.display("Wrong Input", "enter right student contact");
            return false;
        }
        if (!studentContactField.getText().equals(Student.contacts[studentIndex])) {
            AlertBox.display("Wrong Input", "student contact does not match student id");
            return false;
        }

        //date check
        if (issuedDateField.getEditor().getText().equals("")) {
            AlertBox.display("Wrong Input", "enter issue date");
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

    private static void decrementQuantity() {

        Book.readBooksFile();

        for (int i = 0; i < Book.numberOfBooks; i++) {
            if (callNumberField.getText().equals(Book.bookCallNumbers[i])) {
                int quantity = Integer.parseInt(Book.quantities[i]) - 1;
                Book.quantities[i] = "" + quantity;
            }
        }

        Book.writeBooksFile();

    }

    private static boolean checkNumberOfBooks() {

        Book.readBooksFile();
        for (int i = 0; i < Book.numberOfBooks; i++) {
            if (callNumberField.getText().equals(Book.bookCallNumbers[i])) {
                if (Integer.parseInt(Book.quantities[i]) == 0) {
                    AlertBox.display("Error", "this book is not available right now");
                    return false;
                }
            }
        }
        return true;
    }

}
