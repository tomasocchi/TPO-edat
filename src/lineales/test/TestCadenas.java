/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.test;

import lineales.dinamica.Cola;
import lineales.dinamica.Pila;
import lineales.dinamica.Lista;
import java.util.Scanner; 
/**
 *
 * @author Tomas
 */
public class TestCadenas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        Cola c1 = new Cola();
        c1.poner(0);
        c1.poner(1);
        c1.poner(2);
        c1.poner(3);
        c1.poner(4);
        c1.poner(5);
        c1.poner(6);
        c1.poner(7);
        c1.poner(8);
        c1.poner(9);
    
        
        
        /*
        c1.poner('/');
        c1.poner('2');
        c1.poner(')');
        c1.poner('+');
        c1.poner('7');
        c1.poner(']');
        c1.poner('-');
        c1.poner('1');
        c1.poner('}');
        */
        Lista unaLis = new Lista();         
        int m = cuentaSecuencias(c1); 
        System.out.println(unaLis.toString());
        Pila p1 = new Pila(); 
        Pila p2 = new Pila(); 
       
        /*
        p1.apilar(1);
        p1.apilar(2);
        p1.apilar(3);
        p1.apilar(4);
        p1.apilar(5);
        p2.apilar(1);
        p2.apilar(2);
        p2.apilar(3);
        p2.apilar(4);
        p2.apilar(5);
        
       
       p1.equals(p2);
       */      
    }

    /* 
       boolean ex =  verificaBalanceo(c1);
        System.out.println(ex);
         /*      
       
        

        }

    public static Cola generar(Cola c1) {
        //Metodo que devuelve una cola con el formato cc´c#
        Cola aux = new Cola();
        Pila pilAux = new Pila();
        Cola conca = new Cola();
        Cola clon = c1.clone();
        char elem = ' ';
        //Realiza un unico recorrido de la cola original
        while (!clon.esVacia()) {
            elem = (char) clon.obtenerFrente();
            if (elem != '#') {
                aux.poner(elem);
                pilAux.apilar(elem);
                conca.poner(elem);
            } else {
                conca.poner(armaCad(aux, pilAux));
                conca.poner('#');
            }
            //elimina el frente de la cola clon
            clon.sacar();
        }
        //genera cadena para la ultima aparicion de caracteres despues del ultimo numeral
        conca.poner(armaCad(aux, pilAux));
        return conca;
    }

    public static Cola armaCad(Cola aux, Pila pilAux) {
        Cola colaAux = new Cola();
        while (!pilAux.esVacia()) {
            colaAux.poner(pilAux.obtenerTope());
            pilAux.desapilar();
        }
        while (!aux.esVacia()) {
            colaAux.poner(aux.obtenerFrente());
            aux.sacar();
        }
        return colaAux;
    }

    /*
    public static Cola generar(Cola c1) {
        //Metodo que devuelve una cola con el formato cc´c#
        Cola aux = new Cola();
        Pila pilAux = new Pila();
        Cola conca = new Cola();
        Cola clon = c1.clone(); 
        char elem = ' ';
        //Realiza un unico recorrido de la cola original
        while (!clon.esVacia()) {
            elem = (char) clon.obtenerFrente();
            aux.poner(elem);
            pilAux.apilar(elem);
            //Si se encuentra un numeral, llamada a un metodo para generar la cadena aa'a
            if (elem == '#') {
            conca.poner(armaCad(aux,pilAux));              
            }           
            //elimina el frente de la cola clon
            clon.sacar(); 
        }
        
          //genera cadena para la ultima aparicion de caracteres despues del ultimo numeral
          conca.poner(armaCad(aux,pilAux));  

        return conca;
    }

    public static Cola armaCad(Cola aux, Pila pilaux) {
        
        Cola ret = new Cola();
        char busca;
        Cola copia = new Cola();
        copia = aux.clone();

        //Primer llamado cola
        while (!copia.esVacia()) {
            busca = (char) copia.obtenerFrente();
            if (busca != '#') {
                ret.poner(busca);
            }
            copia.sacar();
        }
       
        //Llamado Pila
        while (!pilaux.esVacia()) {
            busca = (char) pilaux.obtenerTope();
            if (busca != '#') {
                ret.poner(busca);
            }
            pilaux.desapilar();
        }

        //2do llamado cola
        while (!aux.esVacia()) {
            busca = (char) aux.obtenerFrente();
            ret.poner(busca);
            aux.sacar();
        }

        return ret;
    }
     */
    /*
    public static boolean verificaBalanceo(Cola q) {
        Cola clon = new Cola();
        clon = q.clone();
        Pila almacena = new Pila();
        Cola verifica = new Cola();
        boolean exito = false;
        boolean esValida = true;
        char simbolo = ' ';

        while (!clon.esVacia() && !(exito)) {
            simbolo = (char) clon.obtenerFrente();
            if (condicionSimbolo(simbolo)) {
               if(condicionApertura(simbolo)){
                   almacena.apilar(simbolo);
                   exito = true; 
               }else{
                   esValida = false;
                   exito = true;
               }
            }
            clon.sacar(); 
        }
        
        while(!clon.esVacia() && (esValida)){
            simbolo = (char) clon.obtenerFrente();
            if(condicionSimbolo(simbolo)){
                if(condicionCierre(simbolo)){
                    verifica.poner(simbolo); 
                    if(condicionBalance((char)almacena.obtenerTope(), (char)verifica.obtenerFrente())){
                        almacena.desapilar();
                        verifica.sacar(); 
                    }else{
                        esValida = false; 
                    }
                }else if(condicionApertura(simbolo)){
                    almacena.apilar(simbolo);                     
                }else{
                 esValida = false;    
                }
            }
            clon.sacar(); 
        }
        
        return esValida; 
    
    }

    public static boolean condicionSimbolo(char c) {
        boolean exito = false;
        switch (c) {
            case '[':
                exito = true;
                break;
            case '{':
                exito = true;
                break;
            case '(':
                exito = true;
                break;
            case ']':
                exito = true;
                break;
            case '}':
                exito = true;
                break;
            case ')':
                exito = true;
                break;
        }
        return exito;
    }

    public static boolean condicionApertura(char c) {
        boolean exito = false;
        switch (c) {
            case '[':
                exito = true;
                break;
            case '{':
                exito = true;
                break;
            case '(':
                exito = true;
                break;

        }
        return exito;
    }
    
    public static boolean condicionCierre(char c){
        boolean exito = false;
        switch (c){
             case ']':
                exito = true;
                break;
            case '}':
                exito = true;
                break;
            case ')':
                exito = true;
                break;
        }
        return exito; 
    }
    
    public static boolean condicionBalance(char a, char c){
        boolean exito = true; 
        switch (a) {
             case '[':
                 if(c != ']'){
                     exito = false; 
                 }
                 break; 
             case '{':
                 if(c != '}'){
                     exito = false; 
                 }
                 break;
             case '(': 
                 if(c != ')'){
                     exito = false; 
                 }
                 break;
        }
        return exito; 
    }
    */
    /*
    public static Lista generaSecuencia(Cola q1, int t){
        Cola clon = new Cola(); 
        clon = q1.clone(); 
        Cola aux = new Cola();
        Pila aux2 = new Pila(); 
        Lista sec = new Lista(); 
        int cont = 1; 
        int cont2 = 1; 
        while(!clon.esVacia()){
            aux.poner((int)clon.obtenerFrente());
            aux2.apilar((int)clon.obtenerFrente()); 
            if(cont % t == 0){
                sec.insertar(generaSec(aux,aux2), cont2); 
                cont2++;
            }
            clon.sacar(); 
            cont++;
        }
        sec.insertar(generaSec(aux,aux2), cont2);
        
        return sec;
    }
    
    
    public static Lista generaSec(Cola c, Pila p){
        Lista l = new Lista();
        int i = 1; 
        while(!p.esVacia()){
           l.insertar(p.obtenerTope(), i);
           p.desapilar();
           i++;
        }
     
        while(!c.esVacia()){
            l.insertar(c.obtenerFrente(), i);
            c.sacar();
            i++;
        }
       
    return l; 
    }
*/
    
    public static int cuentaSecuencias(Cola c1){
        Pila aux = new Pila();
        Cola aux2 = new Cola(); 
        int cuenta = 0; 
        Cola clon = new Cola(); 
        int cont = 0; 
        clon = c1.clone(); 
        char simbolo = ' '; 
        while (!clon.esVacia()){
            simbolo = (char)clon.obtenerFrente(); 
            if(simbolo != '$'){
                aux.apilar(simbolo); 
                aux2.poner(simbolo);                
            }else{
                cuenta += cuentaCapi(aux,aux2); 
            }
               clon.sacar(); 
        }        
        cuenta += cuentaCapi(aux,aux2); 
        
    return cuenta; 
   
}
    
    public static int cuentaCapi(Pila p, Cola c){
        int ret = 0;
        boolean exito = true; 
        while(!p.esVacia() && exito){
            if(!p.obtenerTope().equals(c.obtenerFrente())){
                exito = false; 
            }
            p.desapilar(); 
            c.sacar(); 
        }
        
        if(exito){
            ret = 1; 
        }else{
            p.vaciar();
            c.vaciar();
        }             
        return ret; 
    }    
}
