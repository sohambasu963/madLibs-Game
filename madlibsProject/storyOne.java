package madlibsProject;

//import statements
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class storyOne {
//creating fields for all of the buttons and text fields
    public Button homeMenu;
    public Button finishedEnteringWords; //a.k.a. next button
    public TextField word1;
    public TextField word2;
    public TextField word3;
    public TextField word4;
    public TextField word5;
    public TextField word6;
    public TextField word7;
    public TextField word8;
    public TextField word9;
    public TextField word10;
    public AnchorPane window;

    /**
     * If user wants to return to the home menu in the middle of their game
     * Launches if user clicks on Return to Home Menu button
     */
    public void returnToHomeMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homeMenu.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("MadLibs: Home Menu");
        window.show();
    }

    /**
     * Shows user their MadLibs story
     * Launches if user has filled in all text fields and clicked Next
     */
    public void showResult(ActionEvent actionEvent) throws IOException {
        //if any of the text fields are empty
        if (word1.getText().isEmpty()||word2.getText().isEmpty()||word3.getText().isEmpty()||
                word4.getText().isEmpty()||word5.getText().isEmpty()||word6.getText().isEmpty()||
                word7.getText().isEmpty()||word8.getText().isEmpty()||word9.getText().isEmpty()||
                word10.getText().isEmpty()) {
                //warn user that some text fields are empty and have them complete them before proceeding
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("MadLibs: Warning");
                alert.setContentText("One or more text fields were left blank. Please fill in these words before continuing.");
                alert.showAndWait();
        }
        //if all text fields are completed
        else {
            //opens a new window where user is shown their MadLibs story
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("MadLibs: Results");

            String story = "Here is your MadLibs story:                                                         "
            +"The "+ word1.getText()+" dragon is the "+ word2.getText()+" dragon of all. It has "+word3.getText() +" "
            +word4.getText()+", and a "+word5.getText()+" shaped like a "+word6.getText()+". It loves to eat "+word7.getText()
            +", although it will feast on nearly anything. It is "+word8.getText()+" and "+word9.getText()+". You must be "
            +word10.getText()+" around it, or you may end up as its meal!";

            alert.setContentText(story);
            //remove Header
            alert.setHeaderText(null);

            //declare all buttons, set them, and show content of alert
            ButtonType save = new ButtonType("Save");
            ButtonType close = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(save, close);
            Optional<ButtonType> options = alert.showAndWait();

            //if user chooses to save their story
            if (options.get() == save) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files","*.txt"));
                fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
                Stage stage;
                stage = (Stage)window.getScene().getWindow();
                File currentFile = fileChooser.showSaveDialog(stage);

                FileWriter fw;

                try {
                    fw = new FileWriter(currentFile.getAbsolutePath());
                }
                catch (IOException e) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("MadLibs: Error Checking");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Error opening the file. Please try again.");
                    alert2.showAndWait();
                    return;
                }
                catch (NullPointerException e) {
                    return;
                }

                try {
                    fw.write(story);
                }
                catch (IOException e) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("MadLibs: Error Checking");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Error writing the file. Please try again.");
                    alert2.showAndWait();
                }
                try {
                    fw.close();
                } catch (IOException e) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("MadLibs: Error Checking");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Error closing the file. Please try again.");
                    alert2.showAndWait();
                }
            }
            //once user either closes or saves their story, they are taken to the Home Menu
            //this is so that they can play a new game or open their saved story
            returnToHomeMenu(actionEvent);

        }
    }

}
