module sample.eknbankapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.media;
    requires java.desktop;

    opens sample.eknbankapp to javafx.fxml , javafx.controls ,  javafx.web , javafx.media;
    exports sample.eknbankapp;
}