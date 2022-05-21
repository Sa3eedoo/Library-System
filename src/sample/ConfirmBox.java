package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    static boolean answer;

    public static boolean display(String title, String message) {

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label(message);

        Button yesButton = new Button("YES");
        yesButton.setMinSize(100, 10);
        yesButton.setOnAction(e -> {

            answer = true;
            window.close();

        });

        Button noButton = new Button("NO");
        noButton.setMinSize(100, 10);
        noButton.setOnAction(e -> {

            answer = false;
            window.close();

        });


        HBox yesNoLayout = new HBox(40);
        yesNoLayout.setAlignment(Pos.CENTER);
        yesNoLayout.getChildren().addAll(yesButton, noButton);

        VBox layout = new VBox(30);
        layout.getChildren().addAll(label, yesNoLayout);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 250);
        scene.getStylesheets().add("library.css");
        window.setScene(scene);
        window.showAndWait();

        return answer;

    }

}
