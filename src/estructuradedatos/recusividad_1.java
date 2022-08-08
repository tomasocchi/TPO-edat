/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuradedatos;

import java.util.Scanner;

public class recusividad_1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String f, c;
        int n;
        System.out.println("Inserte una frase");
        f = sc.nextLine();
        sc.nextLine();
        invCadena(f,f.length()-1);

    }

    public static int buscaLetra(String f, int pos) {
        int res;
        if (pos >= f.length()) {
            res = -1;
        } else {
            if (f.charAt(pos) >= 97 && f.charAt(pos) <= 122) {
                res = pos;
            } else {
                res = buscaLetra(f, pos + 1);
            }
        }
        return res;
    }

    public static int buscaLetra2(String f, int pos) {
        int res;
        if (pos <= 0) {
            res = -1;
        } else {
            if (f.charAt(pos) >= 97 && f.charAt(pos) <= 122) {
                res = pos;
            } else {
                res = buscaLetra(f, pos - 1);
            }
        }
        return res;
    }

    public static void invCadena(String cad, int pos){
    if(pos >= 0){
        System.out.print(cad.charAt(pos));
        invCadena(cad,pos-1);
    }                
}
}
