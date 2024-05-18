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

public class SettingForEmployeeController implements Initializable {
    @FXML
    private ImageView logoImageView;
    @FXML
    private Button employeeInformationRegisterButton;
    public void initialize(URL location, ResourceBundle resources) {
        File logoImageFile = new File("Image/logoImage.jpg");
        Image logoImage = new Image(logoImageFile.toURI().toString());
        logoImageView.setImage(logoImage);
        employeeInformationRegisterButton.setDisable(true);
    }

    @FXML
    private Button searchEmployeeWithIdentityNumber;
    @FXML
    private TextField searchTextFieldWithIdentityNumber;
    @FXML
    private TextField employeeFirstNameTextField;
    @FXML
    private TextField employeeLastNameTextField;
    @FXML
    private TextField employeeEmailTextField;
    @FXML
    private TextField employeePasswordTextField;
    @FXML
    private Label notFoundLabel;
    @FXML
    private Label correctLabel;
    @FXML
    private Label incorrectInformationLabel;
    public void setPassword(String password) {
        employeePasswordTextField.setText(password);
    }
    public void setEmail(String eMail) {
        employeeEmailTextField.setText(eMail);
    }
    public void setFirstName(String firstName) {
        employeeFirstNameTextField.setText(firstName);
    }
    public void setLastName(String lastName) {
        employeeLastNameTextField.setText(lastName);
    }

    public void searchEmployeeOnAction(ActionEvent event) throws IOException {
        incorrectInformationLabel.setText("");
        correctLabel.setText("");
        notFoundLabel.setText("");
        EmployeeSettingController employeeSettingController = new EmployeeSettingController();
        String[][] employeesInformation = employeeSettingController.getEmployee();
        Boolean flag = false;
        for (int i = 1; i < employeesInformation.length; i++) {
            if (employeesInformation[i][2].equals(searchTextFieldWithIdentityNumber.getText())) {
                flag = true;
                setFirstName(employeesInformation[i][0]);
                setLastName(employeesInformation[i][1]);
                setEmail(employeesInformation[i][3]);
                setPassword(employeesInformation[i][4]);
                correctLabel.setText("Correct");
                employeeInformationRegisterButton.setDisable(false);
            }
        }
        if (flag == false) {
            notFoundLabel.setText("Not Found!!!");
            employeeFirstNameTextField.setText("");
            employeeLastNameTextField.setText("");
            employeeEmailTextField.setText("");
            employeePasswordTextField.setText("");
            employeeInformationRegisterButton.setDisable(true);
        }
    }

    public void updateEmployeeInformationOnAction(ActionEvent event) throws IOException {
        incorrectInformationLabel.setText("");
        correctLabel.setText("");
        EmployeeSettingController employeeSettingController = new EmployeeSettingController();
        String[][] employeesInformation = employeeSettingController.getEmployee();
        for(int i = 1; i < employeesInformation.length; i++){
            if(employeesInformation[i][2].equals(searchTextFieldWithIdentityNumber.getText())){
                employeesInformation[i][0] = employeeFirstNameTextField.getText();
                employeesInformation[i][1] = employeeLastNameTextField.getText();
                employeesInformation[i][3] = employeeEmailTextField.getText();
                employeesInformation[i][4] = employeePasswordTextField.getText();
            }
        }
        if(employeeFirstNameTextField.getText().equals("") || employeeLastNameTextField.getText().equals("") || employeeEmailTextField.getText().equals("") || employeePasswordTextField.getText().equals("")){
            incorrectInformationLabel.setText("Incorrect Input!!!");
        }else{
            try {
                FileWriter writer = new FileWriter("TextFolders/Employees.txt");
                for (int y = 0; y < employeesInformation.length; y++) {
                    for (int j = 0; j < employeesInformation[y].length; j++) {
                        writer.write(employeesInformation[y][j] + " ");
                    }
                    writer.write("\n");
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("File write error SettingForEmployeeController Employee.txt: " + e.getMessage());
            }
            correctLabel.setText("Correct!!!");
        }
        employeeFirstNameTextField.setText("");
        employeeLastNameTextField.setText("");
        employeeEmailTextField.setText("");
        employeePasswordTextField.setText("");
        employeeInformationRegisterButton.setDisable(true);
    }

    @FXML
    private Button exitButton;
    public void exitButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) exitButton.getScene().getWindow();
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
    private Button employeeSettingButton;
    public void employeeSettingButtonOnAction(ActionEvent event) throws IOException {
        AdminController adminController = new AdminController();
        adminController.employeeSettingView();
        Stage stage = (Stage) employeeSettingButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Button cryptoButton;
    @FXML
    public void cryptoButtonOnAction(ActionEvent event) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI("http://localhost:63342/EknBankApp/src/main/Selenium/CryptoBord.html?_ijt=rf3lt2bfk4modh3k5vss587pl"));
    }

    @FXML
    private Button financialSituationButton;
    public void financialSituationButtonOnAction(ActionEvent event) throws IOException {
        AdminController adminController = new AdminController();
        adminController.financialSituationView();
        Stage stage = (Stage) financialSituationButton.getScene().getWindow();
        stage.close();
    }

}
