/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas.dinamicas;

import lineales.dinamica.Lista;

/**
 *
 * @author Tomas
 */
public class DiccionarioAVL {

    private NodoAVLDicc raiz;

    public DiccionarioAVL() {
        this.raiz = null;
    }

    public boolean insertar(Comparable TipoClave, Object TipoDato) {
        boolean exito = true;
        if (this.raiz != null) {
            exito = insertarAux(TipoClave, TipoDato, this.raiz, null);
        } else {
            this.raiz = new NodoAVLDicc(TipoClave, TipoDato, null, null);
        }

        if (exito) {
            this.raiz.recalcularAltura();
        }
        return exito;
    }

    private boolean insertarAux(Comparable clave, Object dato, NodoAVLDicc n, NodoAVLDicc padre) {
        boolean exito = true;
        NodoAVLDicc nuevoHijo = new NodoAVLDicc(clave, dato, null, null);
        if (n != null) {
            if (n.getClave().compareTo(clave) == 0) {
                exito = false;
            } else {
                if (n.getClave().compareTo(clave) > 0) {
                    if (n.getHijoIzquierdo() != null) {
                        exito = insertarAux(clave, dato, n.getHijoIzquierdo(), n);
                    } else {
                        n.setHijoIzquierdo(nuevoHijo);
                        exito = true;
                    }
                } else if (n.getClave().compareTo(clave) < 0) {
                    if (n.getHijoDerecho() != null) {
                        exito = insertarAux(clave, dato, n.getHijoDerecho(), n);
                    } else {
                        n.setHijoDerecho(nuevoHijo);
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
                n.recalcularAltura();
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

    private boolean eliminarAux(NodoAVLDicc n, Comparable clave, NodoAVLDicc padre) {

        boolean exito = false;
        if (n != null) {
            if (clave.compareTo(n.getClave()) == 0) {
                exito = true;
            //Caso nodo con al menos un hijo
            
                if ((n.getHijoIzquierdo() == null) || (n.getHijoDerecho() == null)) {
                    //Caso raiz
                    if (this.raiz.equals(n)) {
                        this.raiz = null;
                    } else {
                        // Caso que no tiene hijos
                        if ((n.getHijoIzquierdo() == null) && (n.getHijoDerecho() == null)) {
                            if (n.equals(padre.getHijoDerecho())) {
                                padre.setHijoDerecho(null);
                            } else {
                                padre.setHijoIzquierdo(null);
                            }

                        } else {
                            //Caso con un hijo
                            if (clave.compareTo(padre.getClave()) < 0) {
                                if (n.getHijoDerecho() != null) {
                                    padre.setHijoIzquierdo(n.getHijoDerecho());
                                } else {
                                    padre.setHijoIzquierdo(n.getHijoIzquierdo());
                                }
                            } else {

                                if (n.getHijoDerecho() != null) {
                                    padre.setHijoDerecho(n.getHijoDerecho());
                                } else {
                                    padre.setHijoDerecho(n.getHijoIzquierdo());

                                }
                            }
                        }
                    }

                } else {
                    //Caso 3 nodo con dos hijos, se busca el predecesor
                    NodoAVLDicc temp = nodoValorMaximo(n.getHijoIzquierdo());
                    NodoAVLDicc aux = new NodoAVLDicc(temp.getClave(), temp.getDato(), null, null);

                    //Caso raiz
                    if (n.equals(this.raiz)) {                        
                        this.raiz = aux;
                        aux.setHijoDerecho(n.getHijoDerecho());
                        aux.setHijoIzquierdo(n.getHijoIzquierdo());
                        eliminarAux(this.raiz.getHijoIzquierdo(), temp.getClave(), this.raiz);
                        n = aux;

                    } else {                        
                        aux.setHijoDerecho(n.getHijoDerecho());
                        aux.setHijoIzquierdo(n.getHijoIzquierdo());                        
                        if (n.equals(padre.getHijoIzquierdo())) {
                            padre.setHijoIzquierdo(aux);
                        } else {
                            padre.setHijoDerecho(aux);
                        }                       
                        eliminarAux(aux.getHijoIzquierdo(), temp.getClave(), aux);
                        n = aux;

                    }
                }
            } else {

                if (clave.compareTo(n.getClave()) < 0 && n.getHijoIzquierdo() != null) {
                    exito = eliminarAux(n.getHijoIzquierdo(), clave, n);

                } else if (clave.compareTo(n.getClave()) > 0 && n.getHijoDerecho() != null) {
                    exito = eliminarAux(n.getHijoDerecho(), clave, n);
                }
            }
            
            if (exito) {
                if (n != null) {
                    n.recalcularAltura();
                }
                if (padre != null) {
                    padre.recalcularAltura();
                }
                balancear(n, padre);
            }

        }
        return exito;
    }

    private int balance(NodoAVLDicc n) {
        int res = -1;

        if (n != null) {
            if (n.getHijoDerecho() != null && n.getHijoIzquierdo() != null) {
                res = n.getHijoIzquierdo().getAltura() - n.getHijoDerecho().getAltura();
            } else {
                if (n.getHijoIzquierdo() != null) {
                    res = n.getHijoIzquierdo().getAltura() - (-1);
                } else if (n.getHijoDerecho() != null) {
                    res = (-1) - n.getHijoDerecho().getAltura();
                }
            }
        }
        return res;
    }

    private void balancear(NodoAVLDicc n, NodoAVLDicc padre) {
        if (n != null) {
            int balance = balance(n);
            //Caso raiz
            if (n.equals(this.raiz)) {
                NodoAVLDicc a = balancearAux(this.raiz);
                this.raiz = a;
            } else {
                //Caso nodo no raiz
                if (balance < -1 || balance > 1) {
                    if (n.getClave().equals(padre.getHijoIzquierdo().getClave())) {
                        NodoAVLDicc bal = balancearAux(n);
                        padre.setHijoIzquierdo(bal);
                    } else {
                        NodoAVLDicc bal = balancearAux(n);
                        padre.setHijoDerecho(bal);
                    }
                }
            }
        }
    }

    private NodoAVLDicc balancearAux(NodoAVLDicc n) {

        int balPadre = balance(n);
        int balHijo;

        if (n.getHijoIzquierdo() != null) {
            balHijo = balance(n.getHijoIzquierdo());
        } else {
            balHijo = balance(n.getHijoDerecho());
        }

        if (balPadre > 1) {
            if (balHijo >= 0) {
                n = rotacionDerecha(n);
                n.recalcularAltura();
            } else {
                if (balHijo <= 0) {
                    n = rotacionDobleIzquierdaDerecha(n);
                    n.recalcularAltura();
                }
            }
        } else {
            if (balPadre < -1) {
                if (balHijo <= 0) {
                    n = rotacionIzquierda(n);
                    n.recalcularAltura();
                } else {
                    if (balHijo >= 0) {
                        n = rotacionDobleDerechaIzquierda(n);
                        n.recalcularAltura();
                    }
                }
            }
        }
        return n;
    }

    private NodoAVLDicc rotacionDerecha(NodoAVLDicc nodoActual) {

        NodoAVLDicc nuevaRaiz = nodoActual.getHijoIzquierdo();
        NodoAVLDicc temp = null;
        if (nuevaRaiz.getHijoDerecho() != null) {
            temp = nuevaRaiz.getHijoDerecho();
        }

        nuevaRaiz.setHijoDerecho(nodoActual);
        nodoActual.setHijoIzquierdo(temp);

        nodoActual.recalcularAltura();
        nuevaRaiz.recalcularAltura();

        return nuevaRaiz;
    }

    private NodoAVLDicc rotacionIzquierda(NodoAVLDicc nodoActual) {
        NodoAVLDicc nuevaRaiz = nodoActual.getHijoDerecho();
        NodoAVLDicc temp = nuevaRaiz.getHijoIzquierdo();

        nuevaRaiz.setHijoIzquierdo(nodoActual);
        nodoActual.setHijoDerecho(temp);

        nodoActual.recalcularAltura();
        nuevaRaiz.recalcularAltura();

        return nuevaRaiz;
    }

    private NodoAVLDicc rotacionDobleIzquierdaDerecha(NodoAVLDicc nodoActual) {
        NodoAVLDicc hijoIzquierdo = nodoActual.getHijoIzquierdo();

        hijoIzquierdo = rotacionIzquierda(hijoIzquierdo);
        nodoActual.setHijoIzquierdo(hijoIzquierdo);
        nodoActual = rotacionDerecha(nodoActual);

        hijoIzquierdo.recalcularAltura();
        nodoActual.recalcularAltura();

        return nodoActual;
    }

    private NodoAVLDicc rotacionDobleDerechaIzquierda(NodoAVLDicc nodoActual) {
        NodoAVLDicc hijoDerecho = nodoActual.getHijoDerecho();

        hijoDerecho = rotacionDerecha(hijoDerecho);
        nodoActual.setHijoDerecho(hijoDerecho);
        nodoActual = rotacionIzquierda(nodoActual);

        hijoDerecho.recalcularAltura();
        nodoActual.recalcularAltura();

        return nodoActual;
    }

    private NodoAVLDicc nodoValorMaximo(NodoAVLDicc n) {
        NodoAVLDicc aux = n;
        if (aux.getHijoDerecho() != null) {
            aux = nodoValorMaximo(n.getHijoDerecho());
        }
        return aux;
    }

    private NodoAVLDicc buscaPadre(NodoAVLDicc n, NodoAVLDicc hijo) {
        NodoAVLDicc p;
        p = n;
        if (p.getHijoDerecho() != hijo) {
            p = buscaPadre(p.getHijoDerecho(), hijo);
        }
        return p;
    }

    public boolean existeClave(Comparable clave) {
        boolean exito = false;
        if (this.raiz != null) {
            exito = existeClaveAux(this.raiz, clave);
        }
        return exito;
    }

    private boolean existeClaveAux(NodoAVLDicc n, Comparable clave) {
        boolean exito = false;
        if (n != null) {
            if (n.getClave().compareTo(clave) == 0) {
                exito = true;
            } else if (n.getClave().compareTo(clave) > 0) {
                exito = existeClaveAux(n.getHijoIzquierdo(), clave);
            } else {
                exito = existeClaveAux(n.getHijoDerecho(), clave);
            }
        }
        return exito;
    }

    public Object obtenerInformacion(Comparable clave) {
        Object salida = null;
        if (this.raiz != null) {
            salida = obtenerInformacionAux(this.raiz, clave);
        }
        return salida;
    }

    private Object obtenerInformacionAux(NodoAVLDicc n, Comparable clave) {
        Object res = null;
        if (n != null) {
            if (clave.compareTo(n.getClave()) == 0) {
                res = n.getDato();
            } else if (clave.compareTo(n.getClave()) < 0) {
                res = obtenerInformacionAux(n.getHijoIzquierdo(), clave);
            } else {
                res = obtenerInformacionAux(n.getHijoDerecho(), clave);
            }
        }
        return res;
    }

    public Lista listarClaves() {
        Lista l1 = new Lista();
        if (this.raiz != null) {
            listarClavesAux(this.raiz, l1);
        }
        return l1;
    }

    private void listarClavesAux(NodoAVLDicc n, Lista l1) {
        if (n != null) {
            l1.insertar(n.getClave(), l1.longitud() + 1);

            if (n.getHijoIzquierdo() != null) {
                listarClavesAux(n.getHijoIzquierdo(), l1);
            }
            if (n.getHijoDerecho() != null) {
                listarClavesAux(n.getHijoDerecho(), l1);
            }
        }
    }

    public Lista listarDatos() {
        Lista l1 = new Lista();
        if (this.raiz != null) {
            listarDatosAux(this.raiz, l1);
        }
        return l1;
    }

    private void listarDatosAux(NodoAVLDicc n, Lista l1) {
        if (n != null) {
            l1.insertar(n.getDato(), l1.longitud() + 1);

            if (n.getHijoIzquierdo() != null) {
                listarClavesAux(n.getHijoDerecho(), l1);
            }
            if (n.getHijoDerecho() != null) {
                listarClavesAux(n.getHijoDerecho(), l1);
            }
        }
    }

    public boolean esVacio() {
        return this.raiz == null;
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

    private String llamadoStr(NodoAVLDicc n) {
        String cad = "";

        if (n != null) {
            cad = n.getClave().toString() + ", altura:(" + n.getAltura() + ")";

            NodoAVLDicc iz = n.getHijoIzquierdo();
            if (iz != null) {
                cad = cad + "  H.I:" + iz.getClave().toString();
            } else {
                cad = cad + "  H.I:-";
            }
            NodoAVLDicc der = n.getHijoDerecho();
            if (der != null) {
                cad = cad + "  H.D:" + der.getClave().toString();
            } else {
                cad = cad + "  H.D:-";
            }
            cad = "\n" + cad + llamadoStr(iz) + llamadoStr(der);
        }
        return cad;
    }

//Método realizado para TPO
    public Object recuperar(Comparable elemento) {
        Object retorno = null;
        if (this.raiz != null) {
            retorno = recuperarAux(this.raiz, elemento);
        }
        return retorno;
    }

    private Object recuperarAux(NodoAVLDicc n, Comparable elemento) {
        Object retorno = null;
        //si el nodo no es null
        if (n != null) {
            //si el elemento es ta en esta nodo retorno true
            if (n.getClave().compareTo(elemento) == 0) {
                retorno = n.getClave();
            } else {
                //sino busco si es menor a el nodo por HI
                if (n.getClave().compareTo(elemento) > 0) {
                    retorno = recuperarAux(n.getHijoIzquierdo(), elemento);
                } else {
                    //sino busco por el ID
                    retorno = recuperarAux(n.getHijoDerecho(), elemento);
                }
            }
        }
        return retorno;
    }

}
