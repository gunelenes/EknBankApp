package sample.eknbankapp;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class FinancialSituationController implements Initializable {

    @FXML
    private PieChart pieChart;
    @FXML
    private ImageView logoImageView;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File logoImageFile = new File("Image/logoImage.jpg");
        Image logoImage = new Image(logoImageFile.toURI().toString());
        logoImageView.setImage(logoImage);

        Integer[] totalMoney;
        try {
            totalMoney = getCustomerInformation();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Dolars $ ",totalMoney[0]),
                        new PieChart.Data("Euros € ",totalMoney[1]),
                        new PieChart.Data("Turkish Liras ₺ ",totalMoney[2])
                );

        pieChartData.forEach(data ->
                data.nameProperty().bind(Bindings.concat(data.getName(),"Amount ",data.pieValueProperty())));

        pieChart.getData().addAll(pieChartData);
    }
    public double dolar = 0;
    public double euro= 0;
    public double turkishLira=0;
    public Integer[] getCustomerInformation() throws IOException {
        CustomerSettingController customerSettingController = new CustomerSettingController();
        String[][] customerInformation = customerSettingController.getCustomers();
        for (int i = 1; i < customerInformation.length; i++) {
            String[] customerMoney = customerInformation[i][5].split(",");
            dolar += Double.parseDouble(customerMoney[0]);
            euro += Double.parseDouble(customerMoney[1]);
            turkishLira += Double.parseDouble(customerMoney[2]);
        }
        Integer[] totalMoney = new Integer[3];
        totalMoney[0] = (int) dolar;
        totalMoney[1] = (int) euro;
        totalMoney[2] = (int) turkishLira;
        return totalMoney;
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
    private Button adminInformationButton;
    public void adminInformationButtonOnAction(ActionEvent event) throws IOException {
        AdminController loginController = new AdminController();
        loginController.adminView();
        Stage stage = (Stage) adminInformationButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Button customersButton;
    public void employeeCustomersViewButtonOnAction(ActionEvent event) throws IOException {
        EmployeeController employeeController = new EmployeeController();
        employeeController.employeeCustomersView();
        Stage stage = (Stage) customersButton.getScene().getWindow();
        stage.close();
    }
}
