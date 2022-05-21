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

public class ReturnBook {

    private static TextField studentIdField;
    private static TextField bookCallNumberField;
    private static DatePicker returnDateField;
    private static int indexInIssuedBooksFile;
    private static int issueDay;
    private static int issueMonth;
    private static int issueYear;
    private static int returnDay;
    private static int returnMonth;
    private static int returnYear;

    public static void display(Stage window) {

        Label returnBookLabel = new Label("Return Book");

        Label bookCallNumberLabel = new Label("Book Call NO.:");
        bookCallNumberLabel.setMinSize(200 , 10);

        Label studentIdLabel = new Label("Student id:");
        studentIdLabel.setMinSize(200 , 10);

        Label returnDateLabel = new Label("Return Date:");
        returnDateLabel.setMinSize(200 , 10);

        VBox labelLayout = new VBox(18);
        labelLayout.getChildren().addAll(bookCallNumberLabel, studentIdLabel, returnDateLabel);

        bookCallNumberField = new TextField();
        bookCallNumberField.setMinSize(250 , 10);
        bookCallNumberField.setPromptText("Book Call Number");

        studentIdField = new TextField();
        studentIdField.setMinSize(250 , 10);
        studentIdField.setPromptText("Student id");

        returnDateField = new DatePicker();
        returnDateField.setMinSize(250, 10);
        returnDateField.setPromptText("day/month/year");
        returnDateField.setEditable(false);

        VBox fieldLayout = new VBox(18);
        fieldLayout.getChildren().addAll(bookCallNumberField, studentIdField, returnDateField);

        HBox informationLayout = new HBox(75);
        informationLayout.getChildren().addAll(labelLayout, fieldLayout);
        informationLayout.setAlignment(Pos.CENTER);

        Button returnBookButton = new Button("Return Book");
        returnBookButton.setMinSize(200, 50);
        returnBookButton.setOnAction(e -> {

            if (fieldsCheck()) {
                IssuedBook.readIssuedBooksFile();
                fineCheck();
                deleteFromFile();
                incrementQuantity();
                IssuedBook.writeIssuedBooksFile();
                IssuedBook.readIssuedBooksFile();
                IssuedBook.writeIssuedBooksFile();
                LibrarianSection.display(window);
            }

        });


        VBox layout = new VBox(40);
        layout.getChildren().addAll(returnBookLabel, informationLayout, returnBookButton);
        layout.setAlignment(Pos.CENTER);

        Button backButton = new Button("Back");
        backButton.setMinSize(100, 10);
        backButton.setOnAction(e -> LibrarianSection.display(window));

        VBox finalLayout = new VBox(20);
        finalLayout.getChildren().addAll(layout, backButton);
        finalLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(finalLayout, 600, 600);
        scene.getStylesheets().add("library.css");
        window.setScene(scene);

    }

    private static void deleteFromFile() {

        IssuedBook.readIssuedBooksFile();

        IssuedBook.ids[indexInIssuedBooksFile] = "";
        IssuedBook.bookCallNumbers[indexInIssuedBooksFile] = "";
        IssuedBook.studentIds[indexInIssuedBooksFile] = "";
        IssuedBook.studentNames[indexInIssuedBooksFile] = "";
        IssuedBook.studentContacts[indexInIssuedBooksFile] = "";
        IssuedBook.issuedDates[indexInIssuedBooksFile] = "";

        AlertBox.display("Done", "Book Returned successfully!");

    }

    private static void incrementQuantity() {

        Book.readBooksFile();

        for (int i = 0; i < Book.numberOfBooks; i++) {
            if (bookCallNumberField.getText().equals(Book.bookCallNumbers[i])) {
                int quantity = Integer.parseInt(Book.quantities[i]) + 1;
                Book.quantities[i] = "" + quantity;
            }
        }

        Book.writeBooksFile();

    }

    private static void setReturnDate() {

        returnDay = Integer.parseInt("" + returnDateField.getEditor().getText().charAt(0) + "" +
                returnDateField.getEditor().getText().charAt(1));

        returnMonth = Integer.parseInt("" + returnDateField.getEditor().getText().charAt(3) + "" +
                returnDateField.getEditor().getText().charAt(4));

        returnYear = Integer.parseInt("" + returnDateField.getEditor().getText().charAt(6) + "" +
                returnDateField.getEditor().getText().charAt(7) + "" +
                returnDateField.getEditor().getText().charAt(8) + "" +
                returnDateField.getEditor().getText().charAt(9));

    }

    private static void setIssueDate() {

        issueDay = Integer.parseInt("" + IssuedBook.issuedDates[indexInIssuedBooksFile].charAt(0) + "" +
                IssuedBook.issuedDates[indexInIssuedBooksFile].charAt(1));

        issueMonth = Integer.parseInt("" + IssuedBook.issuedDates[indexInIssuedBooksFile].charAt(3) + "" +
                IssuedBook.issuedDates[indexInIssuedBooksFile].charAt(4));

        issueYear = Integer.parseInt("" + IssuedBook.issuedDates[indexInIssuedBooksFile].charAt(6) + "" +
                IssuedBook.issuedDates[indexInIssuedBooksFile].charAt(7) + "" +
                IssuedBook.issuedDates[indexInIssuedBooksFile].charAt(8) + "" +
                IssuedBook.issuedDates[indexInIssuedBooksFile].charAt(9));

    }

    private static void fineCheck() {

        setIssueDate();
        setReturnDate();
        boolean isSameDate = false;

        if (issueDay == returnDay && issueMonth == returnMonth && issueYear == returnYear)
            isSameDate = true;

        if (!isSameDate)
            AlertBox.display("warning", "student should pay fine");

    }

    private static boolean fieldsCheck() {

        Book.readBooksFile();
        IssuedBook.readIssuedBooksFile();
        //boolean foundCallNumber = false;
        boolean foundStudentId = false;
        int numberOfStudentIssuedBooks = 0;
        int[] index = new int[3];


        //call number check
        if (bookCallNumberField.getText().equals("")) {
            AlertBox.display("Wrong Input", "enter book call number");
            return false;
        }
        if (bookCallNumberField.getText().contains(" ")) {
            AlertBox.display("Wrong Input", "book call number can not have spaces");
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
        for (int i = 0; i < IssuedBook.numberOfIssuedBooks; i++) {
            if (studentIdField.getText().equals(IssuedBook.studentIds[i])) {
                foundStudentId = true;
            }
        }
        if (!foundStudentId) {
            AlertBox.display("Wrong Input", "student id not found");
            return false;
        }

        //id and call no. match check
        int j = 0;
        for (int i = 0; i < IssuedBook.numberOfIssuedBooks; i++) {
            if (studentIdField.getText().equals(IssuedBook.studentIds[i])) {
                index[j] = i;
                j++;
                numberOfStudentIssuedBooks++;
            }
        }
        boolean found = false;
        for (int i = 0; i < numberOfStudentIssuedBooks; i++) {
            if (bookCallNumberField.getText().equals(IssuedBook.bookCallNumbers[index[i]])){
                indexInIssuedBooksFile = i;
                found = true;
                break;
            }
        }
        if (!found) {
            AlertBox.display("Wrong Input", "book call number does not match student id");
            return false;
        }

        //date check
        if (returnDateField.getEditor().getText().equals("")) {
            AlertBox.display("Wrong Input", "enter return date");
            return false;
        }
        setIssueDate();
        setReturnDate();
        if (returnYear > issueYear) {
            return true;
        }
        else if (returnYear < issueYear) {
            AlertBox.display("Wrong Input", "enter right return date");
            return false;
        }
        else {
            if (returnMonth > issueMonth) {
                return true;
            }
            else if (returnMonth < issueMonth) {
                AlertBox.display("Wrong Input", "enter right return date");
                return false;
            }
            else {
                if (returnDay > issueDay) {
                    return true;
                }
                else if (returnDay < issueDay) {
                    AlertBox.display("Wrong Input", "enter right return date");
                    return false;
                }
                else
                    return true;
            }
        }

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
