/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas.dinamicas;

/**
 *
 * @author Tomas
 */
public class NodoAVLDicc {
    
    private Comparable clave; 
    private Object dato; 
    private int altura; 
    private NodoAVLDicc hijoIzquierdo; 
    private NodoAVLDicc hijoDerecho; 
    
    public NodoAVLDicc(Comparable c,Object dat, NodoAVLDicc iz, NodoAVLDicc der) {
        clave = c; 
        dato = dat;
        hijoIzquierdo = iz;
        hijoDerecho = der;
        altura = 0;
    }
     
    public Comparable getClave() {
        return this.clave;
    }
    
     public NodoAVLDicc getHijoIzquierdo() {
        return this.hijoIzquierdo;
    }

    public NodoAVLDicc getHijoDerecho() {
        return this.hijoDerecho;
    }
     public int getAltura() {
        return this.altura;
    }
     public Object getDato(){
         return this.dato; 
     }
     
     public void setHijoIzquierdo(NodoAVLDicc iz) {
        this.hijoIzquierdo = iz;
    }

    public void setHijoDerecho(NodoAVLDicc der) {
        this.hijoDerecho = der;
    } 
    
    public void setDato(Object tipoElem) {
        this.dato = tipoElem;
    }
   
     public void recalcularAltura(){
        //tiene al menos un hijo por lo cual vamos a comparar sus alturas 
        int alturaDerecho;
        int alturaIzquierdo; 
        
        if(this.hijoDerecho != null){
            alturaDerecho = this.hijoDerecho.getAltura();
        }else{
            alturaDerecho = -1; 
        }
        if(this.hijoIzquierdo != null){
            alturaIzquierdo = this.hijoIzquierdo.getAltura();
        }else{
            alturaIzquierdo = -1;
        }
        this.altura = Math.max(alturaIzquierdo, alturaDerecho) + 1;
 
    }

   
     
}
