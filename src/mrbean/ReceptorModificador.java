/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrbean;

/**
 *
 * @author pedro
 */
    
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class ReceptorModificador implements PropertyChangeListener{

   
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        
        System.out.println("Canvi a la propietat "+evt.getPropertyName());
        System.out.println("Valor anterior: "+evt.getOldValue());
        System.out.println("Valor actual: "+evt.getNewValue());  
    } 
}  