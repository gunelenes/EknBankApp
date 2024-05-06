package sample.eknbankapp;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.ComboBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class SettingForCustomerController implements Initializable {
    public int loop = 0;
    @FXML
    private ImageView logoImageView;


    @FXML
    private ComboBox<String> comboBox;
    private final String[][] cryptoValue = getCrypto();

    public SettingForCustomerController() throws IOException {
    }

    public void initialize(URL location, ResourceBundle resources) {
        File logoImageFile = new File("Image/logoImage.jpg");
        Image logoImage = new Image(logoImageFile.toURI().toString());
        logoImageView.setImage(logoImage);


        boolean flagFirstItem = true;
        // cryptoValue dizisinin ilk elemanlarını comboBox'a ekle
        for (String[] crypto : cryptoValue) {
            if(flagFirstItem!=true) {
                comboBox.getItems().add(crypto[0]);
                //comboBox.setItems(FXCollections.observableArrayList(crypto[0]));
                }
           flagFirstItem = false;
        }


    }
    @FXML
    private Button exitButton;

    public void exitButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
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

    public String[][] getCrypto () throws IOException {
        File file = new File("TextFolders/Crypto.txt");
        FileReader fReader = new FileReader(file);
        String line;
        loop = 0;
        //Dosyada gezinme komutu
        BufferedReader bReader = new BufferedReader(fReader);
        while ((line = bReader.readLine()) != null) {
            loop++;
        }

        String[][] cryptoInformation = new String[loop][2];
        loop = 0;
        int loopForValue =0;
        fReader = new FileReader(file);
        bReader = new BufferedReader(fReader);
        while ((line = bReader.readLine()) != null) {
            String[] employee = line.split(" ");
            for (String a : employee){
                cryptoInformation[loop][loopForValue] = a;
                loopForValue++;
            }
            loop++;
            loopForValue =0;
        }
        bReader.close();
        return cryptoInformation;
    }

/*
    public void getCombobaxValue(ComboBox<String> comboBox){
        // ComboBox oluştur
        for (String[] crypto : cryptoValue) {
            comboBox.getItems().add(crypto[0]);
        }

    }*/

}
