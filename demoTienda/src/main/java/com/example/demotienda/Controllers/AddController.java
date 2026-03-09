package com.example.demotienda.Controllers;

import com.example.demotienda.Services.TableProcess;
import com.example.demotienda.Services.TableProcess.Usuario;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddController {

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCosto;
    @FXML private TextField txtStock;

    private ObservableList<Usuario> listaUsuarios;
    private final TableProcess procesador = new TableProcess();

    public void setListaUsuarios(ObservableList<Usuario> lista) {
        this.listaUsuarios = lista;
    }

    @FXML
    private void onGuardarClick() {
        String id = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        String costo = txtCosto.getText().trim();
        String stock = txtStock.getText().trim();

        if (!id.isEmpty() && !nombre.isEmpty() && !costo.isEmpty()) {
            // Creamos el nuevo objeto (id alfanumérico)
            Usuario nuevo = new Usuario(id, nombre, costo,stock);

            // Agregamos a la lista observable (se verá en la tabla de inmediato)
            listaUsuarios.add(nuevo);

            // Guardamos en el archivo datos.txt
            procesador.guardarEnArchivo(listaUsuarios);

            cerrarVentana();
        } else {
            System.out.println("Error: Todos los campos son obligatorios.");
        }
    }

    @FXML
    private void onCancelarClick() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) txtId.getScene().getWindow();
        stage.close();
    }
}