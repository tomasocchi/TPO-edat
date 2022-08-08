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
public class NodoAVL {

    //Atributos 
    private Comparable elem;
    private NodoAVL izquierdo;
    private NodoAVL derecho;
    private int altura;

//Constructor 
    public NodoAVL(Comparable tipoElem, NodoAVL iz, NodoAVL der) {
        elem = tipoElem;
        izquierdo = iz;
        derecho = der;
        altura = 0;
    }

//Observadoras 
    public Comparable getElem() {
        return this.elem;
    }

    public NodoAVL getIzquierdo() {
        return this.izquierdo;
    }

    public NodoAVL getDerecho() {
        return this.derecho;
    }

    public int getAltura() {
        return this.altura;
    }

//Modificadoras 
    public void setElem(Comparable tipoElem) {
        this.elem = tipoElem;
    }

    public void setIzquierdo(NodoAVL iz) {
        this.izquierdo = iz;
    }

    public void setDerecho(NodoAVL der) {
        this.derecho = der;
    }

    public void recalcularAltura(){
        //tiene al menos un hijo por lo cual vamos a comparar sus alturas 
        int alturaDerecho;
        int alturaIzquierdo; 
        
        if(this.derecho != null){
            alturaDerecho = this.derecho.getAltura();
        }else{
            alturaDerecho = -1; 
        }
        if(this.izquierdo != null){
            alturaIzquierdo = this.izquierdo.getAltura();
        }else{
            alturaIzquierdo = -1;
        }
        this.altura = Math.max(alturaIzquierdo, alturaDerecho) + 1;

    }
}
