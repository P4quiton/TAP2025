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
    private String[] strTeclas={"7","8","9","4","5","6","1","2","3","+","=","0",".","-","*","/"};

    public void CrearUI(){
        //Para instansear primero el gdpTeclado
        crearTeclado();
        txtSalida=new TextField("0");
        txtSalida.setEditable(false);
        txtSalida.setAlignment(Pos.BASELINE_RIGHT);

        vBox=new VBox(txtSalida,gdpTeclado);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));
        escena=new Scene(vBox,200,200);

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
                maBtnTeclado[i][j].setPrefSize(50,50);
                gdpTeclado.add(maBtnTeclado[i][j],j,i);
                pos++;
            }
        }
    }

    public Calculadora(){
        CrearUI();
        this.setScene(escena);
        this.setTitle("Calculadora");
        this.show();
    }
}