/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos;

import TPO.Estacion;
import Grafos.GrafoEtiq;
import lineales.dinamica.Lista;
import java.util.Scanner;

/**
 *
 * @author Tomas
 */
public class testGrafo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GrafoEtiq g = new GrafoEtiq();
        Lista l = new Lista();
        g.insertarVertice(1); 
        System.out.println(g.existeArco(1, 1));
    }
          
}
