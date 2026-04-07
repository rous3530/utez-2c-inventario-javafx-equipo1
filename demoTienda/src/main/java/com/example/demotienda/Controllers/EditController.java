package com.example.demotienda.Controllers;

import com.example.demotienda.Services.TableProcess;
import com.example.demotienda.Services.TableProcess.Usuario;
import com.example.demotienda.Services.validateProcess;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditController {
    @FXML private TextField txtId, txtNombre, txtCosto, txtStock, txtCategoria;
    private Usuario usuarioSeleccionado;


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
        // Extraemos los valores de los campos de texto
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        String costo = txtCosto.getText();
        String stock = txtStock.getText();
        String categoria = txtCategoria.getText();

        // 1. Validar que no haya campos vacíos
        if (validateProcess.esVacio(id) || validateProcess.esVacio(nombre) ||
                validateProcess.esVacio(costo) || validateProcess.esVacio(stock) ||
                validateProcess.esVacio(categoria)) {

            validateProcess.showAlert("Campos Incompletos",
                    "Todos los campos son obligatorios. Por favor, rellénalos.");
            return;
        }

        // 2. Validar Formato de ID (Sin verificar duplicados, ya que es una edición)
        // Pasamos una lista vacía o null si tu método validarId lo permite,
        // o simplemente validamos el formato si tienes un método específico.
        if (!validateProcess.validarId(id, new java.util.ArrayList<>())) {
            validateProcess.showAlert("ID Inválido",
                    "El ID debe ser alfanumérico.");
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

        // --- Si todas las validaciones pasan, procedemos a actualizar ---

        // Actualizamos los valores del objeto original
        usuarioSeleccionado.nombreProperty().set(nombre);
        usuarioSeleccionado.costoProperty().set(costo);
        usuarioSeleccionado.stockProperty().set(stock);
        usuarioSeleccionado.CategoriaProperty().set(categoria);


        cerrar();
    }

    @FXML private void onCancelarClick() { cerrar(); }
    private void cerrar() { ((Stage) txtId.getScene().getWindow()).close(); }
}