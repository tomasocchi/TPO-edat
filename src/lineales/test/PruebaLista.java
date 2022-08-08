package lineales.test;

import java.util.Scanner;
import lineales.dinamica.Lista;
import lineales.dinamica.Cola;
import lineales.dinamica.Pila;

public class PruebaLista {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char continuar;
        Lista unaLista, unaLista2;
        unaLista = new Lista();
        unaLista2 = new Lista();

        System.out.println("Desea realizar alguna operacion? 'S' si 'N' no");
        continuar = sc.next().charAt(0);
        do {
            System.out.println("Que operacion desea realizar?"
                    + "\n  1: metodo concatenar"
                    + "\n  2: metodo comprobar"
                    + "\n  3: metodo invertir");
            int n = sc.nextInt();

            switch (n) {
                case 1:
                    unaLista.insertar(1, 1);
                    unaLista.insertar(2, 2);
                    unaLista.insertar(3, 3);
                    unaLista2.insertar(4, 1);
                    unaLista2.insertar(5, 2);
                    unaLista2.insertar(6, 3);
                    concatenar(unaLista, unaLista2);
                    break;
                case 2:
                    unaLista.insertar(1, 1);
                    unaLista.insertar(2, 2);
                    unaLista.insertar(0, 3);
                    unaLista.insertar(1, 4);
                    unaLista.insertar(2, 5);
                    unaLista.insertar(0, 6);
                    unaLista.insertar(2, 7);
                    unaLista.insertar(3, 8);
                    boolean c = comprobar(unaLista);
                    System.out.println(c);
                    break;
                case 3:
                    unaLista2.insertar(4, 1);
                    unaLista2.insertar(5, 2);
                    unaLista2.insertar(6, 3);
                    invertir(unaLista);
                    break;
            }
            System.out.println("Desea seguir realizando alguna operacion? 'S' si 'N' no");
            continuar = sc.next().charAt(0);
        } while (continuar == 'S');
    }

    public static void concatenar(Lista lis1, Lista lis2) {
        Lista conca = new Lista();
        int i = 1;
        int j = 1;
        int longi = lis1.longitud() + lis2.longitud();
        int longi2 = lis2.longitud();
        while (i <= longi) {
            if (i <= longi - longi2) {
                conca.insertar(lis1.recuperar(i), i);
                i++;
            } else {
                conca.insertar(lis2.recuperar(j), i);
                j++;
                i++;
            }
        }
        System.out.println("Lista 1: " + lis1.toString() + " Lista 2: " + lis2.toString());
        System.out.println("Listas concatenadas: " + conca.toString());
    }

  public static boolean comprobar(Lista lista){
        Cola colaAux=new Cola ();
        Pila pilaAux= new Pila ();
        int i=1, elem, longitud=lista.longitud();
        boolean formato=false;
        //recupero el primer elemento
        elem=(int)lista.recuperar(i);
        //mientras no encuentro un 0 y no me paso de la longitud de la lista pongo en la cola y en la pila
        while ( (elem!=0) && (i<longitud) ){
            colaAux.poner(elem);
            pilaAux.apilar(elem);
            i++;
            elem=(int)lista.recuperar(i);
        }
        //si el elemento es igual a 0 entonces sigo para comparar los elementos de la lista con los de la cola
        if (elem==0){
           i++;
           while ( (i<=longitud) && (!colaAux.esVacia()) && (colaAux.obtenerFrente()==lista.recuperar(i)) ){
               colaAux.sacar();
               i++;
           }
           if ((i<= longitud) && ((int)lista.recuperar(i)==0)){
               i++;
                while ((i<=longitud) && (!pilaAux.esVacia()) && (pilaAux.obtenerTope()== lista.recuperar(i)) ){
                    pilaAux.desapilar();
                    i++;
                }
                if ((i>longitud) && pilaAux.esVacia()){
                    formato=true;
                }

           }

        }
        return formato; 
 }
    
    public static Pila ponerElem(Cola col){
        Pila pil = new Pila();
        while (!col.esVacia()){
            pil.apilar(col.obtenerFrente()); 
            col.sacar(); 
        }
        return pil; 
    }

    public static void invertir(Lista lis) {
        Lista inv = new Lista();
        int longi = lis.longitud() + 1;
        int i = 1;
        while (i < longi) {
            inv.insertar(lis.recuperar(longi - i), i);
            i++;
        }
        System.out.println("Lista original: " + lis.toString());
        System.out.println("Lista invertida: " + inv.toString());
    }

}
