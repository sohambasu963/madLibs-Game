package madlibsProject;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class storyOpener {
    public TextArea storyOpeningSpace;
    public AnchorPane window;
    public Button open;
    public Button save;
    public Button returnToHomeMenu;

    public void returnToHomeMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homeMenu.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("MadLibs: Home Menu");
        window.show();
    }

    public void openFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files","*.txt"));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        Stage stage;
        stage = (Stage)window.getScene().getWindow();
        File currentFile = fileChooser.showOpenDialog(stage);

        String textFromFile = "";

        try {
            FileReader fr = new FileReader(currentFile.getAbsolutePath());
            BufferedReader br = new BufferedReader(fr);
            String s = "";

            while ((s = br.readLine()) != null) {
                textFromFile += s;
            }
        }
        catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("MadLibs: Error Checking");
            alert.setHeaderText(null);
            alert.setContentText("There was no file found with that name. Check the name entered and try again.");
            alert.showAndWait();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("MadLibs: Error Checking");
            alert.setHeaderText(null);
            alert.setContentText("There was an issue reading from the file. Please try again.");
            alert.showAndWait();
            //if user decides to save file and later cancel their decision
        } catch (NullPointerException e) {
            return;
        }
        storyOpeningSpace.setText(textFromFile);
        storyOpeningSpace.setVisible(true);
        storyOpeningSpace.setWrapText(true);
    }

    public void saveFile(ActionEvent actionEvent) throws IOException {
        if (storyOpeningSpace.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("MadLibs: Error Checking");
            alert.setHeaderText(null);
            alert.setContentText("There is no text in the text box. This means that you will be saving an empty file.");
            alert.showAndWait();
        }
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("MadLibs: Error Checking");
            alert.setHeaderText(null);
            alert.setContentText("Error opening the file. Please try again.");
            alert.showAndWait();
            return;
        }
        //if user decides to save and later cancels their decision
        catch (NullPointerException e) {
            return;
        }

        try {
            fw.write(storyOpeningSpace.getText());
        }
        catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("MadLibs: Error Checking");
            alert.setHeaderText(null);
            alert.setContentText("Error writing the file. Please try again. ");
            alert.showAndWait();
        }
        try {
            fw.close();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("MadLibs: Error Checking");
            alert.setHeaderText(null);
            alert.setContentText("Error closing the file. Please try again.");
            alert.showAndWait();
        }
    }
}
