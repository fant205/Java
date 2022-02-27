module com.geekbrains.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.geekbrains.client to javafx.fxml;
    exports com.geekbrains.client;
}