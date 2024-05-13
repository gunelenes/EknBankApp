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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerSettingController implements Initializable {

    public int loop = 0;


    @FXML
    private Button exitButton;

    public void exitButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }


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



    // new Customer(customersInformation[0][1],customersInformation[0][2],customersInformation[0][3],customersInformation[0][4],customersInformation[0][5]),
              /*  new Customer("gulne","asfa","dfgh","ee@ee","123"),
                new Customer("GULBEN","SARICA","1234","ee@ee","123")*/

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


    public void settingForCustomerView(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SettingForCustomerView.fxml"));
            Parent root = loader.load();
            Stage settingForCustomerViewStage = new Stage();
            settingForCustomerViewStage.initStyle(StageStyle.UNDECORATED);
            settingForCustomerViewStage.setScene(new Scene(root,800,500));
            settingForCustomerViewStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    @FXML
    private Button settingForCustomerButton;
    public void settingForCustomerButtonOnAction(ActionEvent event) throws IOException {
        settingForCustomerView();
        Stage stage = (Stage) settingForCustomerButton.getScene().getWindow();
        stage.close();
    }

    public void signUpCustomerView(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateCustomerView.fxml"));
            Parent root = loader.load();
            Stage settingForCustomerViewStage = new Stage();
            settingForCustomerViewStage.initStyle(StageStyle.UNDECORATED);
            settingForCustomerViewStage.setScene(new Scene(root,800,500));
            settingForCustomerViewStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    @FXML
    private Button signUpCustomerButton;
    public void signUpCustomerButtonOnAction(ActionEvent event) throws IOException {
        signUpCustomerView();
        Stage stage = (Stage) signUpCustomerButton.getScene().getWindow();
        stage.close();
    }


}
