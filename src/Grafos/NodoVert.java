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
public class NodoVert {

    private Object elem;
    private NodoVert sigVertice;
    private NodoAdy primerAdy;

    public NodoVert(Object elem, NodoVert sig, NodoAdy ady) {
        this.elem = elem;
        this.primerAdy = ady;
        this.sigVertice = sig;
    }

    public Object getElem() {
        return this.elem;
    }

    public NodoVert getSigVertice() {
        return this.sigVertice;
    }

    public NodoAdy getPrimerAdy() {
        return this.primerAdy;
    }

    public void setElem(Object el) {
        this.elem = el;
    }

    public void setSigVert(NodoVert n) {
        this.sigVertice = n;
    }

    public void setPrimerAdy(NodoAdy ad) {
        this.primerAdy = ad;
    }
}
