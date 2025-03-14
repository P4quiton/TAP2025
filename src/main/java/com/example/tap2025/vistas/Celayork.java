package com.example.tap2025.vistas;

import com.example.tap2025.componentes.Hilo;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Celayork extends Stage {
    private VBox vBox;
    private GridPane gdpCalles;
    private Button btnIniciar;
    private Label[] arLblRutas;
    private ProgressBar[] arPgbRutas;
    private Scene escena;
    private String[] strRutas={"Pinos","Teneria","San Juan de la Vega","Monte Blanco","Laureles"};
    private Hilo[] thrRutas;

    public Celayork(){
        CrearUI();
        this.setTitle("Calles de Celaya");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        btnIniciar= new Button("Iniciar");
        arPgbRutas= new ProgressBar[5];
        arLblRutas=new Label[5];
        thrRutas=new Hilo[5];
        gdpCalles=new GridPane();
        for (int i = 0; i < arPgbRutas.length; i++){
            arLblRutas[i]=new Label(strRutas[i]);
            arPgbRutas[i]=new ProgressBar(0);
            thrRutas[i]=new Hilo(strRutas[i],arPgbRutas[i]);
            gdpCalles.add(arLblRutas[i],0,i);
            gdpCalles.add(arPgbRutas[i],1,i);
        }
        btnIniciar.setOnAction(actionEvent -> {
            for (int i = 0; i < arPgbRutas.length; i++) {
                thrRutas[i].start();
            }
        });
        vBox=new VBox(gdpCalles,btnIniciar);
        escena=new Scene(vBox,300,200);
    }
}
