module sample.eknbankapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens sample.eknbankapp to javafx.fxml;
    exports sample.eknbankapp;
}