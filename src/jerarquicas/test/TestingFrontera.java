
package jerarquicas.test;
import lineales.dinamica.Lista;
import jerarquicas.ArbolGen;

/**
 *
 * @author Tomas y Davor 
 */
public class TestingFrontera {

    public static void main(String[] args) {
        System.out.println("TEST DE PRUEBA PARA EL MÉTODO sonFrontera():");
        System.out.println("CREAMOS EL ÁRBOL GENÉRICO:");
        ArbolGen unArbol = new ArbolGen();

        unArbol.insertar(1, null);
        unArbol.insertar(2, 1);
        unArbol.insertar(3, 1);
        unArbol.insertar(8, 1);
        unArbol.insertar(4, 2);
        unArbol.insertar(6, 2);
        unArbol.insertar(5, 3);
        unArbol.insertar(7, 5);
        System.out.println(unArbol.toString());
        
        System.out.println("--------------------------------------------------");
        System.out.println("PRIMER CASO: ELEMENTOS REPETIDOS");
        System.out.println("CREAMOS LISTA:");
        Lista lis = new Lista();
        lis.insertar(2, 1);
        lis.insertar(1, 2);
        lis.insertar(1, 3);
        lis.insertar(3, 4);
        System.out.println(lis.toString());
        boolean n = unArbol.sonFrontera(lis);
        System.out.println("DEBE DAR FALSE: ----->" + n);
        
        System.out.println("--------------------------------------------------");
        System.out.println("SEGUNDO CASO: ELEMENTOS QUE SON HOJAS");
        Lista lis1 = new Lista();
        lis1.insertar(6, 1);
        lis1.insertar(8, 2);
        lis1.insertar(7, 3);
        lis1.insertar(4, 4);
        System.out.println("CREAMOS LISTA:");
        System.out.println(lis1.toString());
        n = unArbol.sonFrontera(lis1);
        System.out.println("DEBE DAR TRUE: ---->" + n);
        
        System.out.println("--------------------------------------------------");
        System.out.println("TERCER CASO: ELEMENTOS NO HOJA CON ELEMENTOS HOJA");
        System.out.println("CREAMOS LISTA:");
        Lista lis2 = new Lista();
        lis2.insertar(1, 1);
        lis2.insertar(7, 2);
        lis2.insertar(5, 3);
        lis2.insertar(8, 4);
        System.out.println(lis2.toString());
        n = unArbol.sonFrontera(lis2);
        System.out.println("DEBE DAR FALSE: ----->" + n);
   
        System.out.println("--------------------------------------------------");
        System.out.println("CUARTO CASO: LISTA VACÍA");
        Lista lis3 = new Lista();
        System.out.println("CREAMOS LISTA:");
        System.out.println(lis3.toString());
        n = unArbol.sonFrontera(lis3);
        System.out.println("DEBE DAR FALSE: ----->" + n);
        
        System.out.println("--------------------------------------------------");
        System.out.println("QUINTO CASO: ELEMENTOS REPETIDOS QUE SON HOJAS");
        System.out.println("CREAMOS LISTA:");
        Lista lis4 = new Lista();
        lis4.insertar(4, 1);
        lis4.insertar(7, 2);
        lis4.insertar(8, 3);
        lis4.insertar(8, 4);
        System.out.println(lis4.toString());
        n = unArbol.sonFrontera(lis4);
        System.out.println("DEBE DAR FALSE: ----->" + n);
        
        System.out.println("--------------------------------------------------");
        System.out.println("SEXTO CASO: LISTA CON UN SOLO ELEMENTO HOJA");
        System.out.println("CREAMOS LISTA:");
        Lista lis5 = new Lista();
        lis5.insertar(7, 1);
        System.out.println(lis5.toString());
        n = unArbol.sonFrontera(lis5);
        System.out.println("DEBE DAR TRUE: ----->" + n);
        
        System.out.println("--------------------------------------------------");
        System.out.println("SEPTIMO CASO: ARBOL VACÍO CON LISTA");
        System.out.println("VACIAMOS ARBOL");
        unArbol.vaciar();
        System.out.println("CREAMOS LISTA:");
        Lista lis6 = new Lista();
        lis6.insertar(1, 1);
        lis6.insertar(7, 2);
        lis6.insertar(5, 3);
        lis6.insertar(8, 4);
        System.out.println(lis6.toString());
        n = unArbol.sonFrontera(lis6);
        System.out.println("DEBE DAR FALSE: ----->" + n);
        

    }

}
