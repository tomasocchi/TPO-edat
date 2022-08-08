/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas.test;

import jerarquicas.ArbolGen;
import lineales.dinamica.Lista;
import conjuntistas.dinamicas.ArbolBB;

/**
 *
 * @author Tomas
 */
public class testParcial {

    public static void main(String[] args) {
        ArbolBB a = new ArbolBB();
        a.insertar(20);
        a.insertar(22);
        a.insertar(28);
        a.insertar(23);
        a.insertar(17);
        a.insertar(19);
        a.insertar(5);

        ArbolGen b = new ArbolGen();
        b.insertar(20, null);
        b.insertar(13, 20);
        b.insertar(15, 13);
        b.insertar(12, 13);
        b.insertar(54, 20);
        b.insertar(11, 54);
        b.insertar(27, 54);
        b.insertar(4, 54);
        b.insertar(17, 27);

        Lista l = b.listarHastaNivel(2);
        System.out.println(l.toString());

    }
}
