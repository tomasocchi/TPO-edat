
package lineales.dinamica;

public class Pila {
    
    //Atributo
    //tope: de tipo Nodo, indica el índice del último valor en la pila 
    private Nodo tope;
   
    
    //Constructor
    public Pila() {
        this.tope = null;
    }
    
    //Modificadoras
    public boolean apilar(Object nuevoElemento) {
    //Método apilar: crea y enlaza nodos los cuales poseen el objeto ingresado por el usuario y como enlace el ultimo valor que tiene el tope
    //Si la pila estaba vacía, el primer nodo ingresado quedará apuntando a null
    //El primer valor del tope es null, luego una vez ingresado un nodo, esté apuntará al nodo nuevo 
        Nodo nuevo = new Nodo(nuevoElemento, this.tope);
        this.tope = nuevo;
        return true;
    }

    public boolean desapilar() {
    //Método desapilar: elimina los elementos de la pila cambiando la referencia del tope al enlace anterior de donde se encontraba
    //Si se elimina el ultimo valor de la pila, el tope queda apuntando a null, por lo que no se podría seguir eliminando más elementos
        boolean exito;

        if (this.tope != null) {
            this.tope = this.tope.getEnlace();
            exito = true;
        } else {
            exito = false;
        }
        return exito;
    }

    public String toString() {
    //Método toString: realiza un llamado a un método recursivo denominado llamadoStringR, una vez ejecutado este método retorna la cadena 
    //Si la pila es vacía, retorna un aviso que la pila se encuentra vacia
        String s = "";

        if (this.tope == null) {
            s = "Pila vacia";
        } else {
            s = "["+llamadoStringR(this.tope)+"]";
        }
        return s;
    }
    private String llamadoStringR(Nodo top){
    //Metodo recursivo llamadoStringR: método que recibe por parámetro el tope de una pila y luego concatena en una cadena sus valores de forma recursiva
    //Una vez llegada la referencia del tope a null, copia el primer elemento y inicia la vuelta de la recursión
        String str = "";
        
        if(top.getEnlace()!= null){
            str = llamadoStringR(top.getEnlace())+","+top.getElem().toString();
        }else{
            str = top.getElem().toString();
        }
        return str;
    }

    public boolean esVacia() {
    //Metodo esVacia: verifica si una pila es vacia o no
    //Si el tope apunta a la referencia null, entonces la pila está vacía
        boolean exito;
        if (this.tope == null) {
            exito = true;
        } else {
            exito = false;
        }
        return exito;
    }

    public Object obtenerTope() {
    //Metodo obetenerTope: retorna el valor del objecto donde se encuentra el tope
    //Si la referencia el tope es distinta de null el valor retornado es un Objecto, de lo contrario, el valor retornado es null
        Object ele;
        if (this.tope == null) {
            ele = null;
        } else {
            ele = this.tope.getElem();
        }
        return ele;
    }

    public void vaciar() {
    //Método vaciar: corta todos los enlaces de los nodos que tenía la pila, asignando la referencia del tope a null
        this.tope = null;
    }

    
     public Pila clone() {
    //Método clone: crea una pila nueva que poseerá los mismos valores que la pila original 
    //Realiza la acción de clonar llamando a un método privado, pasando por parámetro un nodo que tendrá el valor y la referencia del tope de la pila original más la nueva pila vacía     
        Pila clon = new Pila();
        Nodo aux = this.tope;
        cloneRecursivo(aux,clon);
        return clon;
    }  
    private void cloneRecursivo(Nodo topeAux, Pila clon){
    //Método cloneRecursivo: copia los valores de la pila original en una nueva pila de forma recursiva
    //A cada vuelta de la recursion, le va enlazando al tope un nuevo nodo
    
        if(topeAux != null){
            cloneRecursivo(topeAux.getEnlace(),clon);
            clon.tope = new Nodo(topeAux.getElem(),clon.tope);
        }
    }
    
    public void equals(Pila p){
       
        boolean ex = equalsRec(this.tope, p.tope); 
        System.out.println(ex);
        
    }
    private boolean equalsRec(Nodo n, Nodo n2){
        boolean exito = true; 
        if(n != null && n2 != null){
            exito = n.getElem().equals(n2.getElem()); 
            if(exito){
                exito = equalsRec(n.getEnlace(),n2.getEnlace()); 
            }
        }else if(n == null && n2 == null){
            exito = true;
        }else{
            exito = false; 
        }
        return exito; 
    }
}
