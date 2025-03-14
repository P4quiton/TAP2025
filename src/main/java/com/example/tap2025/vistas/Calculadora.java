package com.example.tap2025.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculadora extends Stage {

    private Scene escena;
    private TextField txtSalida;
    private VBox vBox;
    private GridPane gdpTeclado;
    private Button[][] maBtnTeclado;
    private Button btnBorrar;
    private String[] strTeclas={"7","8","9","x","4","5","6","/","1","2","3","+",".","0","=","-"};
    private String entradaActual="";
    private double ultimoNum=0;
    private String ultimOperador="";

    public void CrearUI(){
        crearTeclado();
        txtSalida=new TextField("");
        txtSalida.setEditable(false);
        txtSalida.setAlignment(Pos.BASELINE_RIGHT);

        btnBorrar=new Button("CE");
        btnBorrar.setPrefSize(200,50);
        btnBorrar.setOnAction(actionEvent -> borrarEntrada());

        vBox=new VBox(txtSalida,gdpTeclado,btnBorrar);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        escena=new Scene(vBox,200,250);
        escena.getStylesheets().add(getClass().getResource("/styles/calcu.css").toString());
    }

    private void borrarEntrada() {
        entradaActual="";
        txtSalida.setText("");
    }

    public void crearTeclado(){
        maBtnTeclado = new Button[4][4];
        gdpTeclado = new GridPane();
        gdpTeclado.setHgap(5);
        gdpTeclado.setVgap(5);
        int pos=0;
        for(int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++) {
                maBtnTeclado[i][j]=new Button(strTeclas[pos]);
                if(strTeclas[pos].equals("x")){
                    maBtnTeclado[i][j].setId("fontButton");
                }
                int finalPos=pos;
                maBtnTeclado[i][j].setOnAction(actionEvent -> EventoTeclado(strTeclas[finalPos]));
                maBtnTeclado[i][j].setPrefSize(50,50);
                gdpTeclado.add(maBtnTeclado[i][j],j,i);
                pos++;
            }
        }
    }

    private void EventoTeclado(String strTecla) {
        switch(strTecla){
            case "=":
                calcularResultado();
                break;
            case "+": case "-": case"x": case "/":
                manejadorOperadores(strTecla);
                break;
            case ".":
                if (!entradaActual.isEmpty() && Character.isDigit(entradaActual.charAt(entradaActual.length() - 1))) {
                    if (!entradaActual.contains(".")) {
                        entradaActual += strTecla;
                        txtSalida.setText(entradaActual);
                    }
                } else {
                    mostrarAlerta("Error", "Punto en posición inválida");
                }
                break;
            default:
                entradaActual+=strTecla;
                txtSalida.setText(entradaActual);
        }
    }

    private void manejadorOperadores(String operador) {
        if(!entradaActual.isEmpty() && !entradaActual.equals(".")) {
            ultimoNum=Double.parseDouble(entradaActual);
            entradaActual="";
            ultimOperador=operador;
        } else {
            mostrarAlerta("Error", "Entrada inválida");
            entradaActual = "";
        }
    }

    private void calcularResultado() {
        double resultado=0,segundoNum=0;
        if(!entradaActual.isEmpty()&&!ultimOperador.isEmpty()){
            segundoNum=Double.parseDouble(entradaActual);
            if(ultimOperador.equals("/")&&segundoNum==0){
                mostrarAlerta("Error", "No se puede dividir entre cero");
                entradaActual="";
                ultimOperador="";
                return;
            }
            switch (ultimOperador){
                case "+":
                    resultado=ultimoNum+segundoNum;
                    break;
                case "-":
                    resultado=ultimoNum-segundoNum;
                    break;
                case "x":
                    resultado=ultimoNum*segundoNum;
                    break;
                case "/":
                    resultado=ultimoNum/segundoNum;
                    break;
            }
            txtSalida.setText(String.valueOf(resultado));
            entradaActual="";
            ultimOperador="";
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public Calculadora(){
        CrearUI();
        this.setScene(escena);
        this.setTitle("Calculadora");
        this.show();
    }
}
