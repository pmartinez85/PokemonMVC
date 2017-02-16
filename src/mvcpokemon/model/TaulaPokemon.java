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

public class TaulaPokemon {

    private int _1_id;
    private String _2_nom;
    private String _3_tipus;
    private Array _4_atac;

    public TaulaPokemon(int _1_id, String _2_nom, String _3_tipus, Array _4_atac) {
        this._1_id = _1_id;
        this._2_nom = _2_nom;
        this._3_tipus = _3_tipus;
        this._4_atac =  _4_atac;

    }


    public int get1_id() {
        return _1_id;
    }

    public void set1_id(int _1_id) {
        this._1_id = _1_id;
    }

    public TaulaPokemon(String _2_nom, String _3_tipus, Array _4_atac) {
        this._2_nom = _2_nom;
        this._3_tipus = _3_tipus;
        this._4_atac = _4_atac;

    }

    public String get2_nom() {
        return _2_nom;
    }

    public void set2_nom(String _2_nom) {
        this._2_nom = _2_nom;
    }

    public String get3_tipus() {
        return _3_tipus;
    }

    public void set3_tipus(String _3_tipus) {
        this._3_tipus = _3_tipus;
    }
    
    public Array get4_atac() {
        return _4_atac;
    }

    public void set4_atac(Array _4_atac) {
        this._4_atac = _4_atac;
    }
 
}
