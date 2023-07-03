module com.example.houseagent {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.houseagent to javafx.fxml;
    exports com.example.houseagent;
    exports com.example.houseagent.service;
    opens com.example.houseagent.service to javafx.fxml;
}