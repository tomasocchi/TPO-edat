/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas.tests;
import conjuntistas.dinamicas.*;

/**
 *
 * @author Tomas
 */
public class TestingAVL {
    
    public static void main(String[] args) {
        DiccionarioAVL a = new DiccionarioAVL();
        a.insertar(33,null); 
        a.insertar(40,null);
        a.insertar(25,null);
        a.insertar(34, null); 
        System.out.println(a.toString());
        a.eliminar(33);
        System.out.println(a.toString());
    }
}
