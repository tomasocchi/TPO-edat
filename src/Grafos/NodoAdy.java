/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos;

/**
 *
 * @author Tomas
 */
public class NodoAdy {
    private NodoVert vertice; 
    private NodoAdy sigAdyacente; 
    private Object etiqueta; 
    
    public NodoAdy(NodoVert vert, NodoAdy sigAdy, Object et){
        this.etiqueta = et;
        this.sigAdyacente = sigAdy;
        this.vertice = vert;
    }
    
    public NodoVert getVertice(){
        return this.vertice;
    }
    public NodoAdy getSigAdyacente(){
        return this.sigAdyacente;
    }
    public Object getEtiqueta(){
        return this.etiqueta; 
    }
    public void setVertice(NodoVert v){
        this.vertice = v;
    }
    public void setSigAdyacente(NodoAdy ad){
        this.sigAdyacente = ad;
    }
    public void setEtiqueta(Object et){
        this.etiqueta = et; 
    }
}
