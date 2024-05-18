package sample.eknbankapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeSettingController implements Initializable {

    public int loop = 0;
    @FXML
    private TableView<Employee> employeeTableView;

    @FXML
    private TableColumn<Employee, String> employeeFirsNameColumn;

    @FXML
    private TableColumn<Employee, String> employeeLastNameColumn;

    @FXML
    private TableColumn<Employee, String> employeeIdentityNumberColumn;

    @FXML
    private TableColumn<Employee, String> employeeEmailColumn;

    @FXML
    private TableColumn<Employee, String> employeePasswordColumn;

    public String[][] getEmployee () throws IOException {
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
    public void listEmployee() throws IOException{
        String[][] employeesInformation = getEmployee();
        ObservableList<Employee> list = FXCollections.observableArrayList();
        boolean firstValue = false;
        for (String[] employeeInfo : employeesInformation) {
            if (firstValue != false){
                String firstName = employeeInfo[0];
                String lastName = employeeInfo[1];
                String identityNumber = employeeInfo[2];
                String mail = employeeInfo[3];
                String password = employeeInfo[4];
                // Diğer bilgileri buraya ekleyin
                Employee employee = new Employee(firstName, lastName, identityNumber,mail,password); // Customer sınıfına uygun olacak şekilde oluşturun
                list.add(employee);
            }
            firstValue = true;
        }
        employeeTableView.setItems(list);
    }

    @FXML
    private ImageView logoImageView;
    public void initialize(URL location, ResourceBundle resources) {
        File logoImageFile = new File("Image/logoImage.jpg");
        Image logoImage = new Image(logoImageFile.toURI().toString());
        logoImageView.setImage(logoImage);
        employeeFirsNameColumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("firstName"));
        employeeLastNameColumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("lastName"));
        employeeIdentityNumberColumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("identityNumber"));
        employeeEmailColumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("mail"));
        employeePasswordColumn.setCellValueFactory(new PropertyValueFactory<Employee,String>("password"));
        try {
            listEmployee();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void settingForEmployeeView(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SettingForEmployeeView.fxml"));
            Parent root = loader.load();
            Stage settingForEmployeeViewStage = new Stage();
            settingForEmployeeViewStage.initStyle(StageStyle.UNDECORATED);
            settingForEmployeeViewStage.setScene(new Scene(root,800,500));
            settingForEmployeeViewStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    private Button settingForEmployeeButton;
    public void settingForEmployeeButtonOnAction(ActionEvent event) throws IOException {
        settingForEmployeeView();
        Stage stage = (Stage) settingForEmployeeButton.getScene().getWindow();
        stage.close();
    }


    public void createEmployeeView(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateEmployeeView.fxml"));
            Parent root = loader.load();
            Stage createEmployeeViewStage = new Stage();
            createEmployeeViewStage.initStyle(StageStyle.UNDECORATED);
            createEmployeeViewStage.setScene(new Scene(root,800,500));
            createEmployeeViewStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    private Button signUpEmployeeButton;
    public void createEmployeeButtonOnAction(ActionEvent event) throws IOException {
        createEmployeeView();
        Stage stage = (Stage) signUpEmployeeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Button cryptoButton;
    @FXML
    public void cryptoButtonOnAction(ActionEvent event) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI("http://localhost:63342/EknBankApp/src/main/Selenium/CryptoBord.html?_ijt=rf3lt2bfk4modh3k5vss587pl"));
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
