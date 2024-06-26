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

public class EmployeeController implements Initializable {

    public int loop = 0;
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

    @FXML
    private ImageView logoImageView;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File logoImageFile = new File("Image/logoImage.jpg");
        Image logoImage = new Image(logoImageFile.toURI().toString());
        logoImageView.setImage(logoImage);
    }
    @FXML
    private Button exitButton;

    public void exitButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
//kaç çalışanımızın olduğunu gösterir
    public int totalEmployee() throws IOException {
        File file = new File("TextFolders/Employees.txt");
        FileReader fReader = new FileReader(file);
        String line;
        loop = 0;
        //Dosyada gezinme komutu
        BufferedReader bReader = new BufferedReader(fReader);
        while ((line = bReader.readLine()) != null) {
            loop++;
        }
        return loop;
    }
    @FXML
    private Label registerMessageLabel;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private Button employeeInformationRegisterButton;
    public void employeeInformationRegisterButtonOnAction(ActionEvent event) throws IOException {
        registerMessageLabel.setText("");
        errorMessageLabel.setText("");
        if(employeeFirstnameTextField.getText().equals("")||employeeLastnameTextField.getText().equals("")
                ||employeeEmailTextField.getText().equals("")
                ||employeeIdentityNumberTextField.getText().equals("")
                ||employeePasswordTextField.getText().equals("")){
            errorMessageLabel.setText("Incorrect Input !!!");
        }else {
        //login controller'a bağlanıp employeeların hepsini employeesInformation arrayine atar
        LoginController loginController = new LoginController();
        String[][] employeesInformation=loginController.getEmployees();

        loop = 0;
        //arraydeki tüm verileri işler ve doğru kişiyi bulup bilgilerini günceller
        for (int i = 0; i < employeesInformation.length; i++) {
            // Her bir çalışanın bilgilerini işleyin
            // Örneğin, çalışan bilgilerini yazdıralım:
            if(employeesInformation[loop][2].equals(employeeIdentityNumberTextField.getText())){
                 firstName = employeeFirstnameTextField.getText();
                 lastName = employeeLastnameTextField.getText();
                 password = employeePasswordTextField.getText();
                 eMail = employeeEmailTextField.getText();
                employeesInformation[loop][0]=firstName;
                employeesInformation[loop][1]=lastName;
                employeesInformation[loop][3]=eMail;
                employeesInformation[loop][4]=password;
                registerMessageLabel.setText("Correct register");
                employeeView();
            }
            loop++;
        } try {
                FileWriter writer = new FileWriter("TextFolders/Employees.txt");
                for (int y = 0; y < employeesInformation.length; y++) {
                    for (int j = 0; j < employeesInformation[y].length; j++) {
                        writer.write(employeesInformation[y][j] + " ");
                    }
                    writer.write("\n");
                }
                writer.close();
                //System.out.println("Veriler " + TextFolders/deneme.txt"+ " dosyasına yazıldı.");
            } catch (IOException e) {
                System.out.println("File Write Error EmployeeController Method: employeeInformationRegisterButtonOnAction: " + e.getMessage());
            }
        }}


    // TÜM bilgileri ile o an aktif olan kullanıcının verilerini ekrana yazdırır
    public String firstName;
    public String lastName;
    public String eMail;
    public String password;
    public String identityNumber;
    public void employeeView(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeView.fxml"));
            Parent root = loader.load();
            EmployeeController controller = loader.getController();
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
            employeeViewStage.close();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    private Button cryptoButton;
    @FXML
    public void cryptoButtonOnAction(ActionEvent event) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI("http://localhost:63342/EknBankApp/src/main/Selenium/CryptoBord.html?_ijt=rf3lt2bfk4modh3k5vss587pl"));
    }



    public void financialSituationView(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeFinancialSituationView.fxml"));
            Parent root = loader.load();
            Stage financialSituationView = new Stage();
            financialSituationView.initStyle(StageStyle.UNDECORATED);
            financialSituationView.setScene(new Scene(root,800,500));
            financialSituationView.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    private Button employeeViewFinancialSituationButton;
    public void financialSituationButtonOnAction(ActionEvent event) throws IOException {
        financialSituationView();
        Stage stage = (Stage) employeeViewFinancialSituationButton.getScene().getWindow();
        stage.close();
    }



    public void employeeCustomersView(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeCustomersView.fxml"));
            Parent root = loader.load();
            Stage employeeCustomersView = new Stage();
            employeeCustomersView.initStyle(StageStyle.UNDECORATED);
            employeeCustomersView.setScene(new Scene(root,800,500));
            employeeCustomersView.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    private Button employeeCustomersButton;
    public void employeeCustomersViewButtonOnAction(ActionEvent event) throws IOException {
        employeeCustomersView();
        Stage stage = (Stage) employeeCustomersButton.getScene().getWindow();
        stage.close();
    }
}
