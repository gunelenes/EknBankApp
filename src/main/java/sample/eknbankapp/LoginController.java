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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.StageStyle;

import java.io.*;
import java.util.ResourceBundle;
import java.net.URL;
public class LoginController implements Initializable {

    //public String fullName;
    public int loop;
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


        if(usernameTextField.getText().isBlank() == false && passwordTextField.getText().isBlank() == false){
            getEmployees();
            validateLogin();
        }else{
            loginMessageLabel.setText("Please enter username and password");
        }





















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


    }


    public String fullName; // fullName değişkeni
    public String firstName;
    public String lastName;
    public String eMail;
    public String password;
    public String identityNumber;

    public void validateLogin() throws IOException{

        String[][] employeesInformation = getEmployees();
        loop = 0;
        for (int i = 0; i < employeesInformation.length; i++) {
            // Her bir çalışanın bilgilerini işleyin
            // Örneğin, çalışan bilgilerini yazdıralım:
            if(employeesInformation[loop][2].equals(usernameTextField.getText()) && employeesInformation[loop][3].equals(passwordTextField.getText())){
                fullName=employeesInformation[loop][0]+" "+" "+employeesInformation[loop][1];
                firstName = employeesInformation[loop][0];
                lastName = employeesInformation[loop][1];
                eMail = employeesInformation[loop][2];
                password = employeesInformation[loop][3];
                identityNumber = employeesInformation[loop][4];
                employeeView();
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
                break;
            }
            loop++;
        }

        String[][] adminInformation = getAdminInformation();
        loop = 0;
        for (int i = 0; i < adminInformation.length; i++) {
            // Her bir çalışanın bilgilerini işleyin
            // Örneğin, çalışan bilgilerini yazdıralım:
            if(adminInformation[loop][2].equals(usernameTextField.getText()) && adminInformation[loop][3].equals(passwordTextField.getText())){
                fullName=adminInformation[loop][0]+" "+" "+adminInformation[loop][1];
                firstName = adminInformation[loop][0];
                lastName = adminInformation[loop][1];
                eMail = adminInformation[loop][2];
                password = adminInformation[loop][3];
                identityNumber = adminInformation[loop][4];
                adminView();
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
                break;
            }
            loop++;
        }
        loginMessageLabel.setText("Please contol your information");
    }


    public void employeeView(){
        try{
            //Parent root = FXMLLoader.load(getClass().getResource("EmployeeView.fxml"));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeView.fxml"));
            Parent root = loader.load();
            EmployeeController controller = loader.getController();
            controller.setFullName(fullName);
            controller.setFirstName(firstName);
            controller.setLastName(lastName);
            controller.setIdentityNumber(identityNumber);
            controller.setPassword(password);
            controller.setEmail(eMail);


            Stage employeeViewStage = new Stage();
            employeeViewStage.initStyle(StageStyle.UNDECORATED);
            //primaryStage.setTitle("EknBank App");
            employeeViewStage.setScene(new Scene(root,800,500));
            employeeViewStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


    public void adminView(){
        try{
           // Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminView.fxml"));
            Parent root = loader.load();
            AdminController controller = loader.getController();
            controller.setFullName(fullName);
         /*   controller.setIdentityNumber(identityNumber);
            controller.setPassword(password);
            controller.serEmail(eMail);*/



            Stage adminViewStage = new Stage();
            adminViewStage.initStyle(StageStyle.UNDECORATED);
            //primaryStage.setTitle("EknBank App");
            adminViewStage.setScene(new Scene(root,800,500));
            adminViewStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public String[][] getEmployees () throws IOException {
        File file = new File("TextFolders/Employees.txt");
        FileReader fReader = new FileReader(file);
        String line;
        loop = 0;
        //Dosyada gezinme komutu
        BufferedReader bReader = new BufferedReader(fReader);
        while ((line = bReader.readLine()) != null) {
           loop++;
        }


        String[][] employeesInformation = new String[loop][5];
        loop = 0;
        int loopForValue =0;
        fReader = new FileReader(file);
        bReader = new BufferedReader(fReader);
        while ((line = bReader.readLine()) != null) {
            String[] employee = line.split(" ");
            for (String a : employee){
                employeesInformation[loop][loopForValue] = a;
                loopForValue++;
            }
            loop++;
            loopForValue =0;
        }
        bReader.close();
        return employeesInformation;
    }

    public String[][] getAdminInformation () throws IOException {
        File file = new File("TextFolders/AdminInformation.txt");
        FileReader fReader = new FileReader(file);
        String line;
        loop = 0;
        //Dosyada gezinme komutu
        BufferedReader bReader = new BufferedReader(fReader);
        while ((line = bReader.readLine()) != null) {
            loop++;
        }


        String[][] adminInformation = new String[loop][5];
        loop = 0;
        int loopForValue =0;
        fReader = new FileReader(file);
        bReader = new BufferedReader(fReader);
        while ((line = bReader.readLine()) != null) {
            String[] employee = line.split(" ");
            for (String a : employee){
                adminInformation[loop][loopForValue] = a;
                loopForValue++;
            }
            loop++;
            loopForValue =0;
        }
        bReader.close();
        return adminInformation;
    }


    public int loop() {
        return loop;
    }
}
