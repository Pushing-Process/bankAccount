module com.example.login {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;

    opens com.example.login to javafx.fxml;
    exports com.example.login;
    exports controller;
    opens controller to javafx.fxml;
}