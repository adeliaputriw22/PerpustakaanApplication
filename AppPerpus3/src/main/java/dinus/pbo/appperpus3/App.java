package dinus.pbo.appperpus3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/fxml/fmenu.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Aplikasi Perpustakaan");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {

        launch();
    }
}