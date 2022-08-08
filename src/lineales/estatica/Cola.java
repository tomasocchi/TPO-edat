package lineales.estatica;

/**
 *
 * @author Tomas
 */
public class Cola {

    private Object[] arreglo;
    private int frente;
    private int fin;
    private static final int TAMANIO = 10;

    public Cola() {
        this.arreglo = new Object[this.TAMANIO];
        this.frente = 0;
        this.fin = 0;
    }

    public boolean poner(Object nuevoElem) {
        boolean exito = true;

        if ((this.fin + 1) % this.TAMANIO == this.frente) {
            exito = false;
        } else {
            this.arreglo[this.fin] = nuevoElem;
            this.fin = (this.fin + 1) % this.TAMANIO;
        }
        return exito;
    }

    public boolean sacar() {
        boolean exito = true;

        if (this.esVacia()) {
            exito = false;
        } else {
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % this.TAMANIO;
        }
        return exito;
    }

    public boolean esVacia() {
        boolean exito = true;
        if (this.frente != this.fin) {
            exito = false;
        }
        return exito;
    }

    public String toString() {
        String str = "";
        int i = this.frente;
        str += "[";
        while (i != this.fin) {
            str += this.arreglo[i] + "";
            i = (i + 1) % TAMANIO;
        }
        str += "]";

        return str;
    }

    public Object obtenerFrente() {
        Object frente;
        if (this.frente == this.fin) {
            frente = "Cola Vacia";
        } else {
            frente = this.arreglo[this.frente];
        }
        return frente;
    }

    public void vaciar() {
        while (this.frente != this.fin) {
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % this.TAMANIO;
        }
    }

    public Cola clone() {
        Cola unaCola = new Cola();
        unaCola.frente = this.frente;
        unaCola.fin = this.fin;
        if (!this.esVacia()) {
            unaCola.arreglo = this.arreglo.clone();
        }
        return unaCola;
    }

}
