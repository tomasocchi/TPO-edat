/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas.test;
import jerarquicas.ArbolHeap; 
/**
 *
 * @author Tomas
 */
public class TestingHeap {
    public static void main(String[] args){
        ArbolHeap unArbol = new ArbolHeap(); 
        unArbol.insertar(1); 
        unArbol.insertar(2); 
        unArbol.insertar(3); 
        unArbol.insertar(4); 
        unArbol.insertar(5);
        unArbol.eliminarCima();
        System.out.println(unArbol.toString());
      
        
    }
}
