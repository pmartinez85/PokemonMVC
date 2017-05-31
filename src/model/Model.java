/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import entitats.Pokemon;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
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
import javax.management.Query;
import javax.swing.JOptionPane;
/**
 *
 * @author pedro
 * @param <T>
 */
public class Model<T> {
    public static Connection con = null;  
    public static ResultSet rt = null;
    public static PreparedStatement ps = null;
    
    private T t;
    
    public T get() { 
        return this.t; 
    }
    
    public void set(T t) { 
        this.t = t; 
    }
    
    public Model(T o){
        t = o;
    }
  
    
    public Model() {
        Properties props = new Properties();
        String url, user, password;
        url = user = password = null;
//        propertySupport = new PropertyChangeSupport(this);
//        vetoableSupport = new VetoableChangeSupport(this);
        
        
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
            JOptionPane.showMessageDialog(null, "No s'ha pogut establir la connexió a la BD...", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public void finalize() throws Throwable {
        if(rt!=null) rt.close();
        if(con!=null) con.close();
        System.out.println("Tancant la connexió a la BD...");
        JOptionPane.showMessageDialog(null, "Tancant la connexió a la BD...", "Error", JOptionPane.ERROR_MESSAGE);

        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }


    private void crearConnexio(String url, String usuari, String password){
        try {
            con = DriverManager.getConnection(url, usuari, password);
            System.out.println("Connectant a la BD...");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No s'ha pogut establir la connexió a la BD...", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    
    public void insertar(String sql){
            
        try(PreparedStatement sentenciaPr=con.prepareStatement(sql)) {
            //cridar una funció que ens comprovi la sql
            sentenciaPr.executeUpdate();
            //sentenciaPr.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error a l'insertar l'objecte!!", "Error", JOptionPane.ERROR_MESSAGE);
        }  
    
    }
    
    public void borrar(String sql){
        try(PreparedStatement sentenciaPr=con.prepareStatement(sql)) {
            sentenciaPr.executeUpdate();
            //sentenciaPr.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al borrar l'objecte!!", "Error", JOptionPane.ERROR_MESSAGE);
        }  
    
    }
    
    public void modificar(String sql){
        try(PreparedStatement sentenciaPr=con.prepareStatement(sql)) {
            sentenciaPr.executeUpdate();
            //sentenciaPr.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar l'objecte!!", "Error", JOptionPane.ERROR_MESSAGE);
        }  
    }
    
    public ArrayList<T> llistar(){
        //Query consulta= new Query();
        
        ArrayList llista = new ArrayList();
        String sql = "SELECT * FROM pokemon ORDER BY 1;";
        try(PreparedStatement sentenciaPr=con.prepareStatement(sql)) {
            this.rt=sentenciaPr.executeQuery();
            
            if(this.rt!=null){
            
                while(rt.next()){
                    int id=rt.getInt(1);
                    String nom=rt.getString(2);
                    String tipus=rt.getString(3);
                    Array atacs=rt.getArray(4);
                    llista.add(new Pokemon(id, nom, tipus, atacs));   
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al llistar els pokemons!!", "Error", JOptionPane.ERROR_MESSAGE);
        }  
        return llista;    
    }
    
    
//    //String que té com a valor el nom de la propietat lligada
//    
//    public static final String PROP_SAMPLE_PROPERTY = "_1_id";
//    
//    private PropertyChangeSupport propertySupport;
//    
//    
//
//    public void set1_id(Integer value1) {
//        _1_id = value1;
//        if ( this._1_id < 1){
//            propertySupport.firePropertyChange(PROP_SAMPLE_PROPERTY, 1,_1_id);
//        }
//    }
// 
//    public void addPropertyChangeListener(PropertyChangeListener listener) {
//        propertySupport.addPropertyChangeListener(listener);
//    }
//    
//    public void removePropertyChangeListener(PropertyChangeListener listener) {
//        propertySupport.removePropertyChangeListener(listener);
//    }
//    
//    //String que té com a valor el nom de la propietat restringida 
//    public static final String PROP_QUANTITAT = "_1_id";
//  
//    // La ID no pot ser menor de 1
//    
//    private VetoableChangeSupport vetoableSupport;
//
//    
//    
//    public void set1_id(int nouValor) throws PropertyVetoException {
//        float vellValor = _1_id;
//        //Abans de fer el canvi activem els vetadors
//        vetoableSupport.fireVetoableChange(PROP_QUANTITAT, vellValor, nouValor);
//        this._1_id = nouValor;
//    }
//
//    public void addVetoableChangeListener(VetoableChangeListener listener) {
//        vetoableSupport.addVetoableChangeListener(listener);
//    }
//
//
//    public void removeVetoableChangeListener(VetoableChangeListener listener) {
//        vetoableSupport.removeVetoableChangeListener(listener);
//    }    
 
}