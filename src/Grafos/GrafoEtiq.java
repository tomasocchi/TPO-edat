/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos;

import lineales.dinamica.Lista;
import lineales.dinamica.Cola;
import java.util.HashMap;

/**
 *
 * @author Tomas
 */
public class GrafoEtiq {

    private NodoVert inicio;

    public GrafoEtiq() {
        this.inicio = null;
    }

    public boolean insertarVertice(Object nuevoVertice) {
        boolean exito = false;
        NodoVert aux = this.ubicarVertice(nuevoVertice);
        if (aux == null) {
            this.inicio = new NodoVert(nuevoVertice, this.inicio, null);
            exito = true;
        }
        return exito;
    }

    private NodoVert ubicarVertice(Object buscado) {
        NodoVert aux = this.inicio;
        while (aux != null && !aux.getElem().equals(buscado)) {
            aux = aux.getSigVertice();
        }
        return aux;
    }

    public boolean eliminarVertice(Object elem) {
        boolean exito = false;
        if (this.inicio != null) {
            if (this.inicio.getElem().equals(elem)) {
                eliminaAdy(this.inicio, elem);
                this.inicio = this.inicio.getSigVertice();
            } else {
                NodoVert aux = this.inicio;
                NodoVert aux1 = this.inicio.getSigVertice();
                while (aux1 != null && !exito) {
                    if (aux1.getElem().equals(elem)) {
                        exito = true;
                        eliminaAdy(aux1, elem);
                    } else {
                        aux1 = aux1.getSigVertice();
                        aux = aux.getSigVertice();
                    }
                }
                if (aux1 != null) {
                    aux.setSigVert(aux1.getSigVertice());
                } else {
                    aux.setSigVert(null);
                }

            }
        }
        return exito;
    }

    private void eliminaAdy(NodoVert n, Object elem) {
        NodoAdy aux = n.getPrimerAdy();
        while (aux != null) {
            eliminaAdyAux(aux.getVertice(), elem);
            aux = aux.getSigAdyacente();
        }
    }

    private void eliminaAdyAux(NodoVert n, Object elem) {
        boolean buscado = false;
        NodoAdy a1 = n.getPrimerAdy();
        if (a1.getVertice().getElem().equals(elem)) {
            n.setPrimerAdy(a1.getSigAdyacente());
        } else {
            NodoAdy a2 = a1.getSigAdyacente();
            while (!buscado) {
                if (a2.getVertice().getElem().equals(elem)) {
                    a1.setSigAdyacente(a2.getSigAdyacente());
                    buscado = true;
                }
                a1 = a1.getSigAdyacente();
                a2 = a2.getSigAdyacente();
            }
        }
    }

    public boolean insertarArco(Object origen, Object destino, Object etiqueta) {
        boolean exito = false;
        NodoVert auxO = null;
        NodoVert auxD = null;
        NodoVert aux = this.inicio;

        while ((auxO == null || auxD == null) && aux != null) {
            if (aux.getElem().equals(origen)) {
                auxO = aux;
            }
            if (aux.getElem().equals(destino)) {
                auxD = aux;
            }
            aux = aux.getSigVertice();
        }
        if (auxD != null && auxO != null) {
            exito = insertarArcoAux(auxO, etiqueta, auxD);
            if (exito) {
                exito = insertarArcoAux(auxD, etiqueta, auxO);
            }
        }

        return exito;
    }

    private boolean insertarArcoAux(NodoVert n, Object el, NodoVert q) {
        boolean exito = true;
        NodoAdy aux = n.getPrimerAdy();
        if (aux == null) {
            n.setPrimerAdy(new NodoAdy(q, null, el));
        } else {
            while (aux.getSigAdyacente() != null && exito) {
                if (aux.getVertice().getElem().equals(q.getElem())) {
                    exito = false;
                }
                aux = aux.getSigAdyacente();
            }
            if (exito) {
                aux.setSigAdyacente(new NodoAdy(q, null, el));
            }
        }

        return exito;
    }

    public boolean eliminarArco(Object origen, Object destino) {
        boolean exito = false;
        NodoVert auxO = null;
        NodoVert auxD = null;
        NodoVert aux = this.inicio;

        while ((auxO == null || auxD == null) && aux != null) {
            if (aux.getElem().equals(origen)) {
                auxO = aux;
            }
            if (aux.getElem().equals(destino)) {
                auxD = aux;
            }
            aux = aux.getSigVertice();
        }
        if (auxD != null && auxO != null) {
            exito = buscaAdy2(auxO, destino);
            exito = buscaAdy2(auxD, origen);

        }
        return exito;
    }

    private boolean buscaAdy(NodoVert o, Object d) {
        NodoAdy q = o.getPrimerAdy();
        boolean exito = false;
        while (q != null && !exito) {
            if (q.getVertice().getElem().equals(d)) {
                exito = true;
            }
            q = q.getSigAdyacente();
        }
        return exito;
    }

    private boolean buscaAdy2(NodoVert o, Object d) {
        boolean exito = false;
        NodoAdy r = o.getPrimerAdy();

        if (r.getVertice().getElem().equals(d)) {
            o.setPrimerAdy(r.getSigAdyacente());
            exito = true;
        } else {
            NodoAdy q = r.getSigAdyacente();
            while (q != null && !exito) {
                if (q.getVertice().getElem().equals(d)) {
                    exito = true;
                } else {
                    r = r.getSigAdyacente();
                    q = q.getSigAdyacente();
                }
            }
            if (exito) {
                r.setSigAdyacente(q.getSigAdyacente());
            }
        }

        return exito;
    }

    public boolean existeVertice(Object elem) {
        boolean exito = false;
        if (this.inicio != null) {
            NodoVert aux = this.inicio;
            while (aux != null && !exito) {
                if (aux.getElem().equals(elem)) {
                    exito = true;
                }
                aux = aux.getSigVertice();
            }
        }
        return exito;
    }

    public boolean existeCamino(Object origen, Object destino) {
        boolean exito = false;
        //verifica si ambos vertices existen
        NodoVert auxO = null;
        NodoVert auxD = null;
        NodoVert aux = this.inicio;

        while ((auxO == null || auxD == null) && aux != null) {
            if (aux.getElem().equals(origen)) {
                auxO = aux;
            }
            if (aux.getElem().equals(destino)) {
                auxD = aux;
            }

            aux = aux.getSigVertice();
        }
        if (auxO != null && auxD != null) {
            Lista visitados = new Lista();
            exito = existeCaminoAux(auxO, destino, visitados);
        }

        return exito;
    }

    private boolean existeCaminoAux(NodoVert n, Object dest, Lista vis) {
        boolean exito = false;
        if (n != null) {
            // si el vertice n es el destino hay camino
            if (n.getElem().equals(dest)) {
                exito = true;
            } else {
                //si no es el destino verifica si hay camino entre n y dest
                vis.insertar(n.getElem(), vis.longitud() + 1);
                NodoAdy ady = n.getPrimerAdy();
                while (!exito && ady != null) {
                    if (vis.localizar(ady.getVertice().getElem()) < 0) {
                        exito = existeCaminoAux(ady.getVertice(), dest, vis);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
        }
        return exito;
    }

    public boolean existeArco(Object origen, Object destino) {
        boolean exito = false;
        boolean encontro = false;
        NodoVert o = this.inicio;
        while (o != null && !encontro) {
            if (o.getElem().equals(origen)) {
                exito = buscaAdy(o, destino);
                encontro = true;
            }
            o = o.getSigVertice();
        }
        return exito;
    }

    public Lista listarEnProfundidad() {
        Lista visitados = new Lista();
        NodoVert aux = this.inicio;
        while (aux != null) {
            if (visitados.localizar(aux.getElem()) < 0) {
                listarEnProfundidadAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    private void listarEnProfundidadAux(NodoVert n, Lista vis) {
        if (n != null) {
            vis.insertar(n.getElem(), vis.longitud() + 1);
            NodoAdy ady = n.getPrimerAdy();
            while (ady != null) {
                if (vis.localizar(ady.getVertice().getElem()) < 0) {
                    listarEnProfundidadAux(ady.getVertice(), vis);
                }
                ady = ady.getSigAdyacente();
            }
        }
    }

    public Lista listarEnAnchura() {
        Lista visitados = new Lista();
        NodoVert aux = this.inicio;
        while (aux != null) {
            if (visitados.localizar(aux.getElem()) < 0) {
                listarEnAnchuraAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    private void listarEnAnchuraAux(NodoVert n, Lista vis) {
        Cola q = new Cola();
        NodoVert u;
        NodoAdy v;
        vis.insertar(n.getElem(), vis.longitud() + 1);
        q.poner(n);
        while (q.obtenerFrente() != null) {
            u = (NodoVert) q.obtenerFrente();
            q.sacar();
            v = u.getPrimerAdy();

            while (v != null) {
                if (vis.localizar(v.getVertice().getElem()) < 0) {
                    vis.insertar(v.getVertice().getElem(), vis.longitud() + 1);
                    q.poner(v.getVertice());
                }
                v = v.getSigAdyacente();
            }
        }
    }

    public Lista caminoMasCorto(Object origen, Object destino) {

        Lista visitados = new Lista();
        Lista caminoCorto = new Lista();

        NodoVert inicio = ubicarVertice(origen);
        NodoVert fin = ubicarVertice(destino);

        if (inicio != null && fin != null) {
            caminoCorto = caminoMasCortoAux(inicio, destino, visitados, caminoCorto);
        }
        return caminoCorto;
    }

    private Lista caminoMasCortoAux(NodoVert aux, Object dest, Lista visitados, Lista caminoCorto) {

        if (aux != null) {
            visitados.insertar(aux.getElem().toString(), visitados.longitud() + 1);

            if (aux.getElem().equals(dest)) {
                if ((visitados.longitud() < caminoCorto.longitud()) || caminoCorto.longitud() == 0) {
                    caminoCorto = visitados.clone();
                }
            } else {
                NodoAdy ady = aux.getPrimerAdy();
                while (ady != null) {
                    if (visitados.localizar(ady.getVertice().getElem()) < 0) {
                        if (caminoCorto.longitud() == 0 || (visitados.longitud() + 1) < caminoCorto.longitud()) {
                            caminoCorto = caminoMasCortoAux(ady.getVertice(), dest, visitados, caminoCorto);
                        }
                    }
                    ady = ady.getSigAdyacente();
                }
            }
            visitados.eliminar(visitados.longitud());
        }
        return caminoCorto;

    }

    public Lista caminoMenorKm(Object origen, Object destino) {
        NodoVert inicio = ubicarVertice(origen);

        boolean exito = false;
        Lista visitados = new Lista();
        Lista l = new Lista();
        Cola q = new Cola();
        q.poner((double) 100000);
        if (this.existeCamino(origen, destino)) {
            l = CaminoMenorKmAux(0, visitados, inicio, destino, l, q);
        }
        return l;
    }

    private Lista CaminoMenorKmAux(double kmRecorridos, Lista visitados, NodoVert nodo, Object destino, Lista caminoMenor, Cola q) {
        if (nodo != null) {

            visitados.insertar(nodo.getElem(), visitados.longitud() + 1);

            if (nodo.getElem().equals(destino)) {
                if (kmRecorridos < (double) q.obtenerFrente()) {
                    caminoMenor = visitados.clone();
                    q.vaciar();
                    q.poner(kmRecorridos);
                }
            } else {
                NodoAdy ady = nodo.getPrimerAdy();
                while (ady != null) {
                    if (visitados.localizar(ady.getVertice().getElem()) < 0) {
                        if ((double) ady.getEtiqueta() < (double) q.obtenerFrente() || ady.getVertice().getElem().equals(destino)) {
                            caminoMenor = CaminoMenorKmAux(kmRecorridos + (double) ady.getEtiqueta(), visitados, ady.getVertice(), destino, caminoMenor, q);
                        }
                    }
                    ady = ady.getSigAdyacente();
                }
            }
            visitados.eliminar(visitados.longitud());

        }
        return caminoMenor;
    }

    public Lista caminoMasLargo(Object elemA, Object elemB) {
        Lista camino = new Lista();
        HashMap<String, Object> visitados = new HashMap<String, Object>();
        NodoVert nodo = this.inicio;
        NodoVert verticeA = null;
        NodoVert verticeB = null;
        while (nodo != null) {
            if (nodo.getElem().equals(elemA)) {
                verticeA = nodo;
            }
            if (nodo.getElem().equals(elemB)) {
                verticeB = nodo;
            }
            nodo = nodo.getSigVertice();
        }
        if (verticeA != null && verticeB != null) {
            camino = this.caminoMasLargoAux(verticeA, verticeB, visitados);
        }
        return camino;
    }

    private Lista caminoMasLargoAux(NodoVert inicio, NodoVert destino, HashMap<String, Object> visitados) {
        Lista l = new Lista();
        visitados.put(inicio.getElem().toString(), inicio);
        if (inicio != null) {
            if (inicio.equals(destino)) {
                l.insertar(inicio.getElem(), 1);
            } else {
                NodoAdy adyacente = inicio.getPrimerAdy();
                Lista listaAuxiliar = new Lista();
                while (adyacente != null) {
                    if (visitados.get(adyacente.getVertice().getElem().toString()) == null && l.longitud() == 0) {
                        l = this.caminoMasLargoAux(adyacente.getVertice(), destino, visitados);
                    } else if (visitados.get(adyacente.getVertice().getElem().toString()) == null) {
                        listaAuxiliar = this.caminoMasLargoAux(adyacente.getVertice(), destino, visitados);
                        if (listaAuxiliar.longitud() >= l.longitud()) {
                            l = listaAuxiliar;
                        }
                    }
                    adyacente = adyacente.getSigAdyacente();
                }
                if (l.longitud() > 0) {
                    l.insertar(inicio.getElem(), 1);
                }
            }
        }
        visitados.remove(inicio.getElem().toString());
        return l;
    }

    public String toString() {
        String cad = "";
        NodoVert aux = this.inicio;
        while (aux != null) {
            cad = cad + "Estación: " + aux.getElem().toString() + " -> [" + llamadoStr(aux) + "] \n";
            aux = aux.getSigVertice();
        }
        return cad;
    }

    private String llamadoStr(NodoVert n) {
        String cad = "";
        NodoAdy a = n.getPrimerAdy();
        while (a != null) {
            cad = cad + a.getVertice().getElem().toString() + ", " + a.getEtiqueta().toString() + "; ";
            a = a.getSigAdyacente();
        }
        return cad;
    }

    public boolean esVacio() {
        return this.inicio == null;
    }

    public GrafoEtiq clone() {
        GrafoEtiq c = new GrafoEtiq();
        c.inicio = clonarAux(this.inicio);
        return c;
    }

    private NodoVert clonarAux(NodoVert n) {
        NodoVert nodoClon = null;
        if (n != null) {
            nodoClon = new NodoVert(n.getElem(), null, null);
            nodoClon.setPrimerAdy(clonarAdy(n.getPrimerAdy()));
            nodoClon.setSigVert(clonarAux(n.getSigVertice()));

        }
        return nodoClon;
    }

    private NodoAdy clonarAdy(NodoAdy n) {
        NodoAdy nodoClon = null;
        if (n != null) {
            nodoClon = new NodoAdy(null, null, n.getEtiqueta());
            nodoClon.setSigAdyacente(clonarAdy(n.getSigAdyacente()));
        }
        return nodoClon;
    }

}
