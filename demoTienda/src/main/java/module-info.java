module com.example.demotienda {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demotienda to javafx.fxml;
    opens com.example.demotienda.Controllers to javafx.fxml;
    opens com.example.demotienda.Models to javafx.base;
    exports com.example.demotienda;
    exports com.example.demotienda.Controllers;
    exports com.example.demotienda.Models;
    exports com.example.demotienda.Services;
}