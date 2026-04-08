package com.example.demotienda.Controllers;

import com.example.demotienda.Services.LoadProcess;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoadController {

    @FXML
    private VBox rootVBox;

    private final LoadProcess loadProcess = new LoadProcess();

    @FXML
    public void initialize() {
        javafx.application.Platform.runLater(() -> {
            Stage stage = (Stage) rootVBox.getScene().getWindow();
            loadProcess.iniciarTemporizador(stage);
        });
    }
}