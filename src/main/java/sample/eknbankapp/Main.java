package sample.eknbankapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        //primaryStage.setTitle("EknBank App");
        primaryStage.setScene(new Scene(root,520,400));
        primaryStage.show();
    }
}
