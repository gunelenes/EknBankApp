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

    public int loop;
    @FXML
    private Button cancelButton;

    public void cancelButtonOnAction(ActionEvent event) throws IOException {

      listForEmployee();







       /* Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();*/






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
    private ImageView loginImageView;
    @FXML
    private ImageView lockImageView;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File loginImageFile = new File("Image/eknBankLoginImage.jpg");
        Image loginImage = new Image(loginImageFile.toURI().toString());
        loginImageView.setImage(loginImage);

        File lockImageFile = new File("Image/lockLoginImage.jpg");
        Image lockImage = new Image(lockImageFile.toURI().toString());
        lockImageView.setImage(lockImage);
    }

    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    public void loginButtonOnAction(ActionEvent event) throws IOException {

        //employeeView();

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();





















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

        /*
        if(usernameTextField.getText().isBlank() == false && passwordTextField.getText().isBlank() == false){
            validateLogin();
        }else{
            loginMessageLabel.setText("Please enter username and password");
        }*/
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

    public void listForEmployee () throws IOException {
        File file = new File("TextFolders/Employees.txt");
       /* if (!file.exists()) {
            loginMessageLabel.setText("File does not exist");
        }*/

        FileReader fReader = new FileReader(file);
        String line;
        loop = 0;
        //Dosyada gezinme komutu
        BufferedReader bReader = new BufferedReader(fReader);
        while ((line = bReader.readLine()) != null) {
           loop++;
        }
        bReader.close();


    }
}
