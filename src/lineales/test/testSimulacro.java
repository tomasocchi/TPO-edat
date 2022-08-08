/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.test;

import lineales.dinamica.Lista;

/**
 *
 * @author Tomas
 */
public class testSimulacro {

    public static void main(String[] args) {
        Lista unaLista = new Lista();
        unaLista.insertar('b', 1);
        unaLista.insertar('c', 2);
        unaLista.insertar('b', 3);
        unaLista.insertar('b', 4);
        
       
        unaLista.eliminarApariciones('b');
        System.out.println(unaLista.toString());
    }
}
