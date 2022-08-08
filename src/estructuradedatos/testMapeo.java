/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuradedatos;

import lineales.dinamica.Lista;
import estructuradedatos.MapeoAMuchos;

/**
 *
 * @author Tomas
 */
public class testMapeo {

    public static void main(String[] args) {
        MapeoAMuchos mapeo = new MapeoAMuchos();
        String rango = "neuquen";
        String dom = "33";
        mapeo.asociar(dom, rango);
        rango = "salta";
        dom = "6";
        mapeo.asociar(dom, rango);
        rango = "cordoba";
        dom = "33";
        mapeo.asociar(dom, rango);
        System.out.println(mapeo.toString());    
        Lista l = mapeo.obtenerConjuntoRango(); 
        System.out.println(l.toString());
    }

}
