module com.example.demotienda {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demotienda to javafx.fxml;
    exports com.example.demotienda;
}