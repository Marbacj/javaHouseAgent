module com.example.houseagent {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.houseagent to javafx.fxml;
    exports com.example.houseagent;
}