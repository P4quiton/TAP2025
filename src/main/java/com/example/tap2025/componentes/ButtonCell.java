package com.example.tap2025.componentes;

import com.example.tap2025.modelos.ClienteDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;

import java.util.Optional;

//Vamos a hacer nuestro boton que va a tener el comportamiento que requerimos
//En este caso vamos a uusar el mismo para editar y borrar
public class ButtonCell extends TableCell<ClienteDAO,String> {
    private Button btnCelda;
    private String strLabelBtn;

    public ButtonCell(String label){

        strLabelBtn=label;
        btnCelda=new Button(strLabelBtn);
        btnCelda.setOnAction(actionEvent -> {
            ClienteDAO objC=this.getTableView().getItems().get(getIndex());
            if(strLabelBtn.equals("Editar")){

            }
            else {
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mensaje del Sistema");
                alert.setContentText("¿Deseas eliminar el registro seleccionado?");
                Optional<ButtonType>opcion=alert.showAndWait();
                if (opcion.get()==ButtonType.OK) {
                    objC.DELETE();
                }
            }
            this.getTableView().setItems(objC.SELECT());
            this.getTableView().refresh();
        });
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item,empty);
        if(!empty)
            this.setGraphic(btnCelda);
    }
}
