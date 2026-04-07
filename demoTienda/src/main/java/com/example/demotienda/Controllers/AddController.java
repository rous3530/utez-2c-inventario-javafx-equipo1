package com.example.demotienda.Controllers;

import com.example.demotienda.Services.TableProcess;
import com.example.demotienda.Services.TableProcess.Usuario;
import com.example.demotienda.Services.validateProcess;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class AddController {

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCosto;
    @FXML private TextField txtStock;
    @FXML private TextField txtCategoria;

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
        String categoria = txtCategoria.getText().trim();

        // 1. Validar que no haya campos vacíos
        if (validateProcess.esVacio(id) || validateProcess.esVacio(nombre) ||
                validateProcess.esVacio(costo) || validateProcess.esVacio(stock) ||
                validateProcess.esVacio(categoria)) {

            validateProcess.showAlert("Campos Incompletos",
                    "Todos los campos son obligatorios. Por favor, rellénalos.");
            return;
        }

        // 2. Validar Formato de ID y Duplicados
        List<String> idsExistentes = listaUsuarios.stream().map(u -> u.getId()).toList();
        if (!validateProcess.validarId(id, idsExistentes)) {
            validateProcess.showAlert("ID Inválido",
                    "El ID debe ser alfanumérico y no puede estar repetido.");
            return;
        }

        // 3. Validar Nombre (Mínimo 3 caracteres y letras latinas)
        if (!validateProcess.validarNombre(nombre)) {
            validateProcess.showAlert("Nombre No Válido",
                    "El nombre debe tener al menos 3 letras y no incluir símbolos especiales.");
            return;
        }

        // 4. Validar Costo (Número positivo)
        if (!validateProcess.validarCosto(costo)) {
            validateProcess.showAlert("Costo Inválido",
                    "El costo debe ser un número mayor a 0.");
            return;
        }

        // 5. Validar Stock (Número entero positivo)
        if (!validateProcess.validarStock(stock)) {
            validateProcess.showAlert("Stock Inválido",
                    "El stock debe ser un número entero (0 o más).");
            return;
        }


        // Creamos el nuevo objeto
        Usuario nuevo = new Usuario(id, nombre, costo, stock, categoria);

        // Agregamos a la lista observable
        listaUsuarios.add(nuevo);

        // Guardamos en el archivo datos.txt
        procesador.guardarEnArchivo(listaUsuarios);

        // Alerta de éxito con el nuevo diseño
        validateProcess.showAlert("Éxito",
                "El producto se ha registrado y guardado correctamente.");

        cerrarVentana();
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