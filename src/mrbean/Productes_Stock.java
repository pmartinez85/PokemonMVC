/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mrbean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;

/**
 *
 * @author pedro
 */

public class Productes_Stock implements Serializable{

    private String numSerie;
    private String denominacio;
    private String fabricant;
    private int stockActual;
    private int stockMinim = 2;
    private float pvp = 50;
    private int anyFabricacio;
    private String[] caract;

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getDenominacio() {
        return denominacio;
    }

    public void setDenominacio(String denominacio) {
        this.denominacio = denominacio;
    }

    public String getFabricant() {
        return fabricant;
    }

    public void setFabricant(String fabricant) {
        this.fabricant = fabricant;
    }

    public int getStockActual() {
        return stockActual;
    }

    public int getStockMinim() {
        return stockMinim;
    }

    public void setStockMinim(int stockMinim) {
        this.stockMinim = stockMinim;
    }

    public float getPvp() {
        return pvp;
    }

    public void setPvp(float pvp) {
        this.pvp = pvp;
    }

    public int getAnyFabricacio() {
        return anyFabricacio;
    }

    public void setAnyFabricacio(int anyFabricacio) {
        this.anyFabricacio = anyFabricacio;
    }

    public String[] getCaract() {
        return caract;
    }

    public void setCaract(String[] caract) {
        this.caract = caract;
    }
    
    public String getCaract(int index) {
        return caract[index];
    }
    
    public void setCaract(int index, String valor) {
        this.caract[index] = valor;
    }
    
    //String que té com a valor el nom de la propietat lligada
    
    public static final String PROP_SAMPLE_PROPERTY = "stockActual";
    
    private PropertyChangeSupport propertySupport;
    

    public void setStockActual(Integer value1) {
        stockActual = value1;
        if (this.stockMinim > this.stockActual){
            propertySupport.firePropertyChange(PROP_SAMPLE_PROPERTY, stockMinim,stockActual);
        }
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
    
    //String que té com a valor el nom de la propietat restringida 
    public static final String PROP_QUANTITAT = "pvp";
  
    
    private VetoableChangeSupport vetoableSupport;
    
    public Productes_Stock() {
        propertySupport = new PropertyChangeSupport(this);
        vetoableSupport = new VetoableChangeSupport(this);
    }
    
    public void setpvp(float nouValor) throws PropertyVetoException {
        float vellValor = pvp;
        //Abans de fer el canvi activem els vetadors
        vetoableSupport.fireVetoableChange(PROP_QUANTITAT, vellValor, nouValor);
        this.pvp = nouValor;
    }

    public void addVetoableChangeListener(VetoableChangeListener listener) {
        vetoableSupport.addVetoableChangeListener(listener);
    }


    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        vetoableSupport.removeVetoableChangeListener(listener);
    }    

}

