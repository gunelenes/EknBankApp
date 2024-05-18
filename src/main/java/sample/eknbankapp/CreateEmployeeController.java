package sample.eknbankapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateEmployeeController implements Initializable {
    @FXML
    private ImageView logoImageView;
    public void initialize(URL location, ResourceBundle resources) {
        File logoImageFile = new File("Image/logoImage.jpg");
        Image logoImage = new Image(logoImageFile.toURI().toString());
        logoImageView.setImage(logoImage);
    }

    @FXML
    private TextField employeeFirstNameTextField;
    @FXML
    private TextField employeeLastNameTextField;
    @FXML
    private TextField employeeIdentityNumberTextField;
    @FXML
    private TextField employeeEmailTextField;
    @FXML
    private TextField employeePasswordTextField;
    @FXML
    private Button employeeSignUpButton;
    @FXML
    private Label incorrectLabel;
    public void employeeSignUpButtonOnAction(ActionEvent event) throws IOException {
        if(employeeFirstNameTextField.getText().equals("")||employeeLastNameTextField.getText().equals("")||employeeIdentityNumberTextField.getText().equals("")
        ||employeeEmailTextField.getText().equals("")||employeePasswordTextField.getText().equals("")){
            incorrectLabel.setText("Incorrect Input !!!");
        }else{
            EmployeeSettingController employeeSettingController = new EmployeeSettingController();
            String[][] employeesInformation = employeeSettingController.getEmployee();
            String createValue = employeeFirstNameTextField.getText()+" "+employeeLastNameTextField.getText()+
                    " "+employeeIdentityNumberTextField.getText()+" "+employeeEmailTextField.getText()+
                    " "+employeePasswordTextField.getText();
            try {
                FileWriter writer = new FileWriter("TextFolders/Employees.txt");
                for (int y = 0; y < employeesInformation.length; y++) {
                    for (int j = 0; j < employeesInformation[y].length; j++) {
                        writer.write(employeesInformation[y][j] + " ");
                    }
                    writer.write("\n");
                }
                writer.write(createValue);
                writer.write("\n");
                writer.close();
            } catch (IOException e) {
                System.out.println("File write error CreateEmployeeController Employee.Txt: " + e.getMessage());
            }
            Stage stage = (Stage) employeeSignUpButton.getScene().getWindow();
            stage.close();
            AdminController adminController = new AdminController();
            adminController.employeeSettingView();
        }
    }

    @FXML
    private Button cryptoButton;
    @FXML
    public void cryptoButtonOnAction(ActionEvent event) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI("http://localhost:63342/EknBankApp/src/main/Selenium/CryptoBord.html?_ijt=rf3lt2bfk4modh3k5vss587pl"));
    }

    @FXML
    private Button employeeSettingButton;
    public void employeeSettingButtonOnAction(ActionEvent event) throws IOException {
        AdminController adminController = new AdminController();
        adminController.employeeSettingView();
        Stage stage = (Stage) employeeSettingButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Button customerSettingButton;
    public void customerSettingButtonOnAction(ActionEvent event) throws IOException {
        AdminController adminController = new AdminController();
        adminController.customerSettingView();
        Stage stage = (Stage) customerSettingButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Button financialSituationButton;
    public void financialSituationButtonOnAction(ActionEvent event) throws IOException {
        AdminController adminController = new AdminController();
        adminController.financialSituationView();
        Stage stage = (Stage) financialSituationButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Button exitButton;
    public void exitButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

}
