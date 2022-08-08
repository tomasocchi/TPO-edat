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
public class Tren implements Comparable {

//Atributos privados 
    private int codigoNumerico;
    private String tipoPropulsion;
    private int cantVagones;
    private int cantPasajeros;
    private String lineaAsiganda;

//Constructores 
    public Tren(int codNum) {
        this.codigoNumerico = codNum;
    }

    public Tren(int codNum, String tipoProp, int cantV, int cantP, String lineaAsig) {
        this.codigoNumerico = codNum;
        this.tipoPropulsion = tipoProp;
        this.cantVagones = cantV;
        this.cantPasajeros = cantP;
        this.lineaAsiganda = lineaAsig;
    }

//Observadoras 
    public int getCodigoNumerico() {
        return this.codigoNumerico;
    }

    public String GetTipoPropulsion() {
        return this.tipoPropulsion;
    }

    public int getCantVagones() {
        return this.cantVagones;
    }

    public int getCantPasajeros() {
        return this.cantPasajeros;
    }

    public String getLineaAsiganda() {
        return this.lineaAsiganda;
    }

    public Object getDatos() {
        return "Codigo identificador: " + this.codigoNumerico + ", Tipo de propulsion: " + this.tipoPropulsion + ", Cantidad de vagones: " + this.cantVagones + ", Cantidad de pasajeros: " + this.cantPasajeros + ", Linea asignada: " + this.lineaAsiganda;
    }

//Modificadoras 
    public void setTipoPropulsion(String tipoProp) {
        this.tipoPropulsion = tipoProp;
    }

    public void setCantVagones(int cantV) {
        this.cantVagones = cantV;
    }

    public void setCantPasajeros(int cantP) {
        this.cantPasajeros = cantP;
    }

    public void setLineaAsignada(String lineaAsig) {
        this.lineaAsiganda = lineaAsig;
    }

    public String toString() {
        String cad = "";
        return cad +"Tren n°:"+this.codigoNumerico;
    }

    public int compareTo(Object comp) {
        int resultado = 0;
        Tren t = (Tren) comp;

        if (this.codigoNumerico == t.codigoNumerico) {
            resultado = 0;
        } else {
            if (this.codigoNumerico < t.codigoNumerico) {
                resultado = -1;
            } else if (this.codigoNumerico > t.cantPasajeros) {
                resultado = 1;
            }
        }
        return resultado;
    }

}
