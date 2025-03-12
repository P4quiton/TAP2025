package com.example.tap2025.vistas;

import com.example.tap2025.componentes.ButtonCell;
import com.example.tap2025.modelos.ClienteDAO;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;


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
        tbvClientes=new TableView<>();
        btnAgregar=new Button();
        btnAgregar.setOnAction(actionEvent -> new Cliente(tbvClientes, null));
        ImageView imv=new ImageView(getClass().getResource("/images/person_add_icon.jpg").toString());
        imv.setFitWidth(20);
        imv.setFitHeight(20);
        btnAgregar.setGraphic(imv);
        tlbMenu=new ToolBar(btnAgregar);
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
        //Para el ButtonCell
        TableColumn<ClienteDAO,String>tbcEditar=new TableColumn<>("Editar");
        tbcEditar.setCellFactory(new Callback<TableColumn<ClienteDAO, String>, TableCell<ClienteDAO, String>>() {
            @Override
            public TableCell<ClienteDAO, String> call(TableColumn<ClienteDAO, String> param) {
                return new ButtonCell("Editar");
            }
        });

        TableColumn<ClienteDAO,String>tbcEliminar=new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(new Callback<TableColumn<ClienteDAO, String>, TableCell<ClienteDAO, String>>() {
            @Override
            public TableCell<ClienteDAO, String> call(TableColumn<ClienteDAO, String> param) {
                return new ButtonCell("Eliminar");
            }
        });

        //Metemos las columnas al table View
        tbvClientes.getColumns().addAll(tbcNomCte,tbcDireccionCte,tbcTelefonoCte,tbcEmailCte,tbcEditar,tbcEliminar);
        //Para mostrar los datos, entonces necesitamos el metodo select
        tbvClientes.setItems(objC.SELECT());
    }
}
