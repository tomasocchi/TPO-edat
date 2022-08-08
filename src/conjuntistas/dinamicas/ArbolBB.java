 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas.dinamicas;

import lineales.dinamica.Lista;
import jerarquicas.NodoArbol;

/**
 *
 * @author Tomas
 */
public class ArbolBB {

    //Atributo
    private NodoArbol raiz;

//Constructor
    public ArbolBB() {
        this.raiz = null;
    }

//Metodos
    public boolean insertar(Comparable elemento) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoArbol(elemento, null, null);
        } else {
            exito = insertarAux(this.raiz, elemento);
        }
        return exito;
    }

    private boolean insertarAux(NodoArbol n, Comparable elemento) {
        // precondicion: n no es nulo
        boolean exito = true;

        if ((elemento.compareTo(n.getElem()) == 0)) {
            // reportar error: n no es nulo
            exito = false;
        } else if (elemento.compareTo(n.getElem()) < 0) {
            // elemento es menor que n.getElem()
            // si tiene HI baja a la izquierda, sino agrega elemento
            if (n.getIzquierdo() != null) {
                exito = insertarAux(n.getIzquierdo(), elemento);
            } else {
                n.setIzquierdo(new NodoArbol(elemento, null, null));
            }
        } else {
            // elemento es mayor que n.getElem()
            // si tiene HD baja a la derecha, sino agrega elemento
            if (n.getDerecho() != null) {
                insertarAux(n.getDerecho(), elemento);
            } else {
                n.setDerecho(new NodoArbol(elemento, null, null));
            }
        }
        return exito;
    }

    public boolean eliminar(Comparable elemento) {
        boolean exito = true;
        if (this.raiz != null) {
            exito = eliminarAux(elemento, this.raiz, null);
        } else {
            exito = false;
        }
        return exito;
    }

    private boolean eliminarAux(Comparable elem, NodoArbol n, NodoArbol padre) {
        boolean exito = false;

        if (elem.compareTo(n.getElem()) == 0) {
            exito = true;
            // Caso 3 
            if (n.getIzquierdo() != null && n.getDerecho() != null) {
                //Metodo para econtrar el elemento minimo de los mayores que n
                NodoArbol m = mayor(n);
                n.setElem(m.getElem());
                if (m.getDerecho() != null) {
                    //caso comun elemento con descendientes  
                    padre = buscaPadre(n, m);
                    padre.setIzquierdo(m.getDerecho());
                } else {
                    // caso especial
                    // m es hijo derecho de n
                    if (m == n.getDerecho()) {
                        n.setDerecho(m.getDerecho());
                    }
                }
                // Caso 1
            } else if (n.getIzquierdo() == null && n.getDerecho() == null) {
                if (n.getDerecho() == null) {
                    padre.setDerecho(null);
                } else {
                    padre.setIzquierdo(null);
                }
                exito = true;
                // Caso 2
            } else {
                if (elem.compareTo(padre.getElem()) < 0) {
                    if (n.getDerecho() != null) {
                        padre.setIzquierdo(n.getDerecho());
                    } else {
                        padre.setIzquierdo(n.getIzquierdo());
                    }
                } else {
                    if (n.getDerecho() != null) {
                        padre.setIzquierdo(n.getDerecho());
                    } else {
                        padre.setIzquierdo(n.getIzquierdo());

                    }
                }
            }

        } else if (elem.compareTo(n.getElem()) < 0) {
            if (n.getIzquierdo() != null) {
                exito = eliminarAux(elem, n.getIzquierdo(), n);
            }
        } else {
            if (n.getDerecho() != null) {
                exito = eliminarAux(elem, n.getDerecho(), n);
            }
        }

        return exito;
    }

    private NodoArbol mayor(NodoArbol n) {
        NodoArbol aux;
        // Primer recorrido: busco hijo derecho de n
        aux = n.getDerecho();
        while (aux.getIzquierdo() != null) {
            aux = aux.getIzquierdo();
        }
        return aux;
    }

    private NodoArbol buscaPadre(NodoArbol n, NodoArbol hijo) {
        NodoArbol p;
        p = n.getDerecho();
        while (p.getIzquierdo() != hijo) {
            p = p.getIzquierdo();
        }
        return p;
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

    public boolean pertenece(Comparable elem) {
        boolean exito = false;
        if (this.raiz != null) {
            exito = perteneceAux(this.raiz, elem);
        }
        return exito;
    }

    private boolean perteneceAux(NodoArbol n, Comparable el) {
        boolean exito = false;
        if (el.compareTo(n.getElem()) == 0) {
            exito = true;
        } else if (el.compareTo(n.getElem()) < 0) {
            if (n.getIzquierdo() != null) {
                exito = perteneceAux(n.getIzquierdo(), el);
            }
        } else {
            if (n.getDerecho() != null) {
                exito = perteneceAux(n.getDerecho(), el);
            }
        }
        return exito;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public Comparable minimoElem() {
        Comparable el;
        el = minimoElemAux();
        return el;
    }

    private Comparable minimoElemAux() {
        Comparable el = null;
        NodoArbol aux = this.raiz;
        while (aux.getIzquierdo() != null) {
            aux = aux.getIzquierdo();
        }
        el = (Comparable) aux.getElem();
        return el;
    }

    public Comparable maximoElem() {
        Comparable el;
        el = maximoElemAux();
        return el;
    }

    private Comparable maximoElemAux() {
        Comparable el = null;
        NodoArbol aux = this.raiz;
        while (aux.getDerecho() != null) {
            aux = aux.getDerecho();
        }
        el = (Comparable) aux.getElem();
        return el;
    }

    public Lista listar() {
        Lista l = new Lista();
        if (this.raiz != null) {
            listarAuxMin(this.raiz, l);
        }
        return l;
    }

    private void listarAuxMin(NodoArbol n, Lista l) {
        if (n != null) {
            listarAuxMin(n.getIzquierdo(), l);
            l.insertar(n.getElem(), l.longitud() + 1);
            listarAuxMin(n.getDerecho(), l);
        }
    }

    private void listarAuxMax(NodoArbol n, Lista l) {
        if (n != null) {
            listarAuxMax(n.getDerecho(), l);
            l.insertar(n.getElem(), l.longitud() + 1);
            listarAuxMax(n.getIzquierdo(), l);
        }
    }

    public Lista listarRango(Comparable elMinimo, Comparable elMax) {
        Lista l = new Lista();
        if (this.raiz != null) {
            listarRangoAux(elMinimo, elMax, this.raiz, l);
        }
        return l;
    }

    private void listarRangoAux(Comparable elMin, Comparable elMax, NodoArbol n, Lista l) {
        if (n != null) {
            if (elMin.compareTo(n.getElem()) <= 0 && n.getIzquierdo() != null) {
                listarRangoAux(elMin, elMax, n.getIzquierdo(), l);
            }
            if (elMin.compareTo(n.getElem()) <= 0 && elMax.compareTo(n.getElem()) >= 0) {
                l.insertar(n.getElem(), l.longitud() + 1);
            }
            if (elMax.compareTo(n.getElem()) >= 0 && n.getDerecho() != null) {
                listarRangoAux(elMin, elMax, n.getDerecho(), l);
            }
        }
    }

    //ejercicios p parcial
    public ArbolBB clonarParteInvertida(Comparable elem) {
        ArbolBB clon = new ArbolBB();
        clonarParteInvertidaAux(this.raiz, elem, clon);
        return clon;
    }

    private void clonarParteInvertidaAux(NodoArbol n, Comparable elem, ArbolBB clon) {

        if (elem.compareTo(n.getElem()) == 0) {
            NodoArbol c = clonarInv(n);
            clon.raiz = c;
        } else if (elem.compareTo(n.getElem()) < 0) {

            if (n.getIzquierdo() != null) {
                clonarParteInvertidaAux(n.getIzquierdo(), elem, clon);
            }
        } else {
            if (n.getDerecho() != null) {
                clonarParteInvertidaAux(n.getDerecho(), elem, clon);
            }
        }

    }

    private NodoArbol clonarInv(NodoArbol n) {
        NodoArbol nuevo = null;
        if (n != null) {
            nuevo = new NodoArbol(n.getElem(), null, null);
            nuevo.setIzquierdo(clonarInv(n.getDerecho()));
            nuevo.setDerecho(clonarInv(n.getIzquierdo()));
        }
        return nuevo;
    }

    public Lista listarMayorA(Comparable elem) {
        Lista l = new Lista();
        if (this.raiz != null) {
            listarMayorAaux(this.raiz, l, elem);
        }
        return l;
    }

    private void listarMayorAaux(NodoArbol n, Lista l, Comparable el) {
        
        if (el.compareTo(n.getElem()) <= 0) {
            
            if (n.getDerecho() != null) {
                System.out.println(n.getElem().toString());
                listarMayorAaux(n.getDerecho(), l, el);
            }
            if (el.compareTo(n.getElem()) <= 0) {
                l.insertar(n.getElem(), l.longitud() + 1);
            }
            if (n.getIzquierdo() != null) {
                listarMayorAaux(n.getIzquierdo(), l, el);
            }
        } else {
            
            if (el.compareTo(n.getDerecho().getElem()) <= 0) {
                listarMayorAaux(n.getDerecho(), l, el);
            }
        }

    }

    public Lista listarMayoresQue(Comparable valor, Comparable elem) {
        Lista l = new Lista();
        if (this.raiz != null) {
            listarMayoresQueAux(l, this.raiz, valor, elem);
        }
        return l;
    }

    private void listarMayoresQueAux(Lista l, NodoArbol n, Comparable valor, Comparable elem) {
        if (n != null) {
            if (elem.compareTo(n.getElem()) == 0) {
                listarMayores(valor, n, l);
            } else if (elem.compareTo(n.getElem()) < 0) {
                if (n.getIzquierdo() != null) {
                    listarMayoresQueAux(l, n.getIzquierdo(), valor, elem);
                }
            } else {
                if (n.getDerecho() != null) {
                    listarMayoresQueAux(l, n.getDerecho(), valor, elem);
                }
            }
        }
    }

    private void listarMayores(Comparable valor, NodoArbol n, Lista l) {
        if (n != null) {
            if (n.getIzquierdo() != null && valor.compareTo(n.getElem()) <= 0) {      
                listarMayores(valor, n.getIzquierdo(), l);
            }
            if (valor.compareTo(n.getElem()) < 0) {
                l.insertar(n.getElem(), l.longitud()+1);
            }
            if (n.getDerecho() != null && valor.compareTo(n.getElem()) <= 0) {
                listarMayores(valor, n.getDerecho(), l);

            }
        }
    }

    public Lista listarMenores(Comparable elem) {
        Lista l = new Lista();
        if (this.raiz != null) {
            listarMenoresAux(elem, this.raiz, l);
        }
        return l;
    }

    private void listarMenoresAux(Comparable elem, NodoArbol n, Lista l) {
        if (elem.compareTo(n.getElem()) >= 0) {

            if (n.getIzquierdo() != null) {
                listarMenoresAux(elem, n.getIzquierdo(), l);
            }
            if (elem.compareTo(n.getElem()) > 0) {
                l.insertar(n.getElem(), l.longitud() + 1);
            }
            if (n.getDerecho() != null) {
                listarMenoresAux(elem, n.getDerecho(), l);
            }
        } else {
            if (elem.compareTo(n.getIzquierdo().getElem()) >= 0) {
                listarMenoresAux(elem, n.getIzquierdo(), l);
            }
        }

    }

//Ejercicio 2do parcial    
    public void eliminarHojasRango(Comparable min, Comparable max) {
        if (this.raiz != null) {
            if (this.raiz.getDerecho() == null && this.raiz.getIzquierdo() == null && min.compareTo(this.raiz.getElem()) >= 0 && max.compareTo(this.raiz.getElem()) <= 0) {
                this.raiz = null;
            } else {
                if (max.compareTo(this.raiz.getElem()) >= 0) {
                    if (this.raiz.getIzquierdo() != null) {
                        eliminarHojasRangoAux(this.raiz.getIzquierdo(), min, max, this.raiz.getIzquierdo().getIzquierdo(), this.raiz.getIzquierdo().getDerecho());
                    }

                } else {
                    if (min.compareTo(this.raiz.getElem()) <= 0) {
                        if (this.raiz.getDerecho() != null) {
                            eliminarHojasRangoAux(this.raiz.getDerecho(), min, max, this.raiz.getDerecho().getIzquierdo(), this.raiz.getDerecho().getDerecho());
                        }
                    } else {
                        eliminarHojasRangoAux(this.raiz, min, max, this.raiz.getIzquierdo(), this.raiz.getDerecho());
                    }
                }
            }
        }
    }

    private void eliminarHojasRangoAux(NodoArbol n, Comparable min, Comparable max, NodoArbol hijoIzq, NodoArbol hijoDer) { //recorrer preorden
        if (n != null) {
            if (hijoIzq != null) {
                if (hijoIzq.getDerecho() == null && hijoIzq.getIzquierdo() == null) {
                    if (min.compareTo(hijoIzq.getElem()) >= 0 && max.compareTo(hijoIzq.getElem()) <= 0) {
                        n.setIzquierdo(null);
                    }
                }
            }
            if (hijoDer != null) {
                if (hijoDer.getDerecho() == null && hijoDer.getIzquierdo() == null) {
                    if (min.compareTo(hijoDer.getElem()) >= 0 && max.compareTo(hijoDer.getElem()) <= 0) {
                        n.setDerecho(null);
                    }
                }
            }
            if (n.getIzquierdo() != null) {
                eliminarHojasRangoAux(n.getIzquierdo(), min, max, n.getIzquierdo().getIzquierdo(), n.getIzquierdo().getDerecho());
            }
            if (n.getDerecho() != null) {
                eliminarHojasRangoAux(n.getDerecho(), min, max, n.getDerecho().getIzquierdo(), n.getDerecho().getDerecho());
            }
        }
    }

    public void eliminarMinimo() {
        if (this.raiz.getIzquierdo() != null) {
            eliminarMin(this.raiz);
        } else if(this.raiz.getDerecho()==null){
            this.raiz=null;
        }else{
            this.raiz = this.raiz.getDerecho();
        }

    }

    private void eliminarMin(NodoArbol n) {
        NodoArbol aux = n.getIzquierdo();
        while (aux.getIzquierdo() != null) {
            aux = aux.getIzquierdo();
            n = n.getIzquierdo();
        }
        n.setIzquierdo(null);
    }
    
    private NodoArbol obtenerNodo(NodoArbol n, Comparable elem){
        NodoArbol aux = null; 
        if(elem.compareTo(n.getElem())==0){
            aux = n;
        }else{
            if(elem.compareTo(n.getElem()) < 0 && n.getIzquierdo()!=null){
                aux = obtenerNodo(n.getIzquierdo(),elem);
            }
            if(elem.compareTo(n.getElem()) > 0 && n.getDerecho()!=null)    
               aux = obtenerNodo(n.getDerecho(),elem); 
        }
        return aux;
    }
    
    public Lista listarPreorden(){
        Lista l1 = new Lista();
        if (this.raiz != null){
            listarPreordenAux(this.raiz, l1);
        }
        return l1;
    }
    private void listarPreordenAux(NodoArbol n, Lista l1){
        if (n != null){
            l1.insertar(n.getElem(), l1.longitud()+1);
            
            if (n.getIzquierdo() != null){
                listarPreordenAux(n.getIzquierdo(), l1);
            }
            if (n.getDerecho() != null){
                listarPreordenAux(n.getDerecho(), l1);
            }
        }
    }
    
    public Lista listarInorden(){
        Lista l1 = new Lista();
        if (this.raiz != null){
            listarInordenAux(this.raiz, l1);
        }
        return l1;
    }
    private void listarInordenAux(NodoArbol n, Lista l1){
        if (n != null){
            if (n.getIzquierdo() != null){
                listarInordenAux(n.getIzquierdo(), l1);
            }
            l1.insertar(n.getElem(), l1.longitud()+1); 
            
            if (n.getDerecho() != null){
                listarInordenAux(n.getDerecho(), l1);
            }
        }
    }

    public Lista listarPostorden(){
        Lista l1 = new Lista();
        if (this.raiz != null){
            listarPostordenAux(this.raiz, l1);
        }
        return l1;
    }
    private void listarPostordenAux(NodoArbol n, Lista l1){
        if (n != null){
            if (n.getIzquierdo() != null){
                listarInordenAux(n.getIzquierdo(), l1);
            }
            if (n.getDerecho() != null){
                listarInordenAux(n.getDerecho(), l1);
            }
            l1.insertar(n.getElem(), l1.longitud()+1); 
        }
    }
}
