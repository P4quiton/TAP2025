package com.example.tap2025.vistas;

import com.example.tap2025.modelos.ClienteDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
        //Para ir creando las columnas
        CreateTable();
        vBox=new VBox(tlbMenu,tbvClientes);
        escena=new Scene(vBox,800,600);
    }

    private void CreateTable() {
        //tenemos que instancear para poder usar el select
        ClienteDAO objC=new ClienteDAO();
        //Es necesario poner el tipo de dato para definir que columnas va a tener el TableView
        TableColumn<ClienteDAO,String>tbcNomCte=new TableColumn<>("Nombre");
        //Aqui ya se define como tal el valor de la columna
        tbcNomCte.setCellValueFactory(new PropertyValueFactory<>("nomCte"));

        TableColumn<ClienteDAO,String>tbcDireccionCte=new TableColumn<>("Direccion");
        tbcDireccionCte.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        TableColumn<ClienteDAO,String>tbcTelefonoCte=new TableColumn<>("Telefono");
        tbcTelefonoCte.setCellValueFactory(new PropertyValueFactory<>("telCte"));

        TableColumn<ClienteDAO,String>tbcEmailCte=new TableColumn<>("Email");
        tbcEmailCte.setCellValueFactory(new PropertyValueFactory<>("emailCte"));
        //Metemos las columnas al table View
        tbvClientes.getColumns().addAll(tbcNomCte,tbcDireccionCte,tbcTelefonoCte,tbcEmailCte);
        //Para mostrar los datos, entonces necesitamos el metodo select
        tbvClientes.setItems(objC.SELECT());
    }
}
