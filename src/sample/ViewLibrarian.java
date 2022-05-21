package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewLibrarian {

    public static void display(Stage window) {

        TableColumn<Librarian, String> idColumn  = new TableColumn<>("id");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(new PropertyValueFactory<Librarian, String>("id"));

        TableColumn<Librarian, String> nameColumn  = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Librarian, String>("name"));

        TableColumn<Librarian, String> passwordColumn  = new TableColumn<>("Password");
        passwordColumn.setMinWidth(200);
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Librarian, String>("password"));

        TableColumn<Librarian, String> emailColumn  = new TableColumn<>("Email");
        emailColumn.setMinWidth(300);
        emailColumn.setCellValueFactory(new PropertyValueFactory<Librarian, String>("email"));

        TableColumn<Librarian, String> addressColumn  = new TableColumn<>("Address");
        addressColumn.setMinWidth(200);
        addressColumn.setCellValueFactory(new PropertyValueFactory<Librarian, String>("address"));

        TableColumn<Librarian, String> cityColumn  = new TableColumn<>("City");
        cityColumn.setMinWidth(200);
        cityColumn.setCellValueFactory(new PropertyValueFactory<Librarian, String>("city"));

        TableColumn<Librarian, String> contactColumn  = new TableColumn<>("Contact");
        contactColumn.setMinWidth(200);
        contactColumn.setCellValueFactory(new PropertyValueFactory<Librarian, String>("contact"));

        TableView<Librarian> tableView = new TableView<>();
        tableView.setItems(getLibrarian());
        tableView.getColumns().addAll(idColumn, nameColumn, passwordColumn, emailColumn, addressColumn, cityColumn, contactColumn);

        Button backButton = new Button("Back");
        backButton.setMinSize(150, 20);
        backButton.setOnAction(e -> AdminSection.display(window));

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(10,10,10,10));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(tableView, backButton);

        Scene scene = new Scene(layout, 1422,600);
        scene.getStylesheets().add("library.css");
        window.setScene(scene);

    }

    private static ObservableList<Librarian> getLibrarian() {

        ObservableList<Librarian> Librarians = FXCollections.observableArrayList();

        Librarian.readLibrariansFile();

        for (int i = 0;i < Librarian.numberOfLibrarians;i++) {
            Librarians.add(new Librarian(Librarian.ids[i], Librarian.names[i], Librarian.passwords[i], Librarian.emails[i],
                    Librarian.addresses[i], Librarian.cities[i], Librarian.contacts[i]));
        }

        return Librarians;

    }

}
