package com.example.tap2025.vistas;

import com.example.tap2025.modelos.ClienteDAO;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Cliente extends Stage {
    private Button btnGuardar;
    private TextField txtNomCte, txtDireccion, txtTelCte, txtEmail;
    private VBox vBox;
    private Scene escena;
    private ClienteDAO objC;
    private TableView<ClienteDAO> tbvClientes;

    public Cliente(TableView<ClienteDAO> tbvCte, ClienteDAO obj){
        this.tbvClientes=tbvCte;
        CrearUI();
        if(obj==null){
            objC=new ClienteDAO();
        }else{
            objC=obj;
            txtNomCte.setText(objC.getNomCte());
            txtDireccion.setText(objC.getDireccion());
            txtEmail.setText(objC.getEmailCte());
            txtTelCte.setText(objC.getTelCte());
        }
        //objC = obj == null ? new ClienteDAO() : objC;Si el obj es null es insercion
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
        btnGuardar.setOnAction(actionEvent -> {
                objC.setNomCte(txtNomCte.getText());
                objC.setDireccion(txtDireccion.getText());
                objC.setTelCte(txtTelCte.getText());
                objC.setEmailCte(txtEmail.getText());
                //Para saber si se tiene que comportar para editar o insertar
                if(objC.getIdCte()>0) {
                    objC.UPDATE();
                }else {
                    objC.INSERT();
                }
                //Para ver los nuevos cambios
                tbvClientes.setItems(objC.SELECT());
                tbvClientes.refresh();
                //Para que se cierre la ventana
                this.close();
        });
        vBox=new VBox(txtNomCte,txtDireccion,txtTelCte,txtEmail,btnGuardar);
        escena=new Scene(vBox,120,150);
    }
}
