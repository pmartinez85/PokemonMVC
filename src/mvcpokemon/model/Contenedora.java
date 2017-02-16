/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcpokemon.model;

import java.sql.Array;

/**
 *
 * @author pedro
 */
public class Contenedora {
    
    Array atac = null;

    @Override
    public String toString() {
        return "Contenedora{" + "atac=" + atac + '}';
    }

    public Array getAtacs() {
        return atac;
    }

    public Array getAtac() {
        return atac;
    }

    public void setAtac(Array atac) {
        this.atac = atac;
    }


    }
    
