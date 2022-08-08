/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuradedatos;

import java.util.ArrayList;
import lineales.dinamica.Lista;
import java.util.HashMap;

/**
 *
 * @author Tomas
 */
public class MapeoAMuchos {

    HashMap<Object, ArrayList> mapa;

    public MapeoAMuchos() {
        mapa = new HashMap();
    }

    public boolean asociar(Object ValorDominio, Object ValorRango) {

        boolean exito = true;
        if (mapa.containsKey(ValorDominio)) {

            if (mapa.get(ValorDominio).contains(ValorRango)) {
                exito = false;
            } else { 
                 mapa.get(ValorDominio).add(ValorRango);
                
            }
        } else {
            ArrayList nuevaLista = new ArrayList();
            mapa.put(ValorDominio, nuevaLista);
            if(ValorRango != null){
                nuevaLista.add(ValorRango);
            }
        }
        return exito;
    }

    public boolean desasociar(Object ValorDominio, Object ValorRango) {
        boolean exito = false;
        if (mapa.containsKey(ValorDominio)) {
            if (mapa.get(ValorDominio).contains(ValorRango)) {
                exito = true;
                mapa.get(ValorDominio).remove(ValorRango);

                if (mapa.get(ValorDominio).isEmpty()) {
                    mapa.remove(ValorDominio);
                }

            } else {
                System.out.println("El valor especifico del rango no se encuentra en el mapeo");
            }
        } else {
            System.out.println("No existe la línea");
        }
        return exito;
    }

    public Lista obtenerValor(Object ValorDominio) {
        Lista l = new Lista();
        if (mapa.containsKey(ValorDominio)) {
            l.insertar(mapa.get(ValorDominio), l.longitud() + 1);
        }
        return l;
    }

    public Lista obtenerConjuntoDominio() {
        Lista l = new Lista();
        Object key = mapa.keySet();
        l.insertar(key, l.longitud() + 1);
        
        return l;
    }
    
    public Lista obtenerConjuntoRango() {
        Lista l = new Lista();
        Object key = mapa.values();
        l.insertar(key, l.longitud() + 1);       
        return l;
    }
    
    public String toString() {
        String cad = "";
        for (Object key : mapa.keySet()) {
            cad = cad +"Linea: ["+ key + "] : estaciones por las que pasa ---> " + mapa.get(key) + "\n";
        }
        return cad;
    }
    
    public String muestraRangoAsociado(Object valorDominio){
        String cad = ""; 
        cad = cad+mapa.get(valorDominio);
        return cad; 
    }
    
    public boolean existeValor(Object valorDominio){
        boolean exito = false;
        if(mapa.containsKey(valorDominio)){
            exito = true;
        }
        return exito;
    }
    
    public boolean eliminaValorDom(Object valorDominio){
        boolean exito = false;
        if(mapa.containsKey(valorDominio)){
            mapa.remove(valorDominio); 
            exito = true;
        }
        return exito; 
    }
    
}
