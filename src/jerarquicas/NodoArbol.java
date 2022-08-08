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
public class NodoArbol {
    
 //Atributos 
    private Object elem; 
    private NodoArbol izquierdo; 
    private NodoArbol derecho; 
    
 
//Constructor 
    public NodoArbol(Object tipoElem, NodoArbol iz, NodoArbol der){
        elem = tipoElem; 
        izquierdo = iz; 
        derecho = der; 
    }
    
//Observadoras 
    public Object getElem() {
        return this.elem; 
    }
    public NodoArbol getIzquierdo(){
        return this.izquierdo; 
    }
    public NodoArbol getDerecho(){
        return this.derecho; 
    }
    
//Modificadoras 
    public void setElem(Object tipoElem){
        this.elem = tipoElem; 
    }    
    public void setIzquierdo(NodoArbol iz){
        this.izquierdo = iz; 
    }
    public void setDerecho(NodoArbol der){
        this.derecho = der; 
    }
}


