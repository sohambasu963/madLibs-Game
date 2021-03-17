package madlibsProject;

//import statements
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MadLibsUI extends Application {
    /**
     * Starting the program by launching the Home Menu
     * Launches when user runs the program
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("homeMenu.fxml"));
        primaryStage.setTitle("MadLibs: Home Menu");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main (String[]args) {
        launch(args);
    }

}
