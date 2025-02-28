package com.example.tap2025.vistas;

import com.example.tap2025.modelos.ClienteDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ListaClientes extends Stage {
    private ToolBar tlbMenu;
    private TableView<ClienteDAO> tbvClientes;
    private VBox vBox;
    private Scene escena;
    private Button btnAgregar;

    public ListaClientes(){
        CrearUI();
        this.setTitle("Listado de Clientes");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI(){
        tlbMenu=new ToolBar();
        btnAgregar=new Button();
        ImageView imv=new ImageView(getClass().getResource("/images/person_add_icon.jpg").toString());
        imv.setFitWidth(20);
        imv.setFitHeight(20);
        btnAgregar.setGraphic(imv);
        tlbMenu=new ToolBar(btnAgregar);
        tbvClientes=new TableView<>();
        vBox=new VBox(tlbMenu,tbvClientes);
        escena=new Scene(vBox,800,600);
    }
}
