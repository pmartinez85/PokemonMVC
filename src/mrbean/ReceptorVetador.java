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
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class ReceptorVetador implements VetoableChangeListener{

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException{
        //Si el pvp es inferior a 1 ho impedim llançant una excepció
        if((float)evt.getNewValue()<1){
            System.out.println("Impedit el canvi a la propietat "+evt.getPropertyName());
            System.out.println("Motiu: nou valor "+evt.getNewValue()+" es menor o igual a 0");           
            throw new PropertyVetoException("error", evt);
        }
        else{
            System.out.println("Canvi a la propietat "+evt.getPropertyName());
            System.out.println("Valor anterior: "+evt.getOldValue());
            System.out.println("Valor actual: "+evt.getNewValue());    
        }
    }    
    
}
