# Sistema de Inventario de Productos Tienda
# Proyecto Integrador JavaFX - Grupo 2°C
Este proyecto consiste en una aplicación de escritorio desarrollada en Java y JavaFX diseñada para la gestión eficiente de inventarios. Permite el control total de productos (CRUD) mediante persistencia de datos en archivos locales, eliminando la necesidad de una conexión a internet o base de datos externa.

# Características Principales

1.- Gestión Completa (CRUD): Registro, consulta, edición y eliminación de productos.

2.- Persistencia de Datos: Almacenamiento automático en archivo .txt estructurado.

3.- Interfaz Gráfica (GUI): Diseñada con FXML para una separación clara entre lógica y vista.

4.- Validaciones Robustas: Control de campos vacíos, tipos de datos numéricos y prevención de IDs duplicados.

5.-Funciones Avanzadas:Búsqueda y filtrado en tiempo real por nombre o código.

5.1.- Ordenamiento dinámico por precio y nombre.

# Estructura del Archivo de Datos
La información se almacena en un archivo plano llamado datos.txt (o .csv) ubicado en la raíz dentro de una carpeta llamada data dentro del proyecto. El formato utilizado es:

ID,Nombre,Precio,Stock,Categoría

P001,Leche Entera,25.50,50,Lácteos
P002,Pan Integral,32.00,20,Panadería
P003,Arroz 1kg,18.50,100,Abarrotes
P004,Jabón de Trastes,15.00,30,Limpieza
P005,Aceite Vegetal,45.00,15,Abarrotes