package sample.eknbankapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeCustomersController implements Initializable {
    public int loop = 0;
    @FXML
    private TableView<Customer> customerTableView;

    @FXML
    private TableColumn<Customer, String> customerFirsNameColumn;

    @FXML
    private TableColumn<Customer, String> customerLastNameColumn;

    @FXML
    private TableColumn<Customer, String> customerIdentityNumberColumn;

    @FXML
    private TableColumn<Customer, String> customerEmailColumn;

    @FXML
    private TableColumn<Customer, String> customerPasswordColumn;
    @FXML
    private ImageView logoImageView;
    public void initialize(URL location, ResourceBundle resources) {
        File logoImageFile = new File("Image/logoImage.jpg");
        Image logoImage = new Image(logoImageFile.toURI().toString());
        logoImageView.setImage(logoImage);

        customerFirsNameColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("firstName"));
        customerLastNameColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("lastName"));
        customerIdentityNumberColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("identityNumber"));
        customerEmailColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("mail"));
        customerPasswordColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("password"));
        try {
            listCustomer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String[][] getCustomers () throws IOException {
        File file = new File("TextFolders/Customers.txt");
        FileReader fReader = new FileReader(file);
        String line;
        loop = 0;
        //Dosyada gezinme komutu
        BufferedReader bReader = new BufferedReader(fReader);
        while ((line = bReader.readLine()) != null) {
            loop++;
        }

        String[][] customersInformation = new String[loop][8];
        loop = 0;
        int loopForValue =0;
        fReader = new FileReader(file);
        bReader = new BufferedReader(fReader);
        while ((line = bReader.readLine()) != null) {
            String[] employee = line.split(" ");
            for (String a : employee){
                customersInformation[loop][loopForValue] = a;
                loopForValue++;
            }
            loop++;
            loopForValue =0;
        }
        bReader.close();
        return customersInformation;
    }
    public void listCustomer() throws IOException{

        String[][] customersInformation = getCustomers();
        ObservableList<Customer> list = FXCollections.observableArrayList();
        boolean firstValue = false;
        for (String[] customerInfo : customersInformation) {
            if (firstValue != false){
                String firstName = customerInfo[0];
                String lastName = customerInfo[1];
                String identityNumber = customerInfo[2];
                String mail = customerInfo[3];
                String password = customerInfo[4];
                // Diğer bilgileri buraya ekleyin
                Customer customer = new Customer(firstName, lastName, identityNumber,mail,password); // Customer sınıfına uygun olacak şekilde oluşturun
                list.add(customer);
            }
            firstValue = true;
        }
        customerTableView.setItems(list);
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

    @FXML
    private Button financialSituationButton;
    public void financialSituationButtonOnAction(ActionEvent event) throws IOException {
        EmployeeController employeeController = new EmployeeController();
        employeeController.financialSituationView();
        Stage stage = (Stage) financialSituationButton.getScene().getWindow();
        stage.close();
    }
}
