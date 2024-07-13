package dinus.pbo.appperpus3.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private void home(ActionEvent event) {
        try {
            // Load the Home view
            Parent homeViewParent = FXMLLoader.load(getClass().getResource("/dinus/pbo/appperpus3/views/fhome.fxml"));
            Scene homeViewScene = new Scene(homeViewParent);

            // Get the Stage (window) information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene to home view
            window.setScene(homeViewScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
