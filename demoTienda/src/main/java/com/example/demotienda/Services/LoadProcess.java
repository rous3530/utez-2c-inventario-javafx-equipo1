package com.example.demotienda.Services;

import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class LoadProcess {

    public void iniciarTemporizador(Stage stageActual) {
        PauseTransition pausa = new PauseTransition(Duration.seconds(2));

        pausa.setOnFinished(event -> {
            try {
                stageActual.close();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demotienda/Views/table-view.fxml"));
                Parent root = loader.load();

                Stage nuevaStage = new Stage();
                nuevaStage.setScene(new Scene(root));
                nuevaStage.show();

            } catch (IOException e) {
                System.err.println("Error al cambiar de pestaña: " + e.getMessage());
            }
        });

        pausa.play();
    }
}