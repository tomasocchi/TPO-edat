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
public class ArbolGen {

    //Atributos
    private NodoGen raiz;

    //Constructor
    public ArbolGen() {
        this.raiz = null;
    }

    //Modulos
    public boolean insertar(Object elemNuevo, Object elemPadre) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoGen(elemNuevo, null, null);
        } else {
            NodoGen nPadre = obtenerNodo(this.raiz, elemPadre);
            if (nPadre != null) {

                if (nPadre.getHijoIzquierdo() == null) {
                    nPadre.setHijoIzquierdo(new NodoGen(elemNuevo, null, null));
                } else {
                    NodoGen aux = nPadre.getHijoIzquierdo();

                    while (aux.getHermanoDerecho() != null) {
                        aux = aux.getHermanoDerecho();
                    }
                    aux.setHermanoDerecho(new NodoGen(elemNuevo, null, null));
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoGen obtenerNodo(NodoGen n, Object el) {
        NodoGen resultado = null;
        if (n != null) {
            if (n.getElem().equals(el)) {
                resultado = n;
            } else {
                NodoGen hijo = n.getHijoIzquierdo();
                while (hijo != null && resultado == null) {
                    if (hijo.getElem().equals(el)) {
                        resultado = hijo;
                    } else {
                        if (hijo.getHijoIzquierdo() != null) {
                            resultado = obtenerNodo(hijo, el);
                        }
                    }

                    hijo = hijo.getHermanoDerecho();
                }

            }
        }
        return resultado;
    }

    public boolean esVacio() {
        boolean exito = false;
        if (this.raiz == null) {
            exito = true;
        }
        return exito;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public boolean pertenece(Object el) {
        boolean exito = false;
        if (this.raiz != null) {
            NodoGen n = obtenerNodo(this.raiz, el);
            if (n != null) {
                exito = true;
            }
        }
        return exito;
    }

    public String toString() {
        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoGen n) {
        String s = "";
        if (n != null) {
            s += n.getElem().toString() + " -> ";
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                s += hijo.getElem().toString() + ", ";
                hijo = hijo.getHermanoDerecho();
            }

            hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                s += "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return s;
    }


    /*
    public int altura() {
        int alt;
        if (this.raiz == null) {
            alt = -1;
        } else {
            alt = alturaAux(this.raiz);
        }
        return alt;
    }

    private int alturaAux(NodoGen n) {
       int it = 0; 
       if(n != null){
           if(n.getHijoIzquierdo() != null){
               it++;
           }
           NodoGen hijo = n.getHijoIzquierdo(); 
           while(hijo != null){
               if(hijo.getHijoIzquierdo() != null){
                   it = it + alturaAux(hijo);
               }
               hijo = hijo.getHermanoDerecho();
           }
       }
       return it; 
    }
     */
    public int altura() {
        int alt = -1;
        if (this.raiz != null) {
            alt = alturaAux(this.raiz);
        }
        return alt;
    }

    private int alturaAux(NodoGen n) {
        int it = 0;
        if (n.getHijoIzquierdo() != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            int retornoHijos = alturaAux(hijo);
            hijo = hijo.getHermanoDerecho();
            while (hijo != null) {
                int retornoLlamado = alturaAux(hijo);
                if (retornoHijos < retornoLlamado) {
                    retornoHijos = retornoLlamado;
                }
                hijo = hijo.getHermanoDerecho();
            }
            it = retornoHijos + 1;
        }
        return it;
    }


    /*
    public Lista ancestros(Object elem){
        Lista lis = new Lista(); 
        if(this.raiz != null){
            NodoGen n = obtenerNodo(this.raiz, el);
            if(n!= null){
               ancestrosAux(this.raiz,lis,elem);        
            }                            
        }
        return lis; 
    }
    
    private void ancestrosAux(NodoGen n, Lista lis, Object el){
        if(n != null){
            if(n.getElem().equals(el)){
                lis.insertar(el, lis.longitud()+1);
            }
        }
    }
     */
    public Lista ancestros(Object elem) {
        Lista l1 = new Lista();
        listarAncestrosAux(this.raiz, l1, elem);
        return l1;
    }

    private void listarAncestrosAux(NodoGen n, Lista l1, Object elem) {
        if (n != null) {
            if (n.getElem().equals(elem)) {
                l1.insertar(elem, l1.longitud() + 1);
            } else {
                NodoGen hijo = n.getHijoIzquierdo();
                while (hijo != null && l1.esVacia()) {
                    listarAncestrosAux(hijo, l1, elem);
                    hijo = hijo.getHermanoDerecho();
                }
                if (!l1.esVacia()) {
                    l1.insertar(n.getElem(), l1.longitud() + 1);
                }
            }
        }
    }

    public int nivel(Object elem) {
        return nivelAux(this.raiz, elem, 0);
    }

    private int nivelAux(NodoGen n, Object elem, int piso) {
        int res = -1;
        if (n != null) {
            if (n.getElem().equals(elem)) {
                res = piso;
            } else {
                NodoGen hijo = n.getHijoIzquierdo();
                while (hijo != null && res == -1) {
                    res = nivelAux(hijo, elem, piso + 1);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return res;
    }

    public Object padre(Object elem) {
        Object padre;
        if (this.raiz == null || this.raiz.getElem().equals(elem)) {
            padre = null;
        } else {
            padre = padreAux(this.raiz, elem);
        }
        return padre;
    }

    private Object padreAux(NodoGen n, Object el) {
        Object res = null;
        if (n != null) {
            if (n.getElem().equals(el)) {
                res = n.getElem();
            } else {
                NodoGen hijo = n.getHijoIzquierdo();
                while (hijo != null && res == null) {
                    if (hijo.getElem().equals(el)) {
                        res = n.getElem();
                    } else {
                        if (hijo.getHijoIzquierdo() != null) {
                            res = padreAux(hijo, el);
                        }
                    }
                    hijo = hijo.getHermanoDerecho();
                }
            }

        }
        return res;
    }

    public ArbolGen clone() {
        ArbolGen c = new ArbolGen();
        c.raiz = clonarAux(this.raiz);
        return c;
    }

    private NodoGen clonarAux(NodoGen n) {
        NodoGen nodoClon = null;
        if (n != null) {
            nodoClon = new NodoGen(n.getElem(), null, null);
            nodoClon.setHijoIzquierdo(clonarAux(n.getHijoIzquierdo()));
            nodoClon.setHermanoDerecho(clonarAux(n.getHermanoDerecho()));
        }
        return nodoClon;
    }
    
    //LISTADOS   
    public Lista listarInorden() {
        Lista salida = new Lista();
        listarInordenAux(this.raiz, salida);
        return salida;
    }

    private void listarInordenAux(NodoGen n, Lista ls) {
        if (n != null) {
            //Llamado recursivo con primer hijo de n
            if (n.getHijoIzquierdo() != null) {
                listarInordenAux(n.getHijoIzquierdo(), ls);
            }

            // visita del nodo n
            ls.insertar(n.getElem(), ls.longitud() + 1);

            // llamados recursivos con los otros hijos de n
            if (n.getHijoIzquierdo() != null) {
                NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarInordenAux(hijo, ls);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public Lista listarPreorden() {
        Lista salida = new Lista();
        listarPreordenAux(this.raiz, salida);
        return salida;
    }

    private void listarPreordenAux(NodoGen n, Lista l) {
        if (n != null) {
            l.insertar(n.getElem(), l.longitud() + 1);
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                listarPreordenAux(hijo, l);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    public Lista listarPosorden() {
        Lista salida = new Lista();
        listarPosordenAux(this.raiz, salida);
        return salida;
    }

    private void listarPosordenAux(NodoGen n, Lista lis) {
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                listarPosordenAux(hijo, lis);
                hijo = hijo.getHermanoDerecho();
            }
            lis.insertar(n.getElem(), lis.longitud() + 1);
        }
    }

    public Lista ListarNiveles() {
        Lista salida = new Lista();
        listarNivelesAux(this.raiz, salida);
        return salida;
    }

    private void listarNivelesAux(NodoGen n, Lista l) {
        Cola q = new Cola();
        NodoGen nodo;
        q.poner(n);

        while (!q.esVacia()) {
            nodo = (NodoGen) q.obtenerFrente();
            q.sacar();
            l.insertar(nodo.getElem(), l.longitud() + 1);
            NodoGen hijo = nodo.getHijoIzquierdo();

            while (hijo != null) {
                q.poner(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

//METODO TP N° 2
    public boolean sonFrontera(Lista unaLista) {
        boolean ver = noSeRepiten(unaLista);
        if (ver && this.raiz != null) {
            return sonFronteraAux(this.raiz, unaLista);
        } else {
            return false;
        }
    }

    private boolean sonFronteraAux(NodoGen n, Lista l) {
        boolean exito = true;
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null && exito) {
                exito = sonFronteraAux(hijo, l);
                hijo = hijo.getHermanoDerecho();
            }
            if (l.localizar(n.getElem()) != -1 && n.getHijoIzquierdo() != null) {
                exito = false;
            }
        }
        return exito;
    }

    private boolean noSeRepiten(Lista l) {
        boolean exito = true;
        int longi = l.longitud();
        int j = 1;
        int i = 2;
        if (l.esVacia()) {
            exito = false;
        } else {
            while (j < longi && exito) {
                if (l.recuperar(i) == l.recuperar(j)) {
                    exito = false;
                }
                i++;
                if (i > longi) {
                    j++;
                    i = j + 1;
                }
            }
        }
        return exito;
    }

    public int grado() {
        int grado;
        if (raiz != null) {
            grado = grado(this.raiz, 0);
        } else {
            grado = -1;
        }
        return grado;
    }

    private int grado(NodoGen nodo, int grado) {
        int gradoLocal = 0;

        if (nodo != null) {
            gradoLocal = 1;

            NodoGen aux = nodo;
            while (aux.getHermanoDerecho() != null) {
                aux = aux.getHermanoDerecho();
                gradoLocal++;
            }

            if (grado < gradoLocal) {
                grado = gradoLocal;
            }

            grado = grado(nodo.getHijoIzquierdo(), grado);
            grado = grado(nodo.getHermanoDerecho(), grado);
        }

        return grado;
    }

    //EJERCICIOS DE PARCIALES
    public boolean verificarCamino3(Lista l1) {
        return verificarCamino3Aux(this.raiz, l1);
    }

    private boolean verificarCamino3Aux(NodoGen n, Lista l1) {
        boolean exito = false;
        if (n.getElem().equals(l1.recuperar(1)) && l1.longitud() == 1) {
            exito = true;
        } else {
            if (n.getElem().equals(l1.recuperar(1))) {
                NodoGen hijo = n.getHijoIzquierdo();
                l1.eliminar(1);
                while (hijo != null && !exito) {
                    exito = verificarCamino3Aux(hijo, l1);
                    hijo = hijo.getHermanoDerecho();
                }
                if(!exito){
                    l1.insertar(n.getElem(), 1);
                }
            }
        }
        return exito;
    }


    public Lista listarEntreNiveles(int n, int n2) {
        Lista l = new Lista();
        listarEntreNivelesAux(this.raiz, n, n2, 0, l);
        return l;
    }

    public void listarEntreNivelesAux(NodoGen r, int n, int n2, int aux, Lista l) {
        if (r != null) {
            if (aux < n2 && r.getHijoIzquierdo() != null) {
                listarEntreNivelesAux(r.getHijoIzquierdo(), n, n2, aux + 1, l);
            }
            if (aux <= n2 && aux >= n) {
                l.insertar(r.getElem(), l.longitud() + 1);
            }
            if (r.getHijoIzquierdo() != null) {
                aux++;
                NodoGen hijo = r.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarEntreNivelesAux(hijo, n, n2, aux, l);
                    hijo = hijo.getHermanoDerecho();
                }

            }
        }
    }

    public boolean eliminar(Object el) {
        boolean exito = false;
        if (this.raiz.getElem().equals(el)) {
            this.raiz = null;
            exito = true;
        } else {
            exito = eliminarAux(el, this.raiz);
        }
        return exito;
    }

    private boolean eliminarAux(Object el, NodoGen n) {
        boolean exito = false;
        NodoGen aux = n.getHijoIzquierdo();
        //condicion es hijo izquierdo
        if (aux.getElem().equals(el)) {
            n.setHijoIzquierdo(aux.getHermanoDerecho());
            exito = true;
        } else {
            NodoGen hijo = n.getHijoIzquierdo();
            aux = aux.getHermanoDerecho();

            while (hijo != null && !exito) {
                //condicion es hijo derecho
                if (aux.getElem().equals(el)) {
                    hijo.setHermanoDerecho(aux.getHermanoDerecho());
                    exito = true;
                } else {
                    if (hijo.getHijoIzquierdo() != null) {
                        exito = eliminarAux(el, hijo.getHijoIzquierdo());
                    }
                    hijo = n.getHermanoDerecho();
                }
            }
        }
        return exito;
    }

    public Lista listarHastaNivel(int n2) {
        Lista l = new Lista();
        listarHastaNivelAux(this.raiz, 0, n2, 0, l);
        return l;
    }

    public void listarHastaNivelAux(NodoGen r, int n, int n2, int aux, Lista l) {
        if (r != null) {            
            if (aux <= n2 && aux >= n) {
                l.insertar(r.getElem(), l.longitud() + 1);
            }            
            if (aux <= n2 && r.getHijoIzquierdo() != null) {
                NodoGen hijo = r.getHijoIzquierdo();
                while (hijo != null) {
                    listarHastaNivelAux(hijo, n, n2, aux+1, l);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public boolean verificaCamino(Lista l) {
        boolean exito = false;
        if (this.raiz != null && !l.esVacia()) {
            exito = verificaCaminoAux(this.raiz, l);
        }
        return exito;
    }

    private boolean verificaCaminoAux(NodoGen n, Lista l) {
        boolean exito = false;
        if (n != null) {
            if (n.getElem().equals(l.recuperar(1))) {
                exito = true;
                l.eliminar(1);
                if (n.getHijoIzquierdo() != null && !l.esVacia()) {
                    exito = verificaCaminoAux(n.getHijoIzquierdo(), l);
                } else if (n.getHijoIzquierdo() != null && l.esVacia()) {
                    exito = false;
                }
            } else if (n.getHermanoDerecho() != null) {
                NodoGen bro = n.getHermanoDerecho();
                while (bro != null) {
                    if (bro.getElem().equals(l.recuperar(1))) {
                        exito = true;
                        l.eliminar(1);
                        if (bro.getHijoIzquierdo() != null && !l.esVacia()) {
                            exito = verificaCaminoAux(bro, l);
                        } else if (bro.getHijoIzquierdo() != null && l.esVacia()) {
                            exito = false;
                        }
                    }
                    bro = bro.getHermanoDerecho();
                }
            }
        }
        return exito;
    }

    public boolean insertarEnPosicion(Object elem, Object padre, int pos) {
        boolean exito = false;
        if (this.raiz != null && !padre.equals(null)) {
            exito = insertarEnPosicionAux(elem, padre, pos, this.raiz);
        }
        return exito;
    }

    private boolean insertarEnPosicionAux(Object elem, Object padre, int pos, NodoGen n) {
        boolean exito = false;
        if (n != null) {
            if (n.getElem().equals(padre)) {
                exito = true;
                ubicaHijo(elem, n, pos);
            } else if (n.getHijoIzquierdo() != null) {
                NodoGen hijoA = n.getHijoIzquierdo();
                while (hijoA != null) {
                    if (hijoA.getHijoIzquierdo() != null) {
                        exito = insertarEnPosicionAux(elem, padre, pos, hijoA);
                    }
                    hijoA = hijoA.getHermanoDerecho();
                }
            }
        }
        return exito;
    }
  
    private void ubicaHijo(Object hijoNew, NodoGen padre, int pos) {
        int aux = 1;
        if (pos == 1) {
            padre.setHijoIzquierdo(new NodoGen(hijoNew,null,padre.getHijoIzquierdo()));
        } else {
            NodoGen bro = padre.getHijoIzquierdo();
                while (bro.getHermanoDerecho() != null && aux + 1 < pos) {
                bro = bro.getHermanoDerecho();
                aux++;
            }
            bro.setHermanoDerecho(new NodoGen(hijoNew,null,bro.getHermanoDerecho()));
        }
    }

    //ejercicio parcial 2
    public void hermanoExtremoDerecho(Object letra) {
        if (pertenece(letra)) {
            boolean exito = hermanoExtremoDerechoAux(this.raiz, letra);
        }
    }

    private boolean hermanoExtremoDerechoAux(NodoGen n, Object elem) {
        boolean exito = false;
        if (n != null) {
            if (n.getElem().equals(elem)) {
                exito = true;
                if (n.getHijoIzquierdo() != null) {
                    ubicaHijo2(n);
                }

            } else {
                NodoGen hijo = n.getHijoIzquierdo();
                while (hijo != null && !exito) {
                    exito = hermanoExtremoDerechoAux(hijo, elem);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return exito;
    }

    private void ubicaHijo2(NodoGen n) {
        Object el = n.getHijoIzquierdo().getElem();
        boolean encontrado = false;
        NodoGen hijo = n.getHijoIzquierdo();
        while (hijo.getHermanoDerecho() != null && !encontrado) {
            if (el.equals(hijo.getHermanoDerecho().getElem())) {
                encontrado = true;
            } else {
                hijo = hijo.getHermanoDerecho();
            }
        }
        if (!encontrado) {
            hijo.setHermanoDerecho(new NodoGen(el, null, null));
        }
    }
}
