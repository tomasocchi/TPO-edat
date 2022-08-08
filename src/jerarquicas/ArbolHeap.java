/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas;

/**
 *
 * @author Tomas
 */
public class ArbolHeap {

    private int TAMANIO = 20;
    private Comparable[] heap;
    private int ultimo = 0;

    public ArbolHeap() {
        this.ultimo = 0;
        this.heap = new Comparable[TAMANIO];
    }

    public boolean insertar(Comparable objeto) {
        boolean exito = true;

        // condicion arreglo lleno
        if (this.ultimo + 1 > this.TAMANIO) {
            exito = false;
        } else {
            this.heap[++this.ultimo] = objeto;
            int pos = ultimo;
            
            while(pos >1 && this.heap[pos].compareTo(this.heap[pos/2])<0){
                Comparable aux = this.heap[pos]; 
                this.heap[pos] = this.heap[pos/2]; 
                this.heap[pos/2] = aux;
                pos = pos/2;
            }
             
        }
        return exito;
    }

    public boolean eliminarCima() {
        boolean exito;
        if (this.ultimo == 0) {
            // estructura vacia
            exito = false;
        } else {
            // saca la raiz y pone la ultima hoja en su lugar
            this.heap[1] = this.heap[ultimo];
            this.ultimo--;
            // reestablece la propiedad de heap minimo
            hacerBajar(1);
            exito = true;
        }
        return exito;
    }

    private void hacerBajar(int posPadre) {
        int posH;
        Comparable temp = this.heap[posPadre];
        boolean salir = false;

        while (!salir) {
            posH = posPadre * 2;
            if (posH <= this.ultimo) {
                //temp tiene al menos un hijo (izq) y lo considera menor

                if (posH < this.ultimo) {
                    //hijoMenor tiene hermano derecho

                    if (this.heap[posH + 1].compareTo(this.heap[posH]) < 0) {
                        // el hijo derecho es el menor de los dos
                        posH++;
                    }
                }
                // compara al hijo menor con el padre
                if (this.heap[posH].compareTo(temp) < 0) {
                    // el hijo es menor que el padre, los intercambia
                    this.heap[posPadre] = this.heap[posH];
                    this.heap[posH] = temp;
                    posPadre = posH;
                } else {
                    // el padre es menor que sus hijos, esta bien ubicado
                    salir = true;
                }
            } else {
                // el temp es hoja, esta bien ubicado
                salir = true;
            }
        }
    }

    public String toString() {
        String s = "";
        if (this.ultimo == 0) {
            s = "Arbol vacio";
        } else {
            s += stringAux(1);
        }
        return s;
    }

    private String stringAux(int posPadre) {
        int aux = 1;
        String s = "";
        while (aux <= this.ultimo) {
            s += this.heap[aux];
            s += "\n";
            s += "HI:" + this.heap[((aux * 2))] + "   HD:" + this.heap[(aux * 2) + 1];
            s += "\n";
            aux++;
        }
        return s;
    }

    public Comparable recuperaCima() {
        Comparable el;
        if (this.heap[0] != null) {
            el = this.heap[0];
        } else {
            el = null;
        }
        return el;
    }

    public boolean esVacio() {
        return this.heap[0] == null;
    }

    public ArbolHeap clone() {
        ArbolHeap clon = new ArbolHeap();
        int aux = 0;
        while (aux + 1 <= this.ultimo) {
            clon.heap[clon.ultimo] = this.heap[aux];
            clon.ultimo++;
            aux++;
        }
        return clon;
    }

    public void vaciar() {
        int aux = 0;
        while (this.heap[aux] != null && aux + 1 <= this.ultimo) {
            this.heap[aux] = null;
            aux++;
        }
        this.ultimo = 0;
    }
}
