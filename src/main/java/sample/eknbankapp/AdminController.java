package sample.eknbankapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
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


    public int loop = 0;
    @FXML
    private TextField adminFirstnameTextField;
    @FXML
    private TextField adminLastnameTextField;
    @FXML
    private TextField adminEmailTextField;
    @FXML
    private TextField adminIdentityNumberTextField;
    @FXML
    private TextField adminPasswordTextField;

    public void setIdentityNumber(String identityNumber) {
        adminIdentityNumberTextField.setText(identityNumber);
    }
    public void setPassword(String password) {
        adminPasswordTextField.setText(password);
    }
    public void setEmail(String eMail) {
        adminEmailTextField.setText(eMail);
    }
    public void setFirstName(String firstName) {
        adminFirstnameTextField.setText(firstName);
    }
    public void setLastName(String lastName) {
        adminLastnameTextField.setText(lastName);
    }
    private String firstName;
    private String lastName;
    private String eMail;
    private String password;
    private String identityNumber;

    @FXML
    private Label registerMessageLabel;
    @FXML
    private Label errorMessageLabel;

    public void adminInformationRegisterButtonOnAction(ActionEvent event) throws IOException {
        registerMessageLabel.setText("");
        errorMessageLabel.setText("");
        if(adminFirstnameTextField.getText().equals("")||adminLastnameTextField.getText().equals("")
                ||adminEmailTextField.getText().equals("")
                ||adminIdentityNumberTextField.getText().equals("")
                ||adminPasswordTextField.getText().equals("")){
            errorMessageLabel.setText("Incorrect Input !!!");
        }else {
        //login controller'a bağlanıp employeeların hepsini employeesInformation arrayine atar
        LoginController loginController = new LoginController();
        String[][] adminInformation=loginController.getAdminInformation();
        loop = 0;
        //arraydeki tüm verileri işler ve doğru kişiyi bulup bilgilerini günceller
        for (int i = 0; i < adminInformation.length; i++) {
            // Her bir çalışanın bilgilerini işleyin
            // Örneğin, çalışan bilgilerini yazdıralım:
            if(adminInformation[loop][3].equals(adminIdentityNumberTextField.getText())){
                firstName = adminFirstnameTextField.getText();
                lastName = adminLastnameTextField.getText();
                password = adminPasswordTextField.getText();
                eMail = adminEmailTextField.getText();
                adminInformation[loop][0]=firstName;
                adminInformation[loop][1]=lastName;
                adminInformation[loop][2]=eMail;
                adminInformation[loop][4]=password;
                adminView();
            }
            loop++;
        }
        try {
            FileWriter writer = new FileWriter("TextFolders/AdminInformation.txt");
            for (int y = 0; y < adminInformation.length; y++) {
                for (int j = 0; j < adminInformation[y].length; j++) {
                    writer.write(adminInformation[y][j] + " ");
                }
                writer.write("\n");
            }
            writer.close();
            //System.out.println("Veriler " + TextFolders/deneme.txt"+ " dosyasına yazıldı.");
        } catch (IOException e) {
            System.out.println("File Write Error AdminController Method: adminInformationRegisterButtonOnAction: " + e.getMessage());
        } }
    }

    public void adminView(){
        registerMessageLabel.setText("Correct register");
        errorMessageLabel.setText("");
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminView.fxml"));
            Parent root = loader.load();
            AdminController controller = loader.getController();
            controller.setFirstName(firstName);
            controller.setLastName(lastName);
            controller.setIdentityNumber(identityNumber);
            controller.setPassword(password);
            controller.setEmail(eMail);
            Stage adminViewStage = new Stage();
            adminViewStage.initStyle(StageStyle.UNDECORATED);
            adminViewStage.setScene(new Scene(root,800,500));
            adminViewStage.show();
            adminViewStage.close();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


    public void financialSituationView(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FinancialSituationView.fxml"));
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

    @FXML
    private Button financialSituationButton;
    public void financialSituationButtonOnAction(ActionEvent event) throws IOException {
        financialSituationView();
        Stage stage = (Stage) financialSituationButton.getScene().getWindow();
        stage.close();
    }
    public void employeeSettingView(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeSettingView.fxml"));
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

    @FXML
    private Button employeeSettingButton;
    public void employeeSettingButtonOnAction(ActionEvent event) throws IOException {
        employeeSettingView();
        Stage stage = (Stage) employeeSettingButton.getScene().getWindow();
        stage.close();
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

    @FXML
    private Button customerSettingButton;
    public void customerSettingButtonOnAction(ActionEvent event) throws IOException {
        customerSettingView();
        Stage stage = (Stage) customerSettingButton.getScene().getWindow();
        stage.close();
    }

}
