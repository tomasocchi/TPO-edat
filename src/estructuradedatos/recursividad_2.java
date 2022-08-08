/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuradedatos;

import java.util.Scanner;

/**
 *
 * @author Tomas
 */
public class recursividad_2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arreglo;
        int cant;
        System.out.println("Ingrese la cantidad de celdas que posee el arreglo");
        cant = sc.nextInt();
        arreglo = new int[cant];
        cargarArreglo(arreglo, cant);
        double g = prom(arreglo, 0);
        System.out.println(g/arreglo.length);
    }

    public static void cargarArreglo(int[] arr, int cant) {
        Scanner sc = new Scanner(System.in);
        int i, n;
        for (i = 0; i < cant; i++) {
            System.out.println("Ingrese el valor de la pos " + (i + 1));
            n = sc.nextInt();
            arr[i] = n;
        }
    }

    public static double prom(int[] arr, int pos) {
        double res = 0, aux = 0;
            if (pos <= arr.length - 1) {
                res = prom(arr, pos + 1) + arr[pos];
            }                  
        return res;
    }
}
