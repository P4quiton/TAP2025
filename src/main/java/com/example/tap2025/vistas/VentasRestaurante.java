package com.example.tap2025.vistas;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class VentasRestaurante extends Stage {
    private Panel pnlRestaurante;
    private Scene escena;
    public VentasRestaurante(){
        CrearUI();
        this.setTitle("El Aguilucho");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        pnlRestaurante=new Panel("Tacos el inge");
        pnlRestaurante.getStyleClass().add("panel-warning");
        escena=new Scene(pnlRestaurante,300,200);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
    }
}
