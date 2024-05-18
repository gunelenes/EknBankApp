package sample.eknbankapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingForCustomerController implements Initializable {
    public int loop = 0;
    @FXML
    private ImageView logoImageView;

    @FXML
    private ComboBox<String> comboBox;
    private final String[][] cryptoValue = getCrypto();

    @FXML
    private TableColumn<Crypto, String> cryptoNameColumn;

    @FXML
    private TableColumn<Crypto, String> cryptoValueColumn;

    @FXML
    private  Button updateCustomerInformation;
    @FXML
    private  Button addNewCrypto;
    public SettingForCustomerController() throws IOException {
    }

    public void initialize(URL location, ResourceBundle resources) {
        File logoImageFile = new File("Image/logoImage.jpg");
        Image logoImage = new Image(logoImageFile.toURI().toString());
        logoImageView.setImage(logoImage);


        // cryptoValue dizisinin ilk elemanlarını comboBox'a ekle
        for (String[] crypto : cryptoValue) {
                comboBox.getItems().add(crypto[0]);
        }

        cryptoNameColumn.setCellValueFactory(new PropertyValueFactory<Crypto,String>("cryptoName"));
        cryptoValueColumn.setCellValueFactory(new PropertyValueFactory<Crypto,String>("cryptoValue"));

        updateCustomerInformation.setDisable(true);
        addNewCrypto.setDisable(true);
    }


    public String[][] getCrypto () throws IOException {
        File file = new File("TextFolders/Crypto.txt");
        FileReader fReader = new FileReader(file);
        String line;
        loop = 0;
        //Dosyada gezinme komutu
        BufferedReader bReader = new BufferedReader(fReader);
        while ((line = bReader.readLine()) != null) {
            loop++;
        }

        String[][] cryptoInformation = new String[loop][2];
        loop = 0;
        int loopForValue =0;
        fReader = new FileReader(file);
        bReader = new BufferedReader(fReader);
        while ((line = bReader.readLine()) != null) {
            String[] employee = line.split(" ");
            for (String a : employee){
                cryptoInformation[loop][loopForValue] = a;
                loopForValue++;
            }
            loop++;
            loopForValue =0;
        }
        bReader.close();
        return cryptoInformation;
    }

    @FXML
    private Button searchCustomerWithIdentityNumber;
    @FXML
    private TextField  searchTextFieldWithIdentityNumber;
    @FXML
    private TextField customerFirstNameTextField;
    @FXML
    private TextField customerLastNameTextField;
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
    private TextField cryptoAmountTextField;
    @FXML
    private ListView customerCryptoListView;
    public void setPassword(String password) {
        customerPasswordTextField.setText(password);
    }
    public void setEmail(String eMail) {
        customerEmailTextField.setText(eMail);
    }
    public void setFirstName(String firstName) {
        customerFirstNameTextField.setText(firstName);
    }
    public void setLastName(String lastName) {
        customerLastNameTextField.setText(lastName);
    }


    @FXML
    private Label notFoundLabel;
    @FXML
    private TableView<Crypto> cryptoTableView;

    public void searchCustomerOnAction(ActionEvent event) throws IOException {
        incorrectInformationLabel.setText("");
        correctLabel.setText("");
        notFoundLabel.setText("");
        selectCryptoLabel.setText("");
        cryptoAmountLabel.setText("");
        CustomerSettingController customerSettingController = new CustomerSettingController();
        String[][] customersInformation = customerSettingController.getCustomers();
        Boolean flag = false;
        for (int i = 1; i < customersInformation.length; i++) {
            if (customersInformation[i][2].equals(searchTextFieldWithIdentityNumber.getText())) {
                flag = true;
                setFirstName(customersInformation[i][0]);
                setLastName(customersInformation[i][1]);
                setEmail(customersInformation[i][3]);
                setPassword(customersInformation[i][4]);
                String[] customer = customersInformation[i][5].split(",");
                customerDolarTextField.setText(customer[0]);
                customerEuroTextField.setText(customer[1]);
                customerTurkishLiraTextField.setText(customer[2]);
                ObservableList<Crypto> listCrypto = FXCollections.observableArrayList();
                String[] cryptoList = customersInformation[i][6].split(",");
                for (i = 0; i < cryptoList.length; i++) {
                    String cryptoName = cryptoList[i];
                    i++;
                    String cryptoValue = cryptoList[i];
                    Crypto crypto = new Crypto(cryptoName, cryptoValue); //
                    listCrypto.add(crypto);
                }
                cryptoTableView.setItems(listCrypto);
                updateCustomerInformation.setDisable(false);
                addNewCrypto.setDisable(false);
                break;
            }
        }
        if (flag == false) {
            notFoundLabel.setText("Not Found!!!");
            customerFirstNameTextField.setText("");
            customerLastNameTextField.setText("");
            customerEmailTextField.setText("");
            customerPasswordTextField.setText("");
            customerDolarTextField.setText("");
            customerEuroTextField.setText("");
            customerTurkishLiraTextField.setText("");
            cryptoAmountTextField.setText("");
            updateCustomerInformation.setDisable(true);
            addNewCrypto.setDisable(true);
        }
    }
    @FXML
    private Label incorrectInformationLabel;
    @FXML
    private Label correctLabel;
    public void updateCustomerInformationOnAction(ActionEvent event) throws IOException {
        incorrectInformationLabel.setText("");
        correctLabel.setText("");
        CustomerSettingController customerSettingController = new CustomerSettingController();
        String[][] customersInformation = customerSettingController.getCustomers();
        for(int i = 1; i < customersInformation.length; i++){
            if(customersInformation[i][2].equals(searchTextFieldWithIdentityNumber.getText())){
                customersInformation[i][0] =customerFirstNameTextField.getText();
                customersInformation[i][1] =customerLastNameTextField.getText();
                customersInformation[i][3] = customerEmailTextField.getText();
                customersInformation[i][4] = customerPasswordTextField.getText();
                String totalMonay = customerDolarTextField.getText()+","+customerEuroTextField.getText()+","+customerTurkishLiraTextField.getText();
                customersInformation[i][5] = totalMonay;
            }
        }
        if(customerFirstNameTextField.getText().equals("") || customerLastNameTextField.getText().equals("") || customerEmailTextField.getText().equals("") || customerPasswordTextField.getText().equals("")
                || customerDolarTextField.getText().equals("") || customerEuroTextField.getText().equals("") || customerTurkishLiraTextField.getText().equals("")){
            incorrectInformationLabel.setText("Incorrect Input!!!");
        }else{
            try {
                FileWriter writer = new FileWriter("TextFolders/Customers.txt");
                for (int y = 0; y < customersInformation.length; y++) {
                    for (int j = 0; j < customersInformation[y].length; j++) {
                        writer.write(customersInformation[y][j] + " ");
                    }
                    writer.write("\n");
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("File Write Error SettingForCustomerController   Method:updateCustomerInformationOnAction: " + e.getMessage());
            }
            correctLabel.setText("Correct!!!");
        }
        customerFirstNameTextField.setText("");
        customerLastNameTextField.setText("");
        customerEmailTextField.setText("");
        customerPasswordTextField.setText("");
        customerDolarTextField.setText("");
        customerEuroTextField.setText("");
        customerTurkishLiraTextField.setText("");
        cryptoAmountTextField.setText("");
        updateCustomerInformation.setDisable(true);
        addNewCrypto.setDisable(true);
    }
    @FXML
    private Label selectCryptoLabel;
    @FXML
    private Label cryptoAmountLabel;
    public void updateCryptoOnAction(ActionEvent event) throws IOException {
        selectCryptoLabel.setText("");
        cryptoAmountLabel.setText("");
        if(comboBox.getValue()==null) {
            selectCryptoLabel.setText("Choose Crypto Money!!!");
        }else if (cryptoAmountTextField.getText().equals("")) {
            cryptoAmountLabel.setText("Incorrect Input!!!");
        } else {
        CustomerSettingController customerSettingController = new CustomerSettingController();
        String[][] customersInformation = customerSettingController.getCustomers();

        for (int i = 1; i < customersInformation.length; i++) {
            if (customersInformation[i][2].equals(searchTextFieldWithIdentityNumber.getText())) {
                int trueCustomer = i;
                ObservableList<Crypto> listCrypto = FXCollections.observableArrayList();
                String[] cryptoList = customersInformation[i][6].split(",");
                boolean flag = true;
                for (i = 0; i < cryptoList.length; i++) {
                    if (cryptoList[i].equals(comboBox.getValue())) {
                        flag = false;
                        String cryptoName = cryptoList[i];
                        i++;
                        Integer cryptoValue = Integer.parseInt(cryptoList[i]) + Integer.parseInt(cryptoAmountTextField.getText());
                        Crypto crypto = new Crypto(cryptoName, cryptoValue.toString());
                        listCrypto.add(crypto);
                    } else {
                        String cryptoName = cryptoList[i];
                        i++;
                        String cryptoValue = cryptoList[i];
                        Crypto crypto = new Crypto(cryptoName, cryptoValue);
                        listCrypto.add(crypto);
                    }
                }
                if (flag == true) {
                    String cryptoName = comboBox.getValue();
                    String cryptoValue = cryptoAmountTextField.getText();
                    Crypto crypto = new Crypto(cryptoName, cryptoValue); //
                    listCrypto.add(crypto);
                }
                cryptoTableView.setItems(listCrypto);
                String crypto = "";
                for (int z = 0; z < listCrypto.size(); z++) {
                    crypto = crypto + (listCrypto.get(z).cryptoName + "," + listCrypto.get(z).cryptoValue + ",");
                }
                customersInformation[trueCustomer][6] = crypto;
                //UPDATE KISMI

                try {
                    FileWriter writer = new FileWriter("TextFolders/Customers.txt");
                    for (int y = 0; y < customersInformation.length; y++) {
                        for (int j = 0; j < customersInformation[y].length; j++) {
                            writer.write(customersInformation[y][j] + " ");
                        }
                        writer.write("\n");
                    }
                    writer.close();
                } catch (IOException e) {
                    System.out.println("File write error SettingForCustomerController Customers.txt: " + e.getMessage());
                }
            }
        }
    }
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
    private Button financialSituationButton;
    public void financialSituationButtonOnAction(ActionEvent event) throws IOException {
        AdminController adminController = new AdminController();
        adminController.financialSituationView();
        Stage stage = (Stage) financialSituationButton.getScene().getWindow();
        stage.close();
    }

}
