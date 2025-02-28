package com.example.tap2025.vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Cliente extends Stage {
    private Button btnGuardar;
    private TextField txtNomCte, txtDireccion, txtTelCte, txtEmail;
    private VBox vBox;
    private Scene escena;

    public Cliente(){
        this.setTitle("Registrar Cliente");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI(){
        txtNomCte=new TextField("Ingresa el nombre");
        txtDireccion=new TextField("Ingresa la direccion");
        txtTelCte=new TextField("Ingresa el numero de telefono");
        txtEmail=new TextField("Ingresa el email");
        btnGuardar=new Button("Guardar");
        vBox=new VBox(txtNomCte,txtDireccion,txtTelCte,txtEmail);
        escena=new Scene(vBox,120,150);
    }
}
