package madlibsProject;

//import statements
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;

public class homeMenu {
    //creating fields for all of the buttons
    public Button startGame;
    public Button aboutInstructions;
    public Button aboutHelp;
    public Button quit;
    public Button openFiles;

    /**
     * If user clicks on Start Game from the Home Menu, a new window opens asking them to choose a story option
     */
    public void madlibsStoryOptions(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("MadLibs: Story Options");
        //removes header
        alert.setHeaderText(null);
        alert.setContentText("Choose one of the following story options: ");
        //creating three buttons for three different story options
        ButtonType storyOne = new ButtonType("Story 1");
        ButtonType storyTwo = new ButtonType("Story 2");
        ButtonType storyThree = new ButtonType("Story 3");
        //if user clicks cancel, the window will close and return to home menu
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(storyOne, storyTwo, storyThree, cancel);
        //shows options and waits for user to make a decision
        Optional<ButtonType> story = alert.showAndWait();
        //if user chose story 1
        if (story.get() == storyOne) {
            //loading story 1 by getting the stage and changing scenes
            Parent root = FXMLLoader.load(getClass().getResource("storyOne.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("MadLibs: Story One");
            window.show();
        }
        //if user chose story 2
        else if (story.get() == storyTwo) {
            //loading story 2 by getting the stage and changing scenes
            Parent root = FXMLLoader.load(getClass().getResource("storyTwo.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("MadLibs: Story Two");
            window.show();
        }
        //if user chose story 3
        else if (story.get() == storyThree) {
            //loading story 3 by getting the stage and changing scenes
            Parent root = FXMLLoader.load(getClass().getResource("storyThree.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.setTitle("MadLibs: Story Three");
            window.show();
        }
    }

    /**
     * A list of instructions for users on how to play the game
     * Launches if user clicks on Instructions from Home Menu
     */
    public void showInstructions(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("MadLibs: Instructions");
        alert.setContentText("Once you have started the game, a window will pop up asking you to choose a story. " +
        "Once you have chosen one, you must come up with words and fill in the text fields. The appropriate type of " +
        "word is listed beside each text field. For example, it might ask for a noun, verb, or an adjective." +
         " After you are finished, click Next. This will display your unique MadLibs story. If you would like, you can " +
         "save your MadLib by clicking Save.");
        //removes header
        alert.setHeaderText(null);
        //shows message and waits for user to proceed
        alert.showAndWait();
    }

    /**
     * Help message for users who want to know more about how the game functions
     * Launches if user clicks on Help from Home Menu
     */
    public void helpMessage(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("MadLibs: About This Program");
        alert.setContentText("This is a MadLibs game created as a JavaFx Project by Soham Basu. " +
                "To learn how to play the game, please refer to the Instructions. " +
                "To stop playing, press Quit. To open a previously saved MadLib, click Open Files where you can" +
                "open your MadLib, edit it, and re save it.");
        //removes header
        alert.setHeaderText(null);
        //shows message and waits for user to proceed
        alert.showAndWait();
    }

    /**
     * If user wants to quit, they are asked to confirm
     * If they say yes, all windows are closed and program stops running
     * Launches if user clicks on Quit from Home Menu
     */
    public void closeWindow(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("MadLibs: Quit Confirmation");
        alert.setContentText("Are you sure you want to quit? ");
        ButtonType yes = new ButtonType("Yes");
        //if user clicks no, the window will close and return to home menu
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(yes, no);
        //shows options and waits for user to make a decision
        Optional<ButtonType> confirmation = alert.showAndWait();
        //if the user chose to quit game
        if (confirmation.get() == yes) {
            //closes all windows and provide user with an end of game message
            System.out.println("You chose to quit the game. Thanks for playing!");
            Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            window.close();
        }
    }

    public void showFileOpeningSpace(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("storyOpener.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("MadLibs: Story Opener");
        window.show();
    }
}
