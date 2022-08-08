/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamica;

public class Lista {

//Atributo    
    private Nodo cabecera;
    private int longitud;

//Constructor
    public Lista() {
        cabecera = null;
        this.longitud = 0;
    }

//Modificadoras
    public boolean insertar(Object nuevoElem, int pos) {
        // Inserta el elemento nuevo en la posicion pos
        // Detecta y reporta error posicion invalida
        boolean exito = true;

        if (pos < 1 || pos > this.longitud() + 1) {
            exito = false;
        } else {
            if (pos == 1) { // Crea un nuevo nodo y se enlaza a la cabecera
                this.cabecera = new Nodo(nuevoElem, this.cabecera);
                this.longitud++;
            } else { // Avanza hasta el elemento en posicion pos-1
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }

                // Crea el nodo y lo enlaza
                Nodo nuevo = new Nodo(nuevoElem, aux.getEnlace());
                this.longitud++;
                aux.setEnlace(nuevo);
            }
        }
        // Nueca hay error de lista llena, entonces devuelve true
        return exito;
    }

    public boolean eliminar(int pos) {
        // Elimina un elemento en la posicion deseada por el usuario
        // Esta posicion debe ser valida, de lo contrario el metodo retornara false
        boolean exito = true;

        //Condicion invalida
        if (pos < 1 || pos > longitud()) {
            exito = false;
        } else {
            //Caso especial 
            if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();
                this.longitud--;

            } else { // Avanza hasta la posicion pos-1
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                //Enlaza el nodo aux con la posicion siguiente a pos
                //El elemento de pos pierde en enlace por lo que queda liberado
                aux.setEnlace(aux.getEnlace().getEnlace());
                this.longitud--;
            }

        }
        return exito;
    }

    public Object recuperar(int pos) {
        // Devuelve el elemento en la posicion requerida por el usuario
        // Caso invalido retorna null
        Object elem;
        if (pos < 1 || pos > longitud()) {
            elem = null;
        } else { // Caso valido retorna elemento
            Nodo aux = this.cabecera;
            int i = 1;
            while (i < pos) {
                aux = aux.getEnlace();
                i++;
            }
            elem = aux.getElem();
        }
        return elem;
    }

    public int localizar(Object elem) {
        // Devuelve la posicion en la que se encuentra un determinado elemento 
        // Si el elemento no se encuenta en la lista, la posicion es -1
        int pos = -1;
        int i = 1;
        Nodo aux = this.cabecera;
        while (aux != null) {
            if (aux.getElem().equals(elem)) {
                pos = i;
            }
            aux = aux.getEnlace();
            i++;
        }
        return pos;
    }

    public void vaciar() {
        // Vacia la lista creada apuntando a la cabecera a null
        this.cabecera = null;
    }

    public Lista clone() {
        // Crea una copia de la lista original 
        // Condicion: la lista original no debe estar vacia, caso contrario devuelve la lista nueva vacia
        Lista clon = new Lista();

        if (this.cabecera != null) {
            // Crea la cabecera del clon
            clon.cabecera = new Nodo(this.cabecera.getElem(), null);
            Nodo aux = this.cabecera;
            Nodo aux2 = clon.cabecera;
            aux = aux.getEnlace();

            while (aux != null) {
                // Va creando las copias de los elemento de la lista original    
                aux2.setEnlace(new Nodo(aux.getElem(), null));
                aux2 = aux2.getEnlace();
                aux = aux.getEnlace();
            }
        }
        return clon;
    }

    public boolean esVacia() {
        // Retorna true si la lista es vacia, es decir cuando la cabecera es null
        // Caso contrario retorna false
        boolean exito = false;
        if (this.cabecera == null) {
            exito = true;
        }
        return exito;
    }

    public int longitud() {
        // Retorna la cantidad de elementos que posee la lista
        // Si la lista esta vacia, longitud es igual a 0
        return this.longitud;
    }

    public String toString() {
        //Método toString: realiza un llamado a un método recursivo denominado llamadoStringR, una vez ejecutado este método retorna la cadena 
        //Si la pila es vacía, retorna un aviso que la pila se encuentra vacia
        String s = "";

        if (this.cabecera == null) {
            s = "[]";
        } else {
            s = llamadoStringR(this.cabecera);
        }
        return s;
    }

    private String llamadoStringR(Nodo cab) {
        //Metodo recursivo llamadoStringR: método que recibe por parámetro el tope de una pila y luego concatena en una cadena sus valores de forma recursiva
        //Una vez llegada la referencia del tope a null, copia el primer elemento y inicia la vuelta de la recursión
        String str = "";

        if (cab.getEnlace() != null) {
            str = cab.getElem().toString() +", "+ llamadoStringR(cab.getEnlace());
        } else {
            str = cab.getElem().toString();
        }
        return str;
    }

    public Lista obtenerMultiplos(int num) {
        //Metodo que dado un numero entero devuelve una lista con los elementos ubicados en posiciones que son multiplos de dicho numero en la lista original

        //Creo la lista auxiliar mas nodos que se utilizaran en el recorrido
        Lista multi = new Lista();
        Nodo aux = this.cabecera;
        Nodo aux2 = null;
        int i = 1;
        //Condicion lista no vacia
        if (this.cabecera != null) {
            //Realizara el recorrido hasta que el nodo auxiliar sea nulo(inicia el recorrido en la posicion de la cabecera de la lista original)    
            while (aux != null) {

                //Consulto si la posicion es multiplo del numero insertado por el usuario
                if (i % num == 0) {
                    //Condicion primera posicion de la lista
                    if (multi.cabecera == null) {
                        multi.cabecera = new Nodo(aux.getElem(), null);
                        aux2 = multi.cabecera;
                    } else {
                        //Inserto elementos despues de la cabecera
                        aux2.setEnlace(new Nodo(aux.getElem(), null));
                        aux2 = aux2.getEnlace();
                        multi.longitud++;
                    }
                }
                aux = aux.getEnlace();
                i++;
            }
        }

        return multi;
    }
    
    public void eliminarApariciones(Object elem) {
        boolean control = true;
        // Primer caso, Apariciones en la cabecera
        while (control && this.cabecera != null) {
            if (this.cabecera.getElem().equals(elem)) {
                this.cabecera = this.cabecera.getEnlace();
                this.longitud--;
            } else {
                control = false;
            }
        }
        // LLamo al método recursivo
        eliminarAux(null, this.cabecera, elem);
    }

    private void eliminarAux(Nodo nodoAnt, Nodo nodoSig, Object x) {

        if (nodoSig != null) {

            // En caso de hallarlo, setea el enlace y disminuye la longitud.
            if (nodoSig.getElem().equals(x)) {
                nodoAnt.setEnlace(nodoSig.getEnlace());
                nodoSig = nodoSig.getEnlace();
                this.longitud--;
            }

            // Segimos recorriendo recursivamente hasta terminar la lista.
            if (nodoSig != null) {
                if (nodoSig.getElem().equals(x)) {
                    // Caso en que se repite
                    eliminarAux(nodoAnt, nodoSig, x);
                } else {
                    // si no se repite
                    eliminarAux(nodoSig, nodoSig.getEnlace(), x);
                }
            }

        }
    }

    public void agregarElem(Object nuevo, int x) {
        int cont = 1;
        if (this.cabecera != null) {
            this.cabecera = new Nodo(nuevo, this.cabecera);
            this.longitud++;
            Nodo aux = this.cabecera.getEnlace();
            
            while (aux != null) {
                if (cont % x == 0) {
                        Nodo nuevito = new Nodo(nuevo, aux.getEnlace());
                        aux.setEnlace(nuevito);
                        this.longitud++;
                        aux = aux.getEnlace().getEnlace(); 
                        cont = 1;
                    }else{
                aux = aux.getEnlace();
                cont++;
                }
            }
        }
    }
}
