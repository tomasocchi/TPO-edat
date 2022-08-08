/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamica;

/**
 *
 * @author Tomas
 */
public class Cola {

//Atributos
    private Nodo fin;
    private Nodo frente;

//Constructores    
    public Cola() {
        fin = null;
        frente = null;
    }

    public boolean poner(Object nuevoElem) {
        boolean exito;
        if (esVacia()) {
            Nodo nuevo = new Nodo(nuevoElem, null);
            this.frente = nuevo;
            this.fin = nuevo;
            exito = true;
        } else {
            Nodo nuevo = new Nodo(nuevoElem, null);
            this.fin.setEnlace(nuevo);
            this.fin = nuevo;
            exito = true;
        }
        return exito;
    }

    public boolean sacar() {
        boolean exito = true;

        if (esVacia()) {
            exito = false;

        } else {

            this.frente = this.frente.getEnlace();
            if (this.frente == null) {
                this.fin = null;
            }
        }
        return exito;
    }

    public boolean esVacia() {
        boolean exito = false;
        if (this.frente == null) {
            exito = true;
        }
        return exito;
    }

    public String toString() {
        //Método toString: realiza un llamado a un método recursivo denominado llamadoStringR, una vez ejecutado este método retorna la cadena 
        //Si la pila es vacía, retorna un aviso que la pila se encuentra vacia
        String s = "";

        if (this.frente == null) {
            s = "Cola vacia";
        } else {
            s = llamadoStringR(this.frente);
        }
        return s;
    }

    private String llamadoStringR(Nodo top) {
        //Metodo recursivo llamadoStringR: método que recibe por parámetro el tope de una pila y luego concatena en una cadena sus valores de forma recursiva
        //Una vez llegada la referencia del tope a null, copia el primer elemento y inicia la vuelta de la recursión
        String str = "";

        if (top.getEnlace() != null) {
            str = top.getElem().toString() + "" + llamadoStringR(top.getEnlace());
        } else {
            str = top.getElem().toString();
        }
        return str;
    }

    public Object obtenerFrente() {
        Object frente;
        if (!esVacia()) {
            frente = this.frente.getElem();
        } else {
            frente = null;
        }
        return frente;
    }

    public void vaciar() {
        if (!esVacia()) {
            this.fin = null;
            this.frente = null;
        }

    }

    public Cola clone() {
        Cola clon = new Cola();
        Nodo aux1 = this.frente;
        clon.frente = new Nodo(aux1.getElem(), null);
        aux1 = aux1.getEnlace();
        Nodo aux2 = clon.frente;

        while (aux1 != null) {
            aux2.setEnlace(new Nodo(aux1.getElem(), null));
            aux2 = aux2.getEnlace();
            aux1 = aux1.getEnlace();
        }
        clon.fin = aux2;
        return clon;
    }

}
