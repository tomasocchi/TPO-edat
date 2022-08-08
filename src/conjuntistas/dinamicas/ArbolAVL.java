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
public class ArbolAVL {

    private NodoAVL raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    public boolean insertar(Comparable elem) {
        boolean exito = true;
        if (this.raiz != null) {
            exito = insertarAux(this.raiz, elem, null);
        } else {
            this.raiz = new NodoAVL(elem, null, null);
        }
        return exito;
    }

    private boolean insertarAux(NodoAVL n, Comparable elem, NodoAVL padre) {
        boolean exito = false;
        NodoAVL nuevoHijo = new NodoAVL(elem, null, null);
        if (n != null) {
            if (elem.compareTo(n.getElem()) == 0) {
                exito = false;
            } else {
                if (elem.compareTo(n.getElem()) < 0) {
                    if (n.getIzquierdo() != null) {
                        exito = insertarAux(n.getIzquierdo(), elem, n);
                    } else {
                        n.setIzquierdo(nuevoHijo);
                        exito = true;
                    }
                } else if (elem.compareTo(n.getElem()) > 0) {
                    if (n.getDerecho() != null) {
                        exito = insertarAux(n.getDerecho(), elem, n);
                    } else {
                        n.setDerecho(nuevoHijo);
                        exito = true;
                    }
                }
            }
            if (exito) {
                n.recalcularAltura();
                if (padre != null) {
                    padre.recalcularAltura();
                }
                balancear(n, padre);
            }
        }
        return exito;
    }

    public boolean eliminar(Comparable clave) {
        boolean exito = false;
        if (this.raiz != null) {
            exito = eliminarAux(this.raiz, clave, null);
        }
        return exito;
    }

    private boolean eliminarAux(NodoAVL n, Comparable clave, NodoAVL padre) {

        boolean exito = false;
        if (n != null) {
            if (clave.compareTo(n.getElem()) == 0) {
                exito = true;

                if ((n.getIzquierdo() == null) || (n.getDerecho() == null)) {

                    NodoAVL temp = null;
                    // Caso que no tiene hijos
                    if ((n.getIzquierdo() == null) && (n.getDerecho() == null)) {
                        if (n.equals(padre.getDerecho())) {
                            padre.setDerecho(null);
                        } else {
                            padre.setIzquierdo(null);
                        }

                    } else {
                        //Caso con un hijo
                        
                        if (clave.compareTo(padre.getElem()) < 0) {
                            if (n.getDerecho() != null) {
                                padre.setIzquierdo(n.getDerecho());
                                
                            } else {
                                padre.setIzquierdo(n.getIzquierdo());
                            }
                        } else {
                            if (n.getDerecho() != null) {
                                padre.setDerecho(n.getDerecho());
                            } else {
                                padre.setDerecho(n.getIzquierdo());

                            }
                        }
                    }
                } else {
                    //Nodo con dos hijos, se busca el predecesor

                    NodoAVL temp = nodoValorMaximo(n.getIzquierdo());
                    n.setElem(temp.getElem());

                    // caso especial
                    // temp es hijo Izquierdo de n
                    if (temp == n.getIzquierdo()) {
                        n.setIzquierdo(temp.getIzquierdo());
                    }else{
                        //caso comun elemento con descendientes  
                        padre = buscaPadre(n.getIzquierdo(), temp);
                        padre.setDerecho(temp.getIzquierdo());

                    }

                }
            } else {
                if (clave.compareTo(n.getElem()) < 0 && n.getIzquierdo() != null) {
                    exito = eliminarAux(n.getIzquierdo(), clave, n);

                } else if (clave.compareTo(n.getElem()) > 0 && n.getDerecho() != null) {
                    exito = eliminarAux(n.getDerecho(), clave, n);
                }
            }

            if (exito) {
                n.recalcularAltura();
                if (padre != null) {
                    padre.recalcularAltura();
                }
                balancear(n, padre);
            }

        }
        return exito;
    }

    private int balance(NodoAVL n) {
        int res = -1;

        if (n != null) {
            if (n.getDerecho() != null && n.getIzquierdo() != null) {
                res = n.getIzquierdo().getAltura() - n.getDerecho().getAltura();
            } else {
                if (n.getIzquierdo() != null) {
                    res = n.getIzquierdo().getAltura() - (-1);
                } else if (n.getDerecho() != null) {
                    res = (-1) - n.getDerecho().getAltura();
                }
            }
        }
        return res;
    }

    private void balancear(NodoAVL n, NodoAVL padre) {
        int balance = balance(n);

        if (n != null) {
            if (n.equals(this.raiz)) {
                balancearAux(this.raiz);
            } else {
                if (balance < -1 || balance > 1) {
                    if (n.getElem().equals(padre.getIzquierdo().getElem())) {
                        NodoAVL bal = balancearAux(n);
                        padre.setIzquierdo(bal);
                    } else {
                        NodoAVL bal = balancearAux(n);
                        padre.setDerecho(bal);
                    }
                }

            }
        }
    }

    private NodoAVL balancearAux(NodoAVL n) {

        int balPadre = balance(n);
        int balHijo;

        if (n.getIzquierdo() != null) {
            balHijo = balance(n.getIzquierdo());
        } else {
            balHijo = balance(n.getDerecho());
        }

        if (balPadre > 1) {
            if (balHijo >= 0) {
                if (n.equals(this.raiz)) {
                    this.raiz = rotacionDerecha(this.raiz);
                } else {
                    n = rotacionDerecha(n);
                }
            } else {
                if (balHijo <= 0) {
                    if (n.equals(this.raiz)) {
                        this.raiz = rotacionDobleIzquierdaDerecha(this.raiz);
                    } else {
                        n = rotacionDobleIzquierdaDerecha(n);
                    }
                }
            }

        } else {
            if (balPadre < -1) {
                if (balHijo <= 0) {
                    if (n.equals(this.raiz)) {
                        this.raiz = rotacionIzquierda(this.raiz);
                    } else {
                        n = rotacionIzquierda(n);
                    }
                } else {
                    if (balHijo >= 0) {
                        if (n.equals(this.raiz)) {
                            this.raiz = rotacionDobleDerechaIzquierda(this.raiz);
                        } else {
                            n = rotacionDobleDerechaIzquierda(n);

                        }
                    }
                }
            }
        }
        return n;
    }

    private NodoAVL rotacionDerecha(NodoAVL nodoActual) {

        NodoAVL nuevaRaiz = nodoActual.getIzquierdo();
        NodoAVL temp = null;
        if (nuevaRaiz.getDerecho() != null) {
            temp = nuevaRaiz.getDerecho();
        }

        nuevaRaiz.setDerecho(nodoActual);
        nodoActual.setIzquierdo(temp);

        nodoActual.recalcularAltura();
        nuevaRaiz.recalcularAltura();

        return nuevaRaiz;
    }

    private NodoAVL rotacionIzquierda(NodoAVL nodoActual) {
        NodoAVL nuevaRaiz = nodoActual.getDerecho();
        NodoAVL temp = nuevaRaiz.getIzquierdo();

        nuevaRaiz.setIzquierdo(nodoActual);
        nodoActual.setDerecho(temp);

        nodoActual.recalcularAltura();
        nuevaRaiz.recalcularAltura();

        return nuevaRaiz;
    }

    private NodoAVL rotacionDobleIzquierdaDerecha(NodoAVL nodoActual) {
        NodoAVL hijoIzquierdo = nodoActual.getIzquierdo();

        hijoIzquierdo = rotacionIzquierda(hijoIzquierdo);
        nodoActual.setIzquierdo(hijoIzquierdo);
        nodoActual = rotacionDerecha(nodoActual);

        hijoIzquierdo.recalcularAltura();
        nodoActual.recalcularAltura();

        return nodoActual;
    }

    private NodoAVL rotacionDobleDerechaIzquierda(NodoAVL nodoActual) {
        NodoAVL hijoDerecho = nodoActual.getDerecho();

        hijoDerecho = rotacionDerecha(hijoDerecho);
        nodoActual.setDerecho(hijoDerecho);
        nodoActual = rotacionIzquierda(nodoActual);

        hijoDerecho.recalcularAltura();
        nodoActual.recalcularAltura();

        return nodoActual;
    }

    private NodoAVL nodoValorMaximo(NodoAVL n) {
        NodoAVL aux = n;
        if (aux.getDerecho() != null) {
            aux = nodoValorMaximo(aux.getDerecho());
        }
        return aux;
    }

    private NodoAVL buscaPadre(NodoAVL n, NodoAVL hijo) {
        NodoAVL p;
        p = n;
        if (p.getDerecho() != hijo) {
            p = buscaPadre(p.getDerecho(), hijo);
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

    private String llamadoStr(NodoAVL n) {
        String cad = "";

        if (n != null) {
            cad = n.getElem().toString();

            NodoAVL iz = n.getIzquierdo();
            if (iz != null) {
                cad = cad + "  H.I:" + iz.getElem().toString();
            } else {
                cad = cad + "  H.I:-";
            }
            NodoAVL der = n.getDerecho();
            if (der != null) {
                cad = cad + "  H.D:" + der.getElem().toString();
            } else {
                cad = cad + "  H.D:-";
            }
            cad = "\n" + cad + llamadoStr(iz) + llamadoStr(der);
        }
        return cad;
    }

}
