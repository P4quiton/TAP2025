package com.example.tap2025;

import com.example.tap2025.vistas.Calculadora;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private VBox vBox;
    private MenuBar mnbPrincipal;
    private Menu menCompentencia1, manCompentencia2;
    private MenuItem mitCalculadora;
    private Scene escena;

    void CrearUI(){
        mitCalculadora=new MenuItem("Calculadora");
        mitCalculadora.setOnAction(actionEvent -> new Calculadora());
        menCompentencia1=new Menu("Competencia 1");
        //Agregar al menu competencia 1 la mitCalculadora
        menCompentencia1.getItems().addAll(mitCalculadora);
        mnbPrincipal=new MenuBar();
        mnbPrincipal.getMenus().addAll(menCompentencia1);
        //Instansear el vBox para que no suelte un nullPointerExcep
        //por que ahi va el mnbPrincipal
        vBox=new VBox(mnbPrincipal);
    }
    @Override
    public void start(Stage stage) throws IOException {
        CrearUI();
        stage.setTitle("Hola mundo de eventos");
        //Se agrega un setScene de forma anonima (objeto anonimo)
        stage.setScene(new Scene(vBox));
        stage.show();
        stage.setMaximized(true);
    }

    public static void main(String[] args) {
        launch();
    }

    void clickEvent(int cont){
        System.out.println("Evento desde un metodo "+cont);
    }
}

/*
JavaFx esta enfocado para RIA
Programacion orientada a eventos
Eventos en programacion: Son todas acciones asincronas el usuario lo provoca
en una pantalla visual
3 elementos para las interfaces:
stage: para la ventana osea toda la interfaz.
scene: la parte grafica
node: clase padre node
Si quiero usar vario elementos botones usamos un contenedor
padding espacio respecto del padre hacia el elemento hijo
maggin espacio respecto del hijo hacia el padre
Para trabajar un menu se necesitan 3 clases:
menuBar
contenidos dento de el menuMenus
menuItems
Este metodo tiene varias formas, si vamos a maximizar la ventana:
stage.setScene(new Scene(vBox));
sino
stage.setScene(new Scene(vBox,200,200));
Objetos observables: Son reactivos, recuperamos los elementos y luego cargamos
*/