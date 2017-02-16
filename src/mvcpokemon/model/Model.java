/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mvcpokemon.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
/**
 *
 * @author pedro
 */
public class Model {
    public static Connection con=null;  
    public static ResultSet rt = null;

    public Model() {
        Properties props = new Properties();
        String url, user, password;
        url=user=password=null;
        
        try(FileInputStream in = new FileInputStream("database.properties")) {
            props.load(in);
            url = props.getProperty("db.url");
           // System.out.println(url);
            user = props.getProperty("db.user");
            //System.out.println(user);
            password = props.getProperty("db.password");
            //System.out.println(password);
            crearConnexio(url, user, password);
        } catch (IOException ex) {
            System.err.println("No s'ha pogut establir la connexió a la BD...");
            System.exit(0);
        }   
    }

    public void finalize() throws Throwable {
        if(rt!=null) rt.close();
        if(con!=null) con.close();
        System.out.println("Tancant la connexió a la BD...");
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }


    private void crearConnexio(String url, String usuari, String password){
        try {
            con = DriverManager.getConnection(url, usuari, password);
            System.out.println("Connectant a la BD...");
        } catch (SQLException ex) {
            System.err.println("No s'ha pogut establir la connexió a la BD...");
            System.exit(0);
        }
    }
    
    public void insertarPokemon(String _2_nom, String _3_tipus, Array _4_atacs){
            
        String sql = "INSERT INTO pokemon (nom, tipus, atacs) VALUES (? , ? , ?)";
        
        try(PreparedStatement sentenciaPr=con.prepareStatement(sql)) {
            //Array arrayatacs = con.createArrayOf("text", _4_atacs);
            sentenciaPr.setString(1, _2_nom);
            sentenciaPr.setString(2, _3_tipus);
            sentenciaPr.setArray(3, _4_atacs);
            sentenciaPr.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error a l'insertar el pokemon!!");
        }  
    
    }
    
    public void borrarPokemon(int _1_id){
            
        String sql = "DELETE FROM pokemon WHERE id=?";
        try(PreparedStatement sentenciaPr=con.prepareStatement(sql)) {
            sentenciaPr.setInt(1, _1_id);
            sentenciaPr.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al borrar el pokemon!!");
        }  
    
    }
    
    public void modificarPokemon(int _1_id, String _2_nom, String _3_tipus, Array _4_atacs){
            
        String sql = "UPDATE pokemon SET nom=?, tipus=? , atacs=?" + "WHERE id=?";
        try(PreparedStatement sentenciaPr=con.prepareStatement(sql)) {
            //Array arrayatacs = con.createArrayOf("text", _4_atacs);
            sentenciaPr.setString(2, _2_nom);
            sentenciaPr.setString(3, _3_tipus);
            sentenciaPr.setArray(4, _4_atacs);
            sentenciaPr.setInt(1, _1_id);
            sentenciaPr.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al modificar el pokemon!!");
        }  
    
    }
    
    public ArrayList<TaulaPokemon> llistarPokemons(){
            
        ArrayList llista=new ArrayList();
        String sql = "SELECT * FROM pokemon ORDER BY 1;";
        try(PreparedStatement sentenciaPr=con.prepareStatement(sql)) {
            this.rt=sentenciaPr.executeQuery();
            
            if(this.rt!=null){
            
                while(rt.next()){
                    int id=rt.getInt(1);
                    String nom=rt.getString(2);
                    String tipus=rt.getString(3);
                    Array atacs=rt.getArray(4);
                    
                    //llista.add(new TaulaPokemon(id, nom, tipus, new Contenedora(atacs)));
                    llista.add(new TaulaPokemon(id, nom, tipus, atacs));   
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al llistar els pokemons!!");
        }  
        return llista;    
    }


}