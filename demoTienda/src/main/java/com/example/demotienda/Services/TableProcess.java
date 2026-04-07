package com.example.demotienda.Services;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TableProcess {

    private final String RUTA_ARCHIVO = "data/datos.txt";

    public static class Usuario {
        private final SimpleStringProperty id;
        private final SimpleStringProperty nombre;
        private final SimpleStringProperty costo;
        private final SimpleStringProperty stock;
        private final SimpleStringProperty categoria;

        public Usuario(String id, String nombre, String costo, String stock,String categoria) {
            this.id = new SimpleStringProperty(id);
            this.nombre = new SimpleStringProperty(nombre);
            this.costo = new SimpleStringProperty(costo);
            this.stock = new SimpleStringProperty(stock);
            this.categoria = new SimpleStringProperty(categoria);
        }

        public String getId() { return id.get(); }
        public String getNombre() { return nombre.get(); }
        public String getCosto() { return costo.get(); }
        public String getStock() { return stock.get(); }
        public String getCategoria() { return categoria.get(); }

        public SimpleStringProperty idProperty() { return id; }
        public SimpleStringProperty nombreProperty() { return nombre; }
        public SimpleStringProperty costoProperty() { return costo; }
        public SimpleStringProperty stockProperty() { return stock; }
        public SimpleStringProperty CategoriaProperty() { return categoria; }

        @Override
        public String toString() {
            return getId() + "," + getNombre() + "," + getCosto()+"," + getStock()+"," + getCategoria();
        }
    }

    // LEER: Carga los datos al iniciar la tabla
    public void cargarDatosDesdeArchivo(ObservableList<Usuario> lista) {
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Ignorar líneas vacías para evitar errores de índice
                if (linea.trim().isEmpty()) continue;

                String[] partes = linea.split(",");

                // Solo agregar si la línea tiene exactamente las 2 partes (ID y Nombre)
                if (partes.length == 5) {
                    lista.add(new Usuario(partes[0].trim(), partes[1].trim(), partes[2].trim(), partes[3].trim(), partes[4].trim()));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer: " + e.getMessage());
        }
    }

    // GUARDAR: Sobrescribe el archivo con la lista actual (CRUD)
    public void guardarEnArchivo(ObservableList<Usuario> lista) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(RUTA_ARCHIVO))) {
            for (Usuario u : lista) {
                bw.write(u.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eliminarRegistro(ObservableList<Usuario> lista, Usuario usuario) {
        lista.remove(usuario);
        guardarEnArchivo(lista); // Persistir el cambio
    }

}