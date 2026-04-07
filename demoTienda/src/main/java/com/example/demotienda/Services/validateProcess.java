package com.example.demotienda.Services;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;
import java.util.regex.Pattern;

public class validateProcess {

    private static final String REGEX_LATIN = "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]+$";
    private static final String REGEX_ALPHANUMERIC = "^[a-zA-Z0-9]+$";

    /**
     * Muestra una alerta COMPLETAMENTE PERSONALIZADA que coincide con el diseño del formulario.
     * Reemplaza el uso de AlertType.
     */
    public static void showAlert(String title, String content) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Notificación"); // Título de la ventana (invisible si usamos TRANSPARENT)

        // Usar StageStyle.TRANSPARENT para permitir bordes redondeados verdaderos
        dialog.initStyle(StageStyle.TRANSPARENT);

        DialogPane dialogPane = dialog.getDialogPane();

        // 1. Cargar el CSS personalizado
        // Asegúrate de que la ruta coincida con la ubicación de tu archivo CSS
        String cssPath = validateProcess.class.getResource("/com/example/demotienda/Styles/alert-style.css").toExternalForm();
        dialogPane.getStylesheets().add(cssPath);
        dialogPane.getStyleClass().add("custom-alert");

        // 2. Crear el contenido visual (imitando la estructura de tu formulario)
        VBox vboxContenido = new VBox(10);
        vboxContenido.setAlignment(Pos.TOP_CENTER);
        vboxContenido.setPadding(new javafx.geometry.Insets(10, 0, 0, 0));

        Label lblTitulo = new Label(title.toUpperCase()); // Como "NUEVO PRODUCTO"
        lblTitulo.getStyleClass().add("label"); // El CSS se encarga del color y fuente

        Label lblContenido = new Label(content);
        lblContenido.setWrapText(true);
        lblContenido.setAlignment(Pos.CENTER);
        lblContenido.setMaxWidth(300); // Evitar que la alerta sea muy ancha

        vboxContenido.getChildren().addAll(lblTitulo, lblContenido);
        dialogPane.setContent(vboxContenido);

        // 3. Configurar los botones (imitar el botón GUARDAR)
        ButtonType btnAceptarType = new ButtonType("ACEPTAR", ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().addAll(btnAceptarType);

        // Dar sombra a la ventana entera (Stage)
        Stage stage = (Stage) dialogPane.getScene().getWindow();
        stage.getScene().setFill(javafx.scene.paint.Color.TRANSPARENT); // Necesario para StageStyle.TRANSPARENT

        dialog.showAndWait();
    }

    public static boolean esVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    public static boolean validarNombre(String nombre) {
        if (esVacio(nombre) || nombre.trim().length() < 3) return false;
        return Pattern.matches(REGEX_LATIN, nombre);
    }

    public static boolean validarStock(String stock) {
        if (esVacio(stock)) return false;
        try {
            int valor = Integer.parseInt(stock.trim());
            return valor >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validarCosto(String costo) {
        if (esVacio(costo)) return false;
        try {
            double valor = Double.parseDouble(costo.trim().replace(",", "."));
            return valor > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validarId(String id, List<String> idsExistentes) {
        if (esVacio(id) || !Pattern.matches(REGEX_ALPHANUMERIC, id)) {
            return false;
        }

        for (String existente : idsExistentes) {
            if (existente.equalsIgnoreCase(id.trim())) {
                return false;
            }
        }
        return true;
    }
}