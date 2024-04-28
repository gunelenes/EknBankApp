package sample.eknbankapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
    }/*
    public void setPassword(String password) {
        adminNameLabel.setText(password);
    }
    public void setIdentityNumber(String identityNumber) {
        adminNameLabel.setText(identityNumber);
    }
    public void serEmail(String eMail){
        adminNameLabel.setText(eMail);
    }*/
    @FXML
    private Button exitButton;

    public void exitButtonOnAction(ActionEvent event) throws IOException {

        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }


}
