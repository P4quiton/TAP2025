package com.example.tap2025.vistas;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rompecabezas extends Stage {

    private int tamanoPuzzle; // Tamaño del rompecabezas (3x3, 4x4, 5x5)
    private List<ImageView> piezas = new ArrayList<>();
    private List<ImageView> piezasCorrectas = new ArrayList<>();
    private int segundosTranscurridos = 0;
    private boolean juegoEnCurso = false;
    private boolean juegoIniciado = false; // Controla si el juego ha comenzado
    private Label etiquetaTiempo;
    private Button botonIniciar;
    private GridPane gridPane;
    private Scene escena;

    private ImageView piezaSeleccionada = null; // Pieza seleccionada para mover

    public Rompecabezas(int tamanoPuzzle) {
        this.tamanoPuzzle = tamanoPuzzle; // Asignar el tamaño del rompecabezas
        CrearUI();
        this.setScene(escena);
        this.setTitle("Rompecabezas " + tamanoPuzzle + "x" + tamanoPuzzle);
        this.setWidth(600);
        this.setHeight(600);
        this.show();
    }

    private void CrearUI() {
        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(10));

        gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(javafx.geometry.Pos.CENTER);

        // Establecer un tamaño para las celdas del GridPane
        gridPane.setPrefSize(500, 500);

        etiquetaTiempo = new Label("Tiempo: 0s");
        botonIniciar = new Button("Iniciar");
        botonIniciar.setOnAction(event -> manejarInicio());

        root.getChildren().addAll(gridPane, etiquetaTiempo, botonIniciar);

        escena = new Scene(root);

        // Cargar el archivo CSS
        escena.getStylesheets().add(getClass().getResource("/styles/rompecabezas.css").toExternalForm());

        // Inicializar el rompecabezas
        configurarPuzzle();
    }

    private void configurarPuzzle() {
        gridPane.getChildren().clear();
        piezas.clear();
        piezasCorrectas.clear();

        // Cargar las imágenes de las piezas desde la carpeta correspondiente
        String rutaCarpeta = "/images/" + tamanoPuzzle + "x" + tamanoPuzzle + "/";
        for (int i = 1; i <= tamanoPuzzle * tamanoPuzzle; i++) {
            String rutaImagen = rutaCarpeta + "pieza" + i + ".png";
            System.out.println("Intentando cargar: " + rutaImagen); // Mensaje de depuración
            Image imagen = new Image(getClass().getResourceAsStream(rutaImagen));
            if (imagen.isError()) {
                System.err.println("Error al cargar la imagen: " + rutaImagen);
                continue; // Saltar esta imagen si no se puede cargar
            }

            // Crear ImageView y ajustar su tamaño
            ImageView pieza = new ImageView(imagen);
            pieza.setFitWidth(150);
            pieza.setFitHeight(150);
            pieza.setPreserveRatio(true); // Mantener la proporción de la imagen
            pieza.setSmooth(true); // Suavizado de la imagen

            // Aplicar la clase CSS a la imagen
            pieza.getStyleClass().add("image-view");

            // Habilitar el clic para seleccionar y mover la pieza
            habilitarClicParaMover(pieza);

            piezas.add(pieza);
            piezasCorrectas.add(pieza);
        }

        // Mezclar las piezas
        Collections.shuffle(piezas);

        // Añadir las piezas al GridPane
        for (int i = 0; i < piezas.size(); i++) {
            ImageView pieza = piezas.get(i);
            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(pieza);
            gridPane.add(stackPane, i % tamanoPuzzle, i / tamanoPuzzle);
        }
    }

    private void habilitarClicParaMover(ImageView pieza) {
        pieza.setOnMouseClicked(event -> {
            if (!juegoIniciado) {
                // Mostrar un mensaje si el juego no ha comenzado
                mostrarAlerta("Atención", "¡Primero debes iniciar el juego! Haz clic en el botón 'Iniciar'.");
                return;
            }

            if (event.getButton() == MouseButton.PRIMARY) {
                if (piezaSeleccionada == null) {
                    // Seleccionar la pieza
                    piezaSeleccionada = pieza;
                    System.out.println("Pieza seleccionada: " + piezas.indexOf(pieza));
                } else {
                    // Mover la pieza seleccionada a la posición de la pieza actual
                    int indicePiezaSeleccionada = piezas.indexOf(piezaSeleccionada);
                    int indicePiezaActual = piezas.indexOf(pieza);

                    // Intercambiar las piezas en la lista
                    Collections.swap(piezas, indicePiezaSeleccionada, indicePiezaActual);

                    // Actualizar las posiciones en el GridPane
                    gridPane.getChildren().clear();
                    for (int i = 0; i < piezas.size(); i++) {
                        ImageView p = piezas.get(i);
                        StackPane stackPane = new StackPane();
                        stackPane.getChildren().add(p);
                        gridPane.add(stackPane, i % tamanoPuzzle, i / tamanoPuzzle);
                    }

                    // Deseleccionar la pieza
                    piezaSeleccionada = null;

                    // Verificar si el rompecabezas está completo
                    verificarPuzzle();
                }
            }
        });
    }

    private void manejarInicio() {
        if (!juegoEnCurso) {
            juegoEnCurso = true;
            juegoIniciado = true; // Habilitar los clics en las piezas
            botonIniciar.setDisable(true); // Deshabilitar el botón "Iniciar"
            iniciarTemporizador();
        }
    }

    private void iniciarTemporizador() {
        new Thread(() -> {
            while (juegoEnCurso) {
                try {
                    Thread.sleep(1000);
                    segundosTranscurridos++;
                    Platform.runLater(() -> etiquetaTiempo.setText("Tiempo: " + segundosTranscurridos + "s"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void verificarPuzzle() {
        if (piezas.equals(piezasCorrectas)) {
            juegoEnCurso = false;
            juegoIniciado = false; // Deshabilitar los clics en las piezas
            guardarTiempo();
            mostrarAlerta("¡Felicidades!", "Has completado el rompecabezas en " + segundosTranscurridos + " segundos.");
        }
    }

    private void guardarTiempo() {
        // Obtener la ruta de la carpeta de Descargas
        String rutaDescargas = System.getProperty("user.home") + "/Downloads/times.txt";
        try (FileWriter fw = new FileWriter(rutaDescargas, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("Tiempo: " + segundosTranscurridos + "s");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}