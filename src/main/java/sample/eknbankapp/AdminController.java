package sample.eknbankapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private ImageView logoImageView;
    public void initialize(URL location, ResourceBundle resources) {
        File logoImageFile = new File("Image/logoImage.jpg");
        Image logoImage = new Image(logoImageFile.toURI().toString());
        logoImageView.setImage(logoImage);
    }
    @FXML
    private Label adminNameLabel;

    public void setFullName(String fullName) {
        adminNameLabel.setText(fullName);
    }
    @FXML
    private Button exitButton;

    public void exitButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    private Button cryptoButton;
    @FXML
    public void cryptoButtonOnAction(ActionEvent event) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI("http://localhost:63342/EknBankApp/src/main/Selenium/CryptoBord.html?_ijt=rf3lt2bfk4modh3k5vss587pl"));
    }

    public void customerSettingView(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerSettingView.fxml"));
            Parent root = loader.load();
            Stage customerSettingViewStage = new Stage();
            customerSettingViewStage.initStyle(StageStyle.UNDECORATED);
            customerSettingViewStage.setScene(new Scene(root,800,500));
            customerSettingViewStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void customerSettingButtonOnAction(ActionEvent event) throws IOException {
        customerSettingView();
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

}
