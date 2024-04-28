package sample.eknbankapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    private Label employeeNameLabel;
    @FXML
    private TextField employeeFirstnameTextField;
    @FXML
    private TextField employeeLastnameTextField;
    @FXML
    private TextField employeeEmailTextField;
    @FXML
    private TextField employeeIdentityNumberTextField;
    @FXML
    private TextField employeePasswordTextField;
    public void setIdentityNumber(String identityNumber) {
        employeeIdentityNumberTextField.setText(identityNumber);
    }
    public void setPassword(String password) {
        employeePasswordTextField.setText(password);
    }
    public void setEmail(String eMail) {
        employeeEmailTextField.setText(eMail);
    }
    public void setFirstName(String firstName) {
        employeeFirstnameTextField.setText(firstName);
    }
    public void setLastName(String lastName) {
        employeeLastnameTextField.setText(lastName);
    }
    public void setFullName(String fullName) {
        employeeNameLabel.setText(fullName);
    }

    @FXML
    private ImageView logoImageView;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File logoImageFile = new File("Image/logoImage.jpg");
        Image logoImage = new Image(logoImageFile.toURI().toString());
        logoImageView.setImage(logoImage);
        employeeNameLabel.setText(employeeFirstnameTextField.getText()+" "+employeeLastnameTextField.getText());
    }
    @FXML
    private Button exitButton;

    public void exitButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }


   /* @FXML
    private Button employeeInformationRegisterButton;
    public void employeeInformationRegisterButtonOnAction(ActionEvent event) throws IOException {
        // Dosya adı
        String dosyaAdi = "TextFolders/Employees.txt";
        // Silinmesi gereken satırın indeksi
        int silinecekSatirIndex = 2;

        // Dosya işlemleri için gerekli değişkenler
        File girdiDosyasi = new File(dosyaAdi);
        File geciciDosya = new File("TextFolders/Employees.txt");

        try {
            // Girdi dosyasını okumak için BufferedReader oluşturun
            BufferedReader bufferedReader = new BufferedReader(new FileReader(girdiDosyasi));
            // Geçici dosyaya yazmak için BufferedWriter oluşturun
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(geciciDosya));

            String satir;
            int satirSayaci = 0;

            // Dosyayı satır satır okuyun
            while ((satir = bufferedReader.readLine()) != null) {
                satirSayaci++;
                // Silinmesi gereken satırı atlayın
                if (satirSayaci != silinecekSatirIndex) {
                    bufferedWriter.write(satir);
                    bufferedWriter.newLine();
                }
            }

            // Dosyaları kapatın
            bufferedReader.close();
            bufferedWriter.close();

            // Gecici dosyayı eski dosya adıyla yeniden adlandırın
            girdiDosyasi.delete();
            geciciDosya.renameTo(girdiDosyasi);

            System.out.println("Satır başarıyla silindi.");
        } catch (IOException e) {
            System.out.println("Dosya okuma veya yazma hatası: " + e.getMessage());
        }
    }
*/


}
