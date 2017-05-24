/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inici;

import vista.Vista;
import model.Model;
import controlador.Controlador;

/**
 *
 * @author pedro
 */
public class Inici {
    
    static Model model=new Model();
    static Vista vista=new Vista();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Controlador(model, vista);
    }
    
}
