/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mvcpokemon;

import mvcpokemon.view.VistaPokemons;
import mvcpokemon.model.Model;
import mvcpokemon.controller.Controlador;

/**
 *
 * @author pedro
 */
public class pokemonMVC {
    
    static Model model=new Model();
    static VistaPokemons vista=new VistaPokemons();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Controlador(model, vista);
    }
    
}
