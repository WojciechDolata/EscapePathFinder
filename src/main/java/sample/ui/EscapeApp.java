package sample.ui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EscapeApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        Scene scene = new Scene(root, 300, 300);

        stage.setTitle("Evacuation App");
        stage.setScene(scene);
        stage.show();

        //this.stage = stage;
        //this.stage.setScene(scene);
        //this.stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
