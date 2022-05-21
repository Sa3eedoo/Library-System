/*
-----------------------------------------------------------------------------------------------------

this program is implemented with javafx so you have to add the bath to the library of javafx folder
before running

steps for IntelliJ IDE:
1- Run >> Edit Configurations >> Modify options >> Add VM options.
2- --module-path "" --add-modules javafx.controls,javafx.fxml
3- copy step number 2 and paste it in the VM options.
4- copy the path of the lib file located in the project file and paste it between the quotations marks.
5- press apply then okay.

steps for Eclipse IDE:
1- Run >> Run Configurations... >> Arguments.
2- --module-path "" --add-modules javafx.controls,javafx.fxml
3- copy step number 2 and paste it in the VM arguments.
4- copy the path of the lib file located in the project file and paste it between the quotations marks.
5- press apply then Run.

-----------------------------------------------------------------------------------------------------
*/

/*
admin username: admin
admin password: admin
*/

package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        Stage window;
        window = primaryStage;
        new LibraryManagement(window);
        window.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
