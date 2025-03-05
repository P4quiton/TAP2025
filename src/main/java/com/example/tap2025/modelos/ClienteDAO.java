package com.example.tap2025.modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class ClienteDAO {
    private int idCte;
    private String nomCte;
    private String direccion;
    private String emailCte;
    private String telCte;

    public int getIdCte() {
        return idCte;
    }
    public void setIdCte(int idCte) {
        this.idCte = idCte;
    }
    public String getNomCte() {
        return nomCte;
    }
    public void setNomCte(String nomCte) {
        this.nomCte = nomCte;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getEmailCte() {
        return emailCte;
    }
    public void setEmailCte(String emailCte) {
        this.emailCte = emailCte;
    }
    public String getTelCte() {
        return telCte;
    }
    public void setTelCte(String telCte) {
        this.telCte = telCte;
    }

    public void INSERT(){
        String query="INSERT INTO cliente(nomCte,direccion,emailCte,telCte) " +
                "values('"+nomCte+"','"+direccion+"','"+emailCte+"','"+telCte+"')";
        try{
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void UPDATE(){
        String query = "UPDATE cliente SET nomCte = '"+nomCte+"'," +
                "telCte = '"+telCte+"',direccion = '"+direccion+"'," +
                "emailCte = '"+emailCte+"' WHERE idCte = "+idCte;
        try {
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void DELETE(){
        String query = "DELETE FROM cliente WHERE idCte = "+idCte;
        try{
            Statement stmt = Conexion.connection.createStatement();
            stmt.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public ObservableList<ClienteDAO> SELECT(){
        String query = "SELECT * FROM cliente";
        ObservableList<ClienteDAO> listaC = FXCollections.observableArrayList();
        ClienteDAO objC;
        try {
            Statement stmt = Conexion.connection.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objC = new ClienteDAO();
                objC.setIdCte(res.getInt("idCte"));
                objC.setNomCte(res.getString("nomCte"));
                objC.setDireccion(res.getString("direccion"));
                objC.setTelCte(res.getString("telCte"));
                objC.setEmailCte(res.getString("emailCte"));
                listaC.add(objC);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaC;
    }
}
//Breakpoint se ejecuta el codigo y luego podemos depurar usando el debug
//En este ejemplo nos muestra lo que tiene el listaC antes de hacer el return