package sample.eknbankapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

public class CreateCustomerController implements Initializable {
    @FXML
    private ImageView logoImageView;
    @FXML
    private TextField customerFirstNameTextField;
    @FXML
    private TextField customerLastNameTextField;
    @FXML
    private TextField customerIdentityNumberTextField;
    @FXML
    private TextField customerEmailTextField;
    @FXML
    private TextField customerPasswordTextField;
    @FXML
    private TextField customerDolarTextField;
    @FXML
    private TextField customerEuroTextField;
    @FXML
    private TextField customerTurkishLiraTextField;
    @FXML
    private ComboBox<String> customerMentorComboBax;
    LoginController loginController = new LoginController();
    private final String[][] mentor = loginController.getEmployees();

    public CreateCustomerController() throws IOException {
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        File logoImageFile = new File("Image/logoImage.jpg");
        Image logoImage = new Image(logoImageFile.toURI().toString());
        logoImageView.setImage(logoImage);

        // cryptoValue dizisinin ilk elemanlarını comboBox'a ekle
        int loop =0;
        for (String[] crypto : mentor) {
            if(loop != 0){
                customerMentorComboBax.getItems().add(crypto[3]);
            }
            loop++;
        }
    }
    @FXML
    private Label incorrectInformationLabel;
    @FXML
    private Button customerSignUpButton;
    public void customerSignUpButtonOnAction(ActionEvent event) throws IOException {
        incorrectInformationLabel.setText("");
        if(customerFirstNameTextField.getText().equals("")||customerLastNameTextField.getText().equals("")
        ||customerIdentityNumberTextField.getText().equals("")||customerEmailTextField.getText().equals("")
        ||customerPasswordTextField.getText().equals("")||customerMentorComboBax.getValue()==null
        ||customerDolarTextField.getText().equals("")||customerEuroTextField.getText().equals("")
        ||customerTurkishLiraTextField.getText().equals("")){
            incorrectInformationLabel.setText("Incorrect Input!!!");
        }else{


        CustomerSettingController customerSettingController = new CustomerSettingController();
        customerSettingController.getCustomers();
        String[][] customersInformation = customerSettingController.getCustomers();
        String createValue = customerFirstNameTextField.getText()+" "+customerLastNameTextField.getText()+
                " "+customerIdentityNumberTextField.getText()+" "+customerEmailTextField.getText()+
                " "+customerPasswordTextField.getText()+" "+customerDolarTextField.getText()+
                ","+customerEuroTextField.getText()+","+customerTurkishLiraTextField.getText()+","+
                " "+"Solana,1,"+" "+customerMentorComboBax.getValue();
        try {
            FileWriter writer = new FileWriter("TextFolders/Customers.txt");
            for (int y = 0; y < customersInformation.length; y++) {
                for (int j = 0; j < customersInformation[y].length; j++) {
                    writer.write(customersInformation[y][j] + " ");
                }
                writer.write("\n");
            }
            writer.write(createValue);
            writer.write("\n");
            writer.close();
            //System.out.println("Veriler " + TextFolders/deneme.txt"+ " dosyasına yazıldı.");
        } catch (IOException e) {
            System.out.println("Dosya yazma hatası: " + e.getMessage());
        }
        Stage stage = (Stage) customerSignUpButton.getScene().getWindow();
        stage.close();
        AdminController adminController = new AdminController();
        adminController.customerSettingView();
    }}

    @FXML
    private Button cryptoButton;
    @FXML
    public void cryptoButtonOnAction(ActionEvent event) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI("http://localhost:63342/EknBankApp/src/main/Selenium/CryptoBord.html?_ijt=rf3lt2bfk4modh3k5vss587pl"));
    }
    @FXML
    private Button exitButton;

    public void exitButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) exitButton.getScene().getWindow();
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
}
