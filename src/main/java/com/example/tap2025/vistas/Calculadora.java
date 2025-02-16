package com.example.tap2025.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
    //Matriz de botones
    private Button[][] maBtnTeclado;
    //Reacomodar los botones de la calculadora
    private String[] strTeclas={"7","8","9","x","4","5","6","/","1","2","3","+",".","0","=","-"};
    private String entradaActual="";
    private double ultimoNum=0;
    private String ultimOperador="";

    public void CrearUI(){
        //Para instansear primero el gdpTeclado
        crearTeclado();
        txtSalida=new TextField("");
        txtSalida.setEditable(false);
        txtSalida.setAlignment(Pos.BASELINE_RIGHT);
        vBox=new VBox(txtSalida,gdpTeclado);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        escena=new Scene(vBox,200,200);
        //Ya estamos apuntando a la carpeta resources
        escena.getStylesheets().add(getClass().getResource("/styles/calcu.css").toString());
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
                    /*Esto es especifico, solo sale en esta parte del programa y al ser el ultimo cambio
                    es el cambio final que se muestra*/
                    //maBtnTeclado[i][j].setStyle("-fx-background-color: rgba(31,107,45,0.72)");
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
                if(!entradaActual.contains(".")){
                    entradaActual+=strTecla;
                    txtSalida.setText(entradaActual);
                }
                break;
            default:
                entradaActual+=strTecla;
                txtSalida.setText(entradaActual);
        }
    }

    private void manejadorOperadores(String operador) {
        if(!entradaActual.isEmpty()){
            ultimoNum=Double.parseDouble(entradaActual);
            //Pa limpiar la entrada y que se pueda poner otro num
            entradaActual="";
            ultimOperador=operador;
        }
    }

    private void calcularResultado() {
        double resultado=0,segundoNum=0;
        if(!entradaActual.isEmpty()&&!ultimOperador.isEmpty()){
            segundoNum=Double.parseDouble(entradaActual);
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
                    resultado=(segundoNum!=0)?ultimoNum/segundoNum:0;
            }
            txtSalida.setText(String.valueOf(resultado));
            entradaActual="";
            ultimOperador="";
        }
    }

    public Calculadora(){
        CrearUI();
        this.setScene(escena);
        this.setTitle("Calculadora");
        this.show();
    }
}