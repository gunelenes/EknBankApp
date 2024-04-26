package sample.eknbankapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.StageStyle;

import java.io.*;
import java.util.ResourceBundle;
import java.net.URL;
public class LoginController implements Initializable {

    @FXML
    private Button cancelButton;

    public void cancelButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();






       //txt dosyası içinde satır satır gezinme
/*
        File file = new File("TextFolders/Employees.txt");
        if (!file.exists()) {
            loginMessageLabel.setText("File does not exist");
        }

        FileReader fReader = new FileReader(file);
        String line;

        //Dosyada gezinme komutu
        BufferedReader bReader = new BufferedReader(fReader);
        while ((line = bReader.readLine()) != null) {
            System.out.println(line);
        }
        bReader.close();*/
    }


    @FXML
    private ImageView solMenuView;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File solMenuFile = new File("Image/bird-thumbnail.jpg");
        Image solMenuImage = new Image(solMenuFile.toURI().toString());
        solMenuView.setImage(solMenuImage);
    }

    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    public void loginButtonOnAction(ActionEvent event) throws IOException {

        employeeView();
        //txt dosyasında yeni satıra kod yazma
        /*
        File file = new File("TextFolders/Employees.txt");
        if (!file.exists()) {
            loginMessageLabel.setText("File does not exist");
        }

        String value = "Deneme veri";

        FileWriter fWriter = new FileWriter(file, true);
        BufferedWriter bWriter = new BufferedWriter(fWriter);
        bWriter.newLine();
        bWriter.write(value);

        bWriter.close();
*/
        if(usernameTextField.getText().isBlank() == false && passwordTextField.getText().isBlank() == false){
            validateLogin();
        }else{
            loginMessageLabel.setText("Please enter username and password");
        }
    }
    public void validateLogin(){}

    public void employeeView(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("EmployeeView.fxml"));
            Stage employeeViewStage = new Stage();
            employeeViewStage.initStyle(StageStyle.UNDECORATED);
            //primaryStage.setTitle("EknBank App");
            employeeViewStage.setScene(new Scene(root,520,400));
            employeeViewStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
