package com.example.demotienda.Controllers;

import com.example.demotienda.Services.TableProcess;
import com.example.demotienda.Services.TableProcess.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TableController {

    @FXML
    private TableView<Usuario> tablaDatos;
    @FXML
    private TableColumn<Usuario, String> colId;
    @FXML
    private TableColumn<Usuario, String> colNombre;
    @FXML
    private TableColumn<Usuario, String> colCosto;
    @FXML
    private TableColumn<Usuario, Void> colEditar;
    @FXML
    private TableColumn<Usuario, Void> colEliminar;

    @FXML
    private VBox mainContainer;

    private final TableProcess procesador = new TableProcess();
    private final ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));

        configurarColumnaEditar();
        configurarColumnaEliminar();

        procesador.cargarDatosDesdeArchivo(listaUsuarios);
        tablaDatos.setItems(listaUsuarios);
    }

    private void configurarColumnaEliminar() {
        colEliminar.setCellFactory(param -> new TableCell<>() {
            private final Button btnDel = new Button("Eliminar");

            {
                btnDel.setStyle("-fx-background-color: #C0392B; -fx-text-fill: white;");
                btnDel.setOnAction(e -> {
                    Usuario u = getTableView().getItems().get(getIndex());
                    procesador.eliminarRegistro(listaUsuarios, u);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btnDel);
            }
        });
    }


    @FXML
    private void onAgregarClick() {
        try {
            // 1. Cargar el FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demotienda/Views/add-view.fxml"));
            Parent root = loader.load();

            // 2. OBTENER EL CONTROLADOR DEL HIJO (AddController)
            AddController controller = loader.getController();

            // 3. PASAR LA LISTA (Esto es lo que falta y causa el error)
            controller.setListaUsuarios(this.listaUsuarios);

            // 4. Configurar y mostrar el Stage
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));

            // Efecto de blur opcional si lo tienes
            mainContainer.setEffect(new GaussianBlur(10));

            stage.showAndWait();

            // Quitar efecto al cerrar
            mainContainer.setEffect(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onEditarClick(Usuario seleccionado) {
        try {
            // 1. Aplicamos el desenfoque al fondo (mainContainer)
            mainContainer.setEffect(new GaussianBlur(10));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demoTienda/Views/edit-view.fxml"));
            Parent root = loader.load();

            // 2. Pasamos el usuario seleccionado al controlador de edición
            EditController controller = loader.getController();
            controller.cargarDatos(seleccionado);

            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT); // Vital para que no haya cuadro blanco
            stage.initModality(Modality.APPLICATION_MODAL);

            Scene scene = new Scene(root);
            scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
            stage.setScene(scene);

            stage.showAndWait();

            // 3. Al cerrar, guardamos cambios y quitamos el efecto
            new TableProcess().guardarEnArchivo(listaUsuarios);
            mainContainer.setEffect(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configurarColumnaEditar() {
        colEditar.setCellFactory(param -> new TableCell<>() {
            private final Button btnEdit = new Button("Editar");
            {
                // Estilo del botón azul como en tus capturas
                btnEdit.setStyle("-fx-background-color: #4169E1; -fx-text-fill: white; -fx-cursor: hand;");

                btnEdit.setOnAction(e -> {
                    // Obtenemos el objeto Usuario de la fila actual
                    Usuario seleccionado = getTableView().getItems().get(getIndex());

                    // Llamamos al método que abre form
                    onEditarClick(seleccionado);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    // Centramos el botón dentro de la celda
                    setStyle("-fx-alignment: CENTER;");
                    setGraphic(btnEdit);
                }
            }
        });
    }
}