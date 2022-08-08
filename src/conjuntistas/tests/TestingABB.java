/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas.tests;
import conjuntistas.dinamicas.ArbolBB;
import lineales.dinamica.Lista;
/**
 *
 * @author Tomas
 */
public class TestingABB {
    public static void main(String[] args){
        ArbolBB unArbol = new ArbolBB();
        unArbol.insertar(10);
        unArbol.insertar(8);
        unArbol.insertar(15);
        unArbol.insertar(6);
        unArbol.insertar(9);
        unArbol.insertar(5);
        unArbol.insertar(7);
        unArbol.insertar(13);
        unArbol.insertar(14); 
        System.out.println(unArbol.toString());
        Lista l = new Lista();
        l = unArbol.listarRango(3, 12);
        System.out.println(l.toString());
    }
}
