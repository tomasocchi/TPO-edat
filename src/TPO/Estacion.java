/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPO;

/**
 *
 * @author Tomas
 */
public class Estacion implements Comparable{

//Atributos privados
    private String nombreEstacion;
    private String calle;
    private int numero;
    private String ciudad;
    private String codPostal;
    private int cantVias;
    private int cantPlataformas;

//Constructores 
    public Estacion(String nombreEst) {
        this.nombreEstacion = nombreEst;
    }

    public Estacion(String nombreEst, String calle, int num, String ciu, String codPost, int cantV, int cantP) {
        this.nombreEstacion = nombreEst;
        this.calle = calle;
        this.numero = num;
        this.ciudad = ciu;
        this.codPostal = codPost;
        this.cantVias = cantV;
        this.cantPlataformas = cantP;

    }

//Observadoras
    public String getNombreEstacion() {
        return this.nombreEstacion;
    }

    public String getCalle() {
        return this.calle;
    }

    public int getNumero() {
        return this.numero;
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public String getCodPostal() {
        return this.codPostal;
    }

    public int getCantVias() {
        return this.cantVias;
    }

    public int getCantPlataformas() {
        return this.cantPlataformas;
    }

    public Object getDatos() {
        return "Estacion: " + this.nombreEstacion + "; numero: " + this.numero + "; calle: " + this.calle + "; ciudad: " + this.ciudad + "; cod postal " + this.codPostal + "; cant vias: " + this.cantVias + "; cant plataformas: " + this.cantPlataformas;
    }
    
    public String toString(){
        String cad = ""; 
        return cad+"Estación: "+this.nombreEstacion; 
    }

//Modificadoras
    public void setCalle(String c) {
        this.calle = c;
    }

    public void setNumero(int num) {
        this.numero = num;
    }

    public void setCiudad(String city) {
        this.ciudad = city;
    }

    public void setCodPostal(String codP) {
        this.codPostal = codP;
    }

    public void setCantVias(int cantV) {
        this.cantVias = cantV;
    }

    public void setCantPlataformas(int cantP) {
        this.cantPlataformas = cantP;
    }
    
    public int compareTo(Object comp){
        Estacion otro = (Estacion) comp; 
        int res = 0;
        return this.nombreEstacion.compareTo(otro.nombreEstacion); 
    }
}
