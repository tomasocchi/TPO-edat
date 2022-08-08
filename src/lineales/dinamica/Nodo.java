
package lineales.dinamica;

 class Nodo {
    
    private Object elem;
    private Nodo enlace;
    
    //Constructor
    public Nodo(Object ele, Nodo enl){
        this.elem = ele;
        this.enlace = enl;
    }
    
    //Modificadoras
    public void setElem(Object ele){
        this.elem = ele;
    }
    
    public void setEnlace(Nodo enl){
        this.enlace = enl;
    }
    
    //Observadoras
    public Object getElem(){
        return elem;
    }
    
    public Nodo getEnlace(){
        return enlace;
    }
    
    
}
