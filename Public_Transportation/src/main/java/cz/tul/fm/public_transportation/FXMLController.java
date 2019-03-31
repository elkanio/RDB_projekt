package cz.tul.fm.public_transportation;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FXMLController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private JFXButton importButton, exportButton;

    @FXML
    private void importButtonAction(ActionEvent event) {
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV soubory (*.csv)", "*.csv");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Vyberte soubor");
        Stage fileChooserStage = new Stage();
        File file = fileChooser.showOpenDialog(fileChooserStage);
        if (file != null) {
            importData(file);
        }
    }

    @FXML
    private void exportButtonAction(ActionEvent event) {
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV soubory (*.csv)", "*.csv");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(extFilter);
        File dest = fileChooser.showSaveDialog(new Stage());
        if (dest != null) {
            saveFile(dest);

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    private void importData(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void saveFile(File dest) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //Files.copy(file.toPath(), dest.toPath());
    }
}
