package com.example.demotienda.Controllers;

import com.example.demotienda.Services.TableProcess;
import com.example.demotienda.Services.TableProcess.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditController {
    @FXML private TextField txtId, txtNombre, txtCosto, txtStock, txtCategoria;
    private Usuario usuarioSeleccionado;
    private final TableProcess procesador = new TableProcess();


    public void cargarDatos(Usuario usuario) {
        this.usuarioSeleccionado = usuario;
        txtId.setText(usuario.getId());
        txtNombre.setText(usuario.getNombre());
        txtCosto.setText(usuario.getCosto());
        txtStock.setText(usuario.getStock());
        txtCategoria.setText(usuario.getCategoria());
    }

    @FXML
    private void onActualizarClick() {
        // Actualizamos los valores del objeto original (se refleja en la tabla automáticamente)
        usuarioSeleccionado.nombreProperty().set(txtNombre.getText());
        usuarioSeleccionado.costoProperty().set(txtCosto.getText());
        usuarioSeleccionado.stockProperty().set(txtStock.getText());
        usuarioSeleccionado.stockProperty().set(txtStock.getText());
        usuarioSeleccionado.CategoriaProperty().set(txtCategoria.getText());

        // Guardamos los cambios en el archivo TXT
        // Aquí pasamos la lista completa desde el TableController o llamamos al guardado
        cerrar();
    }

    @FXML private void onCancelarClick() { cerrar(); }
    private void cerrar() { ((Stage) txtId.getScene().getWindow()).close(); }
}