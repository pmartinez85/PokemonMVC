/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mvcpokemon.model;


/**
 *
 * @author pedro
 */

public class TaulaPokemon {

    private int _1_pkm_id;
    private String _2_pkm_nom;
    private String _3_pkm_tipus;
    private String[] _4_pkm_atac;

    public TaulaPokemon(int pkm_id, String pkm_nom, String pkm_tipus, String[] pkm_atac) {
        this._1_pkm_id = pkm_id;
        this._2_pkm_nom = pkm_nom;
        this._3_pkm_tipus = pkm_tipus;
        this._4_pkm_atac = pkm_atac;

    }

    public int get1_pkm_id() {
        return _1_pkm_id;
    }

    public void set1_pkm_id(int id) {
        this._1_pkm_id = id;
    }

    public TaulaPokemon(String pkm_nom, String pkm_tipus, String[] pkm_atac) {
        this._2_pkm_nom = pkm_nom;
        this._3_pkm_tipus = pkm_tipus;
        this._4_pkm_atac = pkm_atac;

    }

    public String get2_pkm_nom() {
        return _2_pkm_nom;
    }

    public void set2_pkm_nom(String nom) {
        this._2_pkm_nom = nom;
    }

    public String get3_pkm_tipus() {
        return _3_pkm_tipus;
    }

    public void set3_pkm_tipus(String tipus) {
        this._3_pkm_tipus = tipus;
    }

    public String[] get4_pkm_atac() {
        return _4_pkm_atac;
    }

    public void set4_pkm_atac(String[] atac) {
        this._4_pkm_atac = atac;
    }

}
