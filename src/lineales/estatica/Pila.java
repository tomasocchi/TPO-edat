
//Implementacion Pila estatica

package lineales.estatica;
    
public class Pila {

    //Atributos privados: 
    //  arreglo: elemento de la pila donde se guardan los valores de la misma
    //  tope: atributo de tipo int, indica el índice del ultimo valor o bien -1 si la pila es vacía
    //  TAMANIO: establece el tamaño máximo que tendrá la pila
    private Object[] arreglo;
    private int tope;
    private static final int TAMANIO = 10;

    //Constructor
    public Pila() {
        this.arreglo = new Object[TAMANIO];
        this.tope = -1;
    }

    //Modificadores 
    public boolean apilar(Object nuevoElem) {
    //Método apilar: recibe por parámetro un Objeto el cuál apilará en una cierta posición en el arreglo dependiendo de la posicion del tope
    //Si el tope excede las posiciones posibles del arreglo, no podrá apilar más elementos
        boolean exito;
  
        if (this.tope + 1 >= this.TAMANIO) {
            exito = false;
        } else {
            this.tope++;
            this.arreglo[tope] = nuevoElem;
            exito = true;
        }
        return exito;
    }

    
    public boolean desapilar() {
    //Método desapilar: Este método va quitando valores del arreglo y asignando el valor null en las pocisiones. También va decrementando el tope
    //Si se quita el ultimo elemento posible del arreglo, el módulo dará un error de pila vacía
        boolean exito;
        
        if (this.tope == -1) {
            exito = false;
        } else {
            this.arreglo[tope] = null;
            this.tope--;
            exito = true;
        }
        return exito;
    }

    
    public Object obtenerTope() {
    //Método obtenerTope: devuelve el valor del objeto que se encuentra en la posicion del tope
    //Si la pila es vacia, el valor retornado es null
        Object num;
        if (this.tope == -1) {
            num = null;
        } else {
            num = this.arreglo[tope];
        }
        return num;
    }

    
    public boolean esVacia() {
    //Método esVacia: Verifica si una pila es vacía dependiendo de la poscición del tope
    //Si el tope es -1 la pila está vacía    
        boolean exito;
        if (this.tope == -1) {
            exito = true;
        } else {
            exito = false;
        }
        return exito;
    }

    
    public void vaciar() {
    //Método vaciar: asigna valor null a todas posiciones de la pila donde se encuentre un Objeto, a su vez, va decrementando el tope hasta llegar al valor -1
        while (this.tope != -1) {
            this.arreglo[tope] = null;
            tope--;
        }
    }

    
    public String toString() {
    //Método toString: recorre toda la pila concatenando los valores del arreglo en una cadena desde la primera posicion hasta la ultima 
    //Si el valor del tope es -1, la cadena retornará un mensaje de pila vacia
        String str = "";
        int longi = this.tope;
        
        if (this.tope == -1) {
            str = "Pila vacia";
        } else {
                str += "[";
            for (int i = 0; i <= longi; i++) {
                str += this.arreglo[i];
                if(i < longi){
                    str += ",";
                }
            }
                str += "]";
        }
        return str;
    }

    
    public Pila clone() {
    //Método clone: crea una nueva pila la cual copia el mismo tope de la pila original, también copia el arreglo de la pila original en la nueva por referencia
    //Si la pila original es vacía, la pila clone no copiará su arreglo 
        Pila clone = new Pila();
        clone.tope = this.tope;
        if (!this.esVacia()) {
            clone.arreglo = this.arreglo.clone();
        }
        return clone;
    }
}
