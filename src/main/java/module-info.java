module sample.eknbankapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.media;

    opens sample.eknbankapp to javafx.fxml;
    exports sample.eknbankapp;
}