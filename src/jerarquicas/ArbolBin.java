/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas;

import lineales.dinamica.Lista;
import lineales.dinamica.Cola;

/**
 *
 * @author Tomas
 */
public class ArbolBin {

//Atributo
    private NodoArbol raiz;

//Constructor
    public ArbolBin() {
        this.raiz = null;
    }

//Modificadoras 
    public boolean insertar(Object elemNuevo, Object elemPadre, char lugar) {
        boolean exito = true;

        if (this.raiz == null) {
            //Si el arbol esta vacio, pone elem nuevo en la raiz 
            this.raiz = new NodoArbol(elemNuevo, null, null);
        } else {
            //Si el arbol no esta vacio, busca al padre
            NodoArbol nPadre = obtenerNodo(this.raiz, elemPadre);

            //Si el padre exito y lugar no esta ocupado lo pone, sino da error
            if (nPadre != null) {
                if (lugar == 'I' && nPadre.getIzquierdo() == null) {
                    nPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else if (lugar == 'D' && nPadre.getDerecho() == null) {
                    nPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                } else {
                    exito = false;
                }

            } else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoArbol obtenerNodo(NodoArbol n, Object buscado) {
        // Metodo PRIVADO que busca un elemento y devuelve el nodo que
        // lo contiene. Si no se encuentra buscado devuelve null 
        NodoArbol resultado = null;
        if (n != null) {
            if (n.getElem().equals(buscado)) {
                //Si el buscado es n, lo devuelve
                resultado = n;
            } else {
                // no es el buscado: busca primero en el HI
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
                // Si no lo encontro en el HI, busca en HD
                if (resultado == null) {
                    resultado = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    public boolean esVacio() {
        // Metodo que verifica si un arbol está vacío, analizando en valor de su raiz. 

        boolean exito = false;
        if (this.raiz == null) {
            exito = true;
        }
        return exito;
    }

    public int nivel(Object elemento) {
        int niv = 0;
        NodoArbol busca = obtenerNodo(this.raiz, elemento);
        if (busca != null) {
            niv = obtenerNivel(this.raiz, elemento, busca);
        } else {
            niv = -1;
        }
        return niv;
    }

    private int obtenerNivel(NodoArbol n, Object buscado, NodoArbol n2) {
        int busca = 0;
        int aux = 0;
        if (n == n2) {
            busca = aux;
        } else if (n != null) {
            NodoArbol iz = n.getIzquierdo();
            NodoArbol der = n.getDerecho();
            if (iz == n2 || der == n2) {
                n = n2;
                busca = aux + 1;
            } else {
                aux++;
                busca = aux + obtenerNivel(iz, buscado, n2);
                if (n == null) {
                    busca = aux + obtenerNivel(der, buscado, n2);
                }
            }
        }
        return busca;
    }

    public Object padre(Object elemento) {
        //Metodo que devuelve cual es el nodo padre del elemento igresado por el usuario
        Object padre = null;
        NodoArbol hijo = obtenerNodo(this.raiz, elemento);
        if (hijo != null) {
            NodoArbol pax = buscaPadre(this.raiz, hijo);
            if (pax != null) {
                padre = pax.getElem();
            }
        }
        return padre;
    }

    private NodoArbol buscaPadre(NodoArbol n, NodoArbol hijo) {
        NodoArbol padre = null;

        if (n != null) {
            NodoArbol iz = n.getIzquierdo();
            NodoArbol der = n.getDerecho();
            if (iz == hijo || der == hijo) {
                padre = n;
            } else {
                padre = buscaPadre(iz, hijo);
                if (padre == null) {
                    padre = buscaPadre(der, hijo);
                }
            }
        }
        return padre;
    }

    public String toString() {
        String str = "";
        if (this.raiz == null) {
            str = "Arbol Vacío";
        } else {
            str = llamadoStr(this.raiz);
        }
        return str;
    }

    private String llamadoStr(NodoArbol n) {
        String cad = "";

        if (n != null) {
            cad = n.getElem().toString();

            NodoArbol iz = n.getIzquierdo();
            if (iz != null) {
                cad = cad + "  H.I:" + iz.getElem().toString();
            } else {
                cad = cad + "  H.I:-";
            }
            NodoArbol der = n.getDerecho();
            if (der != null) {
                cad = cad + "  H.D:" + der.getElem().toString();
            } else {
                cad = cad + "  H.D:-";
            }
            cad = "\n" + cad + llamadoStr(iz) + llamadoStr(der);
        }
        return cad;
    }

    public int altura() {
        // método que retorna la altura total del árbol, si este es vacío retornará -1
        int total;
        if (this.raiz == null) {
            total = -1;
        } else {
            total = calculaAltura(this.raiz);
        }
        return total;
    }

    private int calculaAltura(NodoArbol n) {
        // método recursivo PRIVADO ya que su parámetro es de tipo NodoArbol
        // calcula la altura que posee el arbol
        int it = 0;
        if (n != null) {
            // busca si el nodo actual posee hijos
            NodoArbol iz = n.getIzquierdo();
            NodoArbol der = n.getDerecho();
            // si existe algun hijo, aumenta la altura del arbol
            if (iz != null || der != null) {
                it = it + 1;
            }
            // recorre a sus hijos en preorden
            it = it + calculaAltura(iz) + calculaAltura(der);
        }
        return it;
}

    public Lista listarPreorden() {
        // retorna una lista con los elementos del árbol en PREORDEN
        Lista lis = new Lista();
        listarPreordenAux(this.raiz, lis);
        return lis;
    }

    private void listarPreordenAux(NodoArbol nodo, Lista lis) {
        // método recursivo PRIVADO porque su parámetro es de tipo NodoArbol
        if (nodo != null) {
            // visita el elemento en el nodo
            lis.insertar(nodo.getElem(), lis.longitud() + 1);

            // recorre a sus hijos em preorden
            listarPreordenAux(nodo.getIzquierdo(), lis);
            listarPreordenAux(nodo.getDerecho(), lis);
        }
    }

    public Lista listarInorden() {
        // Retorna una lista con los elementos del árbol en INORDEN 
        Lista lis = new Lista();
        listarInordenAux(this.raiz, lis);
        return lis;
    }

    private void listarInordenAux(NodoArbol n, Lista lis) {
        // método recursivo PRIVADO ya que su parámetro es de tipo NodoArbol
        if (n != null) {
            listarInordenAux(n.getIzquierdo(), lis);
            lis.insertar(n.getElem(), lis.longitud() + 1);
            listarInordenAux(n.getDerecho(), lis);
        }
    }

    public Lista listarPosorden() {
        // Retorna una lista con los elementos del árbol en POSORDEN 
        Lista lis = new Lista();
        listarPosordenAux(this.raiz, lis);
        return lis;
    }

    private void listarPosordenAux(NodoArbol n, Lista lis) {
        // Método recursivo PRIVADO ya que si parámetro es de tipo NodoArbol
        if (n != null) {
            // Primero recorre el hijo izquierdo hasta el nodo hoja, luego visita si tiene el hijo derecho
            listarPosordenAux(n.getIzquierdo(), lis);
            listarPosordenAux(n.getDerecho(), lis);
            lis.insertar(n.getElem(), lis.longitud() + 1);
        }
    }

    public Lista listarNiveles() {
        // Retorna una lista con los elementos del árbol por NIVEL
        Lista lis = new Lista();
        listarNivelesAux(lis);
        return lis;
    }

    private void listarNivelesAux(Lista lis) {
        // Método recursivo PRIVADO ya que si parámetro es de tipo NodoArbol
        Cola q = new Cola();
        NodoArbol actual;
        q.poner(this.raiz);
        //Mientras la cola no sea vacia, va a ir copiando los nodos 
        while (q.obtenerFrente() != null) {
            actual = (NodoArbol) q.obtenerFrente();
            q.sacar();
            //Inserta en la lista el valor del elemento que fue el frente de la cola
            lis.insertar(actual.getElem(), lis.longitud() + 1);

            //Si el nodo tiene hijos, los inserta en la cola 
            if (actual.getIzquierdo() != null) {
                q.poner(actual.getIzquierdo());
            }
            if (actual.getDerecho() != null) {
                q.poner(actual.getDerecho());
            }
        }
    }

    public ArbolBin clone() {
        //Metodo que devuelve una copia del arbol original
        ArbolBin clon = new ArbolBin();
        if (this.raiz != null) {
            clon.raiz = clonRec(this.raiz);
        }
        return clon;
    }

    private NodoArbol clonRec(NodoArbol n) {
        NodoArbol nuevo = null;
        if (n != null) {
            nuevo = n;
            if (n.getIzquierdo() != null) {
                nuevo.setIzquierdo(n.getIzquierdo());
            }
            if (n.getDerecho() != null) {
                nuevo.setDerecho(n.getDerecho());
            }
        }
        return nuevo;
    }

    public void vaciar() {
        this.raiz = null;
    }
    
    //------------------------------------------------------------------------------------------------------------------------------------//
    

//Metodo simulacro
    public Lista frontera() {
        Lista lis = new Lista();
        if (this.raiz != null) {
            fronteraAux(lis, this.raiz, 1);
        }
        return lis;
    }

    private void fronteraAux(Lista lis, NodoArbol n, int pos) {
        if (n != null) {
            if (n.getIzquierdo() == null && n.getDerecho() == null) {
                lis.insertar(n.getElem(), pos);
            }
            fronteraAux(lis, n.getDerecho(), pos);
            fronteraAux(lis, n.getIzquierdo(), pos);
        }
    }
    
      public ArbolBin cloneInv() {
        //Metodo que devuelve una copia del arbol original
        ArbolBin clon = new ArbolBin();
        clon.raiz = clonRecInv(this.raiz);
        
        return clon;
    }

    private NodoArbol clonRecInv(NodoArbol n) {
        NodoArbol nuevo = null;
        if (n != null) {
            nuevo = new NodoArbol(n.getElem(),null,null);
            nuevo.setIzquierdo(clonRecInv(n.getDerecho()));
            nuevo.setDerecho(clonRecInv(n.getIzquierdo()));
     
        }
        return nuevo;
    }
    
    public boolean equals(ArbolBin otro){
        return equalsAux(this.raiz,otro.raiz);
    }
    private boolean equalsAux(NodoArbol n, NodoArbol n2){
        boolean res = false;
        
        if(n != null && n2 != null ){
            
            if(  n2.getIzquierdo()== null && n2.getDerecho()== null && n.getIzquierdo() == null && n.getDerecho() == null && n.getElem() == n2.getElem()){
                res = true;
            }else{
                if(n.getElem() == n2.getElem()){
                    res = equalsAux(n.getIzquierdo(),n2.getIzquierdo());
                }
                if( res == true ){
                    res = equalsAux(n.getDerecho(),n2.getDerecho());
                }
                                
            }
            
            //Excepcion nodo con un solo hijo
        }else if(n == null && n2==null){
            res = true;
        }

        return res;
    }
}

