module com.example.java3lesson2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.geekbrains.client to javafx.fxml;
    exports com.geekbrains.client;
}