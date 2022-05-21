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

import java.util.regex.Pattern;

public class AddLibrarian {

    private static TextField idField;
    private static TextField nameField;
    private static PasswordField passwordField;
    private static TextField emailField;
    private static TextField addressField;
    private static TextField cityField;
    private static TextField contactNumberField;

    public static void display(Stage window) {

        Label addLibrarianLabel = new Label("Add Librarian");

        Label idLabel = new Label("id:");
        idLabel.setMinSize(150, 10);

        Label nameLabel = new Label("Name:");
        nameLabel.setMinSize(150, 10);

        Label passwordLabel = new Label("Password:");
        passwordLabel.setMinSize(150, 10);

        Label emailLabel = new Label("Email:");
        emailLabel.setMinSize(150, 10);

        Label addressLabel = new Label("Address:");
        addressLabel.setMinSize(150, 10);

        Label cityLabel = new Label("City:");
        cityLabel.setMinSize(150, 10);

        Label contactNumberLabel = new Label("Contact No.:");
        contactNumberLabel.setMinSize(150, 10);

        VBox labelLayout = new VBox(10);
        labelLayout.getChildren().addAll(idLabel, nameLabel, passwordLabel, emailLabel, addressLabel, cityLabel, contactNumberLabel);

        idField = new TextField();
        idField.setMinSize(200, 10);
        idField.setPromptText("id");

        nameField = new TextField();
        nameField.setMinSize(200, 10);
        nameField.setPromptText("name");

        passwordField = new PasswordField();
        passwordField.setMinSize(200, 10);
        passwordField.setPromptText("password");

        emailField = new TextField();
        emailField.setMinSize(200, 10);
        emailField.setPromptText("email");

        addressField = new TextField();
        addressField.setMinSize(200, 10);
        addressField.setPromptText("address");

        cityField = new TextField();
        cityField.setMinSize(200, 10);
        cityField.setPromptText("city");

        contactNumberField = new TextField();
        contactNumberField.setMinSize(200, 10);
        contactNumberField.setPromptText("contact number");

        VBox fieldLayout = new VBox(10);
        fieldLayout.getChildren().addAll(idField, nameField, passwordField, emailField, addressField,
                cityField, contactNumberField);

        HBox informationLayout = new HBox(75);
        informationLayout.getChildren().addAll(labelLayout, fieldLayout);
        informationLayout.setAlignment(Pos.CENTER);

        Button addLibrarianButton = new Button("Add Librarian");
        addLibrarianButton.setMinSize(150, 20);
        addLibrarianButton.setOnAction(e -> {

            if (fieldsCheck()) {
                Librarian.readLibrariansFile();
                readNewLibrarian();
                Librarian.writeLibrariansFile();
                AlertBox.display("Done", "Librarian added successfully!");
                AdminSection.display(window);
            }

        });


        VBox layout = new VBox(10);
        layout.getChildren().addAll(addLibrarianLabel, informationLayout, addLibrarianButton);
        layout.setAlignment(Pos.CENTER);

        Button backButton = new Button("Back");
        backButton.setMinSize(100, 20);
        backButton.setOnAction(e -> AdminSection.display(window));

        VBox finalLayout = new VBox(10);
        finalLayout.getChildren().addAll(layout, backButton);
        finalLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(finalLayout, 600, 600);
        scene.getStylesheets().add("library.css");
        window.setScene(scene);

    }

    public static void readNewLibrarian() {

            Librarian.ids[Librarian.numberOfLibrarians] = idField.getText();
            Librarian.names[Librarian.numberOfLibrarians] = nameField.getText();
            Librarian.passwords[Librarian.numberOfLibrarians] = passwordField.getText();
            Librarian.emails[Librarian.numberOfLibrarians] = emailField.getText();
            Librarian.addresses[Librarian.numberOfLibrarians] = addressField.getText();
            Librarian.cities[Librarian.numberOfLibrarians] = cityField.getText();
            Librarian.contacts[Librarian.numberOfLibrarians] = contactNumberField.getText();

            Librarian.numberOfLibrarians++;

    }

    private static boolean fieldsCheck() {

        Librarian.readLibrariansFile();

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
        for (int i = 0; i < Librarian.numberOfLibrarians; i++) {
            if (idField.getText().equals(Librarian.ids[i])){
                AlertBox.display("Wrong Input", "this id is already taken");
                return false;
            }
        }

        //name check
        if (nameField.getText().equals("")) {
            AlertBox.display("Wrong Input", "enter username");
            return false;
        }
        if (nameField.getText().contains(" ")) {
            AlertBox.display("Wrong Input", "username can not have spaces");
            return false;
        }
        for (int i = 0; i < Librarian.numberOfLibrarians; i++) {
            if (nameField.getText().equals(Librarian.names[i])){
                AlertBox.display("Wrong Input", "this username is already taken");
                return false;

            }
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

        //email check
        if (emailField.getText().equals("")) {
            AlertBox.display("Wrong Input", "enter email");
            return false;
        }
        if (!isEmail(emailField.getText())) {
            AlertBox.display("Wrong Input", "enter right email");
            return false;
        }
        if (emailField.getText().contains(" ")) {
            AlertBox.display("Wrong Input", "email can not have spaces");
            return false;
        }
        for (int i = 0; i < Librarian.numberOfLibrarians; i++) {
            if (emailField.getText().equals(Librarian.emails[i])){
                AlertBox.display("Wrong Input", "this email is already taken");
                return false;
            }
        }

        //address check
        if (addressField.getText().equals("")) {
            AlertBox.display("Wrong Input", "enter address");
            return false;
        }
        if (addressField.getText().contains(" ")) {
            AlertBox.display("Wrong Input", "address can not have spaces");
            return false;
        }

        //city check
        if (cityField.getText().equals("")) {
            AlertBox.display("Wrong Input", "enter city");
            return false;
        }
        if (cityField.getText().contains(" ")) {
            AlertBox.display("Wrong Input", "city can not have spaces");
            return false;
        }

        //contact number check
        if (contactNumberField.getText().equals("")) {
            AlertBox.display("Wrong Input", "enter contact number");
            return false;
        }
        if (!isNumeric(contactNumberField.getText())) {
            AlertBox.display("Wrong Input", "enter right contact number");
            return false;
        }
        if (contactNumberField.getText().contains(" ")) {
            AlertBox.display("Wrong Input", "contact number can not have spaces");
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

    private static boolean isEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

}
