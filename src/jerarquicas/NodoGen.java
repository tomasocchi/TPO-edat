/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas;

/**
 *
 * @author Tomas
 */
public class NodoGen {

//Atributos 
private Object elem; 
private NodoGen hijoIzquierdo; 
private NodoGen hermanoDerecho; 

//Constructor
public NodoGen(Object el, NodoGen iz, NodoGen der){
    this.elem = el; 
    this.hijoIzquierdo = iz; 
    this.hermanoDerecho = der; 
}

//Observadoras
public Object getElem(){
    return this.elem; 
}
public NodoGen getHijoIzquierdo(){
    return this.hijoIzquierdo; 
}
public NodoGen getHermanoDerecho(){
    return this.hermanoDerecho; 
}

//Modificadoras
public void setElem(Object el){
    this.elem = el; 
}
public void setHijoIzquierdo(NodoGen iz){
    this.hijoIzquierdo = iz; 
}
public void setHermanoDerecho(NodoGen der){
    this.hermanoDerecho = der; 
}
}
