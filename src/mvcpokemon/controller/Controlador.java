/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcpokemon.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import mvcpokemon.model.Model;
import static mvcpokemon.model.Model.con;
import mvcpokemon.view.VistaPokemons;

/**
 *
 * @author pedro
 */
public class Controlador {

    private Model odb;
    private VistaPokemons vista;
    private int filasel=-1;
    private int id = -1;
    private String nom = "";
    private String tipus = "";
    private Array atacs = null;
    public String[] atacsArray= null;

    
    public Controlador(Model odb, VistaPokemons jf) {
        this.odb = odb;
        this.vista = jf;
        carregaTaula(odb.llistarPokemons());
        borrarCamps();
        vista.setVisible(true);
        control();
    }
    
    public void borrarCamps() {
        //Posem en blanc el nom i tipus de pokemon
        vista.getjTextField1().setText("");
        vista.getjTextField2().setText("");
        vista.getjTextField3().setText("");
    }

    public void carregaTaula(ArrayList resultSet) {
        //Quan tornem a carregar la taula perdem la selecció que haviem fet i posem filasel a -1
        filasel = -1;

        //Si hi ha algun element a l'arraylist omplim la taula
        if (!resultSet.isEmpty()) {
            Vector columnNames = new Vector();
            Vector data = new Vector();
            DefaultTableModel model;
            //Obtenim la classe dels objectes de la llista
            Class<?> classe = resultSet.get(0).getClass();
            //Anotem el nº de camps de la classe
            int ncamps = classe.getDeclaredClasses().length;
            //Recorrem els camps de la classe i posem els seus noms com a columnes de la taula
            for (Field f : classe.getDeclaredFields()) {
                columnNames.addElement(f.getName());
            }

            //Guardem els descriptors de mètode que ens interessen (els getters)
            Vector<Method> methods = new Vector(resultSet.size());
            try {
                for (PropertyDescriptor pD : Introspector.getBeanInfo(classe).getPropertyDescriptors()) {
                    Method m = pD.getReadMethod();
                    if (m != null & !m.getName().equals("getClass")) {
                        methods.addElement(m);
                    }
                }
            } catch (IntrospectionException ex) {
                Logger.getLogger(VistaPokemons.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (Object m : resultSet) {
                Vector row = new Vector(ncamps);

                for (Method mD : methods) {
                    try {
                        row.addElement(mD.invoke(m));
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(VistaPokemons.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(VistaPokemons.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        Logger.getLogger(VistaPokemons.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                data.addElement(row);
            }

            model = new DefaultTableModel(data, columnNames);
            vista.getjTable2().setModel(model);

            TableColumn column;
            for (int i = 0; i < vista.getjTable2().getColumnCount(); i++) {
                column = vista.getjTable2().getColumnModel().getColumn(i);
                column.setMaxWidth(250);
            }
        }

    }

    public void control() {
        
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getSource().equals(vista.getjButton6())) {
                    if (filasel!=-1){
                            odb.borrarPokemon(id);
                            borrarCamps();
                            carregaTaula(odb.llistarPokemons());
                    }
                    else JOptionPane.showMessageDialog(null, "Per borrar un pokemon primer l'has de seleccionar!!", "Error", JOptionPane.ERROR_MESSAGE);                
                } 
                else 
                    if (actionEvent.getSource().equals(vista.getjButton5())) {
                        if (!nom.equals("") || !tipus.equals("") ) {
                         
                                odb.insertarPokemon(nom, tipus, atacs);
                                borrarCamps();
                                carregaTaula(odb.llistarPokemons());
                            
                        }
                        else JOptionPane.showMessageDialog(null, "No pots introduir un pokemon sense nom ni tipus!!", "Error", JOptionPane.ERROR_MESSAGE);
                    } 
                    else
                        if (actionEvent.getSource().equals(vista.getjButton7())) {
                            if (filasel!=-1 && (!nom.equals("") || !tipus.equals(""))){
                            
                                    odb.modificarPokemon(id, nom, tipus, atacs);
                                    borrarCamps();
                                    carregaTaula(odb.llistarPokemons());
                                
                            }
                            else JOptionPane.showMessageDialog(null, "Per modificar un pokemon primer l'has de seleccionar i posar algun valor!!", "Error", JOptionPane.ERROR_MESSAGE);                
                        }
                        else {
                            try {
                                    odb.finalize();
                            } catch (Throwable ex) {
                                System.out.println("Error tancant la base de dades!!");
                            }
                            System.exit(0);
                        }
            }
        };
        vista.getjButton4().addActionListener(actionListener);
        vista.getjButton5().addActionListener(actionListener);
        vista.getjButton6().addActionListener(actionListener);
        vista.getjButton7().addActionListener(actionListener);
        
        MouseAdapter mouseAdapter=new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); 
                
                try {
                    filasel = vista.getjTable2().getSelectedRow();
                    if (filasel != -1) {
                        id = Integer.parseInt(vista.getjTable2().getValueAt(filasel, 0).toString());
                        
                        nom = (String) vista.getjTable2().getValueAt(filasel, 1);
                        vista.getjTextField1().setText(nom);
                        
                        tipus = (String) vista.getjTable2().getValueAt(filasel, 2);
                        vista.getjTextField2().setText(tipus);
                        
                        atacs = (Array) vista.getjTable2().getValueAt(filasel, 3);
                        vista.getjTextField3().setText(atacs==null?"":atacs.toString());
                        
                    }else borrarCamps();
                } catch (NumberFormatException ex) {
                    System.out.println("Error al generar les dades: " + ex);
                }
            }
        
        };
        vista.getjTable2().addMouseListener(mouseAdapter);
        
        FocusAdapter focusAdapter=new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(e.getSource().equals(vista.getjTextField1())){
                    nom = vista.getjTextField1().getText().trim();
                }
                if(e.getSource().equals(vista.getjTextField2())){
                    tipus = vista.getjTextField2().getText().trim();
                }
                if(e.getSource().equals(vista.getjTextField3())){
                    try {
                        String separar = vista.getjTextField3().getText().replace("{", "");
                        separar = separar.replace("}", "");
                        final String[] arrayAtacs = separar.split(",");
                        atacs = con.createArrayOf("text", arrayAtacs);
                        Model.ps.setArray(1, atacs);
                    }catch (SQLException e2) {
                        System.out.println("Error al crear l'array" + e2);
                    }
                    //atacs = (Array) vista.getjTextField3();
                }
            }
        
        };
        
        vista.getjTextField1().addFocusListener(focusAdapter);
        vista.getjTextField2().addFocusListener(focusAdapter);
        vista.getjTextField3().addFocusListener(focusAdapter);
        
        WindowAdapter windowAdapter =new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent evt) {
                //Mateix codi que quan paretem el botó de sortir del programa
                try {
                    odb.finalize();
                } catch (Throwable ex) {
                    System.out.println("Error tancant la base de dades!!");
                }
                System.exit(0);
            }
        };
        
        vista.addWindowListener(windowAdapter);
    }

}
