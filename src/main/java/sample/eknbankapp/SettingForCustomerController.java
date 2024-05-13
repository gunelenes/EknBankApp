package sample.eknbankapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.ComboBox;

import java.io.*;
import java.net.URL;
import java.security.PublicKey;
import java.util.Arrays;
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


        boolean flagFirstItem = true;
        // cryptoValue dizisinin ilk elemanlarını comboBox'a ekle
        for (String[] crypto : cryptoValue) {
            if(flagFirstItem!=true) {
                comboBox.getItems().add(crypto[0]);
                //comboBox.setItems(FXCollections.observableArrayList(crypto[0]));
                }
           flagFirstItem = false;
        }

        cryptoNameColumn.setCellValueFactory(new PropertyValueFactory<Crypto,String>("cryptoName"));
        cryptoValueColumn.setCellValueFactory(new PropertyValueFactory<Crypto,String>("cryptoValue"));

        updateCustomerInformation.setDisable(true);
        addNewCrypto.setDisable(true);
    }
    @FXML
    private Button exitButton;

    public void exitButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
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

    public void customerSettingButtonOnAction(ActionEvent event) throws IOException {
        customerSettingView();
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
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

        CustomerSettingController customerSettingController = new CustomerSettingController();
        String[][] customersInformation = customerSettingController.getCustomers();
        Boolean flag =false;
        for(int i = 1; i < customersInformation.length; i++){
            if(customersInformation[i][2].equals(searchTextFieldWithIdentityNumber.getText())){
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
                for (i=0;i<cryptoList.length;i++){
                    String cryptoName = cryptoList[i];
                    i++;
                    String cryptoValue = cryptoList[i];
                    Crypto crypto = new Crypto(cryptoName, cryptoValue); //
                    listCrypto.add(crypto);
                }
                cryptoTableView.setItems(listCrypto);
                updateCustomerInformation.setDisable(false);
                addNewCrypto.setDisable(false);
            }
        }
        if(flag==false){
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
    private Label correctLabel;
    public void updateCustomerInformationOnAction(ActionEvent event) throws IOException {
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
        try {
            FileWriter writer = new FileWriter("TextFolders/Customers.txt");
            for (int y = 0; y < customersInformation.length; y++) {
                for (int j = 0; j < customersInformation[y].length; j++) {
                    writer.write(customersInformation[y][j] + " ");
                }
                writer.write("\n");
            }
            writer.close();
            //System.out.println("Veriler " + TextFolders/deneme.txt"+ " dosyasına yazıldı.");
        } catch (IOException e) {
            System.out.println("Dosya yazma hatası: " + e.getMessage());
        }


        correctLabel.setText("Correct!!!");
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

    public void updateCryptoOnAction(ActionEvent event) throws IOException {
        CustomerSettingController customerSettingController = new CustomerSettingController();
        String[][] customersInformation = customerSettingController.getCustomers();

        for(int i = 1; i < customersInformation.length; i++){
            if(customersInformation[i][2].equals(searchTextFieldWithIdentityNumber.getText())){
                int trueCustomer = i;
                ObservableList<Crypto> listCrypto = FXCollections.observableArrayList();
                String[] cryptoList = customersInformation[i][6].split(",");
                boolean flag = true;
                for (i=0;i<cryptoList.length;i++){
                    if(cryptoList[i].equals(comboBox.getValue())){
                        flag =false;
                        String cryptoName = cryptoList[i];
                        i++;
                        Integer cryptoValue = Integer.parseInt(cryptoList[i])+Integer.parseInt(cryptoAmountTextField.getText());
                        Crypto crypto = new Crypto(cryptoName, cryptoValue.toString());
                        listCrypto.add(crypto);
                    }else{
                        String cryptoName = cryptoList[i];
                        i++;
                        String cryptoValue = cryptoList[i];
                        Crypto crypto = new Crypto(cryptoName, cryptoValue);
                        listCrypto.add(crypto);
                    }
                }
                if(flag == true){
                    String cryptoName=comboBox.getValue();
                    String cryptoValue = cryptoAmountTextField.getText();
                    Crypto crypto = new Crypto(cryptoName, cryptoValue); //
                    listCrypto.add(crypto);
                }
                cryptoTableView.setItems(listCrypto);
                String crypto ="";
                for(int z = 0; z < listCrypto.size();z++){
                    crypto = crypto + (listCrypto.get(z).cryptoName+","+listCrypto.get(z).cryptoValue+",");
                }
                customersInformation[trueCustomer][6]= crypto;
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
                    //System.out.println("Veriler " + TextFolders/deneme.txt"+ " dosyasına yazıldı.");
                } catch (IOException e) {
                    System.out.println("Dosya yazma hatası: " + e.getMessage());
                }





            }
        }

    }
}
