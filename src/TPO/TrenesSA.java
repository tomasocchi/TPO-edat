package TPO;

import java.util.*;
import lineales.dinamica.*;
import TPO.*;
import conjuntistas.dinamicas.DiccionarioAVL;
import Grafos.*;
import java.io.*;

public class TrenesSA {

    private static DiccionarioAVL dicEstaciones = new DiccionarioAVL();
    private static DiccionarioAVL dicTrenes = new DiccionarioAVL();
    private static FileWriter salida;
    private static HashMap<Object, ArrayList> mapa = new HashMap();
    private static GrafoEtiq graf = new GrafoEtiq();

    static {
        try {
            salida = new FileWriter("‪cargalog.txt");
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        char continuar = 's';
        int opcion;

// MENU INICIAL        
        System.out.println("--------------------------------------------------------");
        System.out.println("Bienvenido al sistema de TrenesSA!");
        do {
            System.out.println("--------------------------------------------------------");
            System.out.println("Que operacion desea realizar? \n"
                    + "1- Carga inicial del sistema \n"
                    + "2- ABM (Altas-Bajas-Modificaciones) de trenes \n"
                    + "3- ABM (Altas-Bajas-Modificaciones) de estaciones \n"
                    + "4- ABM (Altas-Bajas-Modificaciones) de líneas \n"
                    + "5- ABM (Altas-Bajas-Modificaciones) de rieles \n"
                    + "6- Consulta sobre trenes \n"
                    + "7- Consulta sobre estaciones \n"
                    + "8- Consulta sobre viajes \n"
                    + "9- Mostrar sistema");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    cargaInicial();
                    break;
                case 2:
                    ABMs('T');
                    break;
                case 3:
                    ABMs('E');
                    break;
                case 4:
                    ABMs('L');
                    break;
                case 5:
                    ABMs('R');
                    break;
                case 6:
                    consultaTrenes();
                    break;
                case 7:
                    consultaEstaciones();
                    break;
                case 8:
                    consultaViajes();
                    break;
                case 9:
                    muestraSistema();
                    break;
            }
            System.out.println("");
            System.out.println("Menu Principal");
            System.out.println("Desea realizar otra operacion? 's' para SI, 'n' para NO");
            continuar = sc.next().charAt(0);
        } while (continuar == 's');

        System.out.println("Muchas gracias por utilizar el sistema de TrenesSA!");
        salida.close();
    }
//CARGA INICIAL

    public static void cargaInicial() throws IOException {
        System.out.println("--------------------------------------------------------");
        String file = "C:\\Users\\Tomas\\OneDrive\\Escritorio\\UNCO\\tpo\\tpo.txt";
        String[] parts;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                parts = line.split(";");
                carga(parts);
            }
        }
        salida.write("Se realizó la carga inicial \n");
        System.out.println("CARGA REALIZADA");
        System.out.println("--------------------------------------------------------");
    }

    public static boolean carga(String[] arr) {
        String a = arr[0];
        boolean exito = true;
        switch (a) {
            case "E":
                Estacion unaEst = new Estacion(arr[1]);
                exito = dicEstaciones.existeClave(unaEst);
                if (!exito) {
                    cargaDatosEstacion(unaEst, arr[2], Integer.parseInt(arr[3]), arr[4], arr[5], Integer.parseInt(arr[6]), Integer.parseInt(arr[7]));
                    graf.insertarVertice(unaEst.getNombreEstacion());
                }
                break;
            case "T":
                Tren unTren = new Tren(Integer.parseInt(arr[1]));
                exito = dicTrenes.existeClave(unTren);
                if (!exito) {
                    cargaDatosTren(unTren, arr[2], Integer.parseInt(arr[3]), Integer.parseInt(arr[4]), arr[5]);
                }
                break;
            case "L":
                int i = 2;
                while (i < arr.length) {
                    cargaDatosMapa(arr[1], arr[i]);
                    i++;
                }

                break;
            case "R":
                graf.insertarArco(arr[1], arr[2], Double.parseDouble(arr[3]));
                break;
        }
        return exito;
    }
//CARGA ESTRUCTURAS AVL Y HASHMAP

    public static void cargaDatosEstacion(Estacion e, String calle, int num, String ciudad, String codPostal, int cantV, int cantP) {
        e.setCalle(calle);
        e.setNumero(num);
        e.setCiudad(ciudad);
        e.setCodPostal(codPostal);
        e.setCantVias(cantV);
        e.setCantPlataformas(cantP);
        dicEstaciones.insertar(e, e.getDatos());
    }

    public static void cargaDatosTren(Tren t, String tipoP, int cantV, int cantP, String lineaAsig) {
        t.setTipoPropulsion(tipoP);
        t.setCantVagones(cantV);
        t.setCantPasajeros(cantP);
        t.setLineaAsignada(lineaAsig);
        dicTrenes.insertar(t, t.getDatos());
    }

    public static boolean cargaDatosMapa(String key, String v) {
        boolean exito = true;
        if (mapa.containsKey(key)) {
            if (mapa.get(key).contains(v)) {
                exito = false;
            } else {
                mapa.get(key).add(v);
            }
        } else {
            ArrayList nuevaLista = new ArrayList();
            mapa.put(key, nuevaLista);
            if (v != null) {
                nuevaLista.add(v);
            }
        }
        return exito;
    }

// MENU AMBs
    public static void ABMs(char tipo) throws IOException {
        System.out.println("--------------------------------------------------------");
        Scanner sc = new Scanner(System.in);
        System.out.println("Seleccione la opción a realizar \n"
                + "1- Altas  \n"
                + "2- Bajas  \n"
                + "3- Modificaciones ");

        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                altas(tipo);
                break;
            case 2:
                bajas(tipo);
                break;
            case 3:
                modificaciones(tipo);
                break;
        }
        System.out.println("--------------------------------------------------------");
    }

    public static void altas(char tipo) throws IOException {
        switch (tipo) {
            case 'T':
                altaTrenes();
                break;
            case 'E':
                altaEstaciones();
                break;
            case 'L':
                altaLineas();
                break;
            case 'R':
                altaRieles();
                break;
        }
    }

    public static void bajas(char tipo) throws IOException {
        switch (tipo) {
            case 'T':
                bajasTrenes();
                break;
            case 'E':
                bajasEstaciones();
                break;
            case 'L':
                bajaLineas();
                break;
            case 'R':
                bajaRieles();
                break;
        }
    }

    public static void modificaciones(char tipo) throws IOException {
        switch (tipo) {
            case 'T':
                modificacionesTrenes();
                break;
            case 'E':
                modificacionesEstaciones();
                break;
            case 'L':
                modificacionesLineas();
                break;
        }
    }

    public static boolean altaTrenes() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean exito;
        System.out.println("Ingrese el identificador numerico único del tren");
        int cod = sc.nextInt();
        Tren nuevoTren = new Tren(cod);
        exito = dicTrenes.existeClave(nuevoTren);
        if (!exito) {
            sc.nextLine();
            System.out.println("Ingrese el tipo de propulsion del tren");
            String prop = sc.nextLine();
            System.out.println("Ingrese la cantidad de vagones que posee el tren");
            int cantV = sc.nextInt();
            System.out.println("Ingrese la cantidad de pasajeros que puede llevar el tren");
            int cantP = sc.nextInt();
            sc.nextLine();
            System.out.println("Ingrese la línea asiganada");
            String lin = sc.nextLine();
            if (mapa.containsKey(lin)) {
                cargaDatosTren(nuevoTren, prop, cantV, cantP, lin);
            } else {
                cargaDatosTren(nuevoTren, prop, cantV, cantP, "no-asignado");
            }
            salida.write("Se creó el tren n°" + cod + " \n");
            System.out.println("Tren cargado con éxito!");
        } else {
            System.out.println("El tren ya se encuentra cargado en el sistema");
        }
        return exito;
    }

    public static void bajasTrenes() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean exito;
        System.out.println("Ingrese el identificador numerico unico del tren");
        int cod = sc.nextInt();
        Tren t = new Tren(cod);
        exito = dicTrenes.eliminar(t);
        if (!exito) {
            System.out.println("No existe el tren");
        } else {
            System.out.println("Se eliminó el tren con éxito!");
            salida.write("Se eliminó el tren n°" + cod + " \n");
        }
    }

    public static boolean modificacionesTrenes() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean exito;
        System.out.println("Ingrese el identificador numerico único del tren");
        int cod = sc.nextInt();
        Tren t = new Tren(cod);
        exito = dicTrenes.existeClave(t);
        if (exito) {
            System.out.println("Que modificacion desea realizar? \n"
                    + "1- Modificar el tipo de propulsion \n"
                    + "2- Modificar la cantidad de vagones \n"
                    + "3- Modificar la cantidad de de pasajeros \n"
                    + "4- Modificar la linea asignada");
            cod = sc.nextInt();
            modificacionesTrenesAux(cod, t);
            System.out.println("Modificacion realizada con exito!");
        } else {
            System.out.println("El tren no existe en el sistema");
        }
        return exito;
    }

    public static boolean modificacionesTrenesAux(int opcion, Tren t) throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean exito = true;
        Tren tren = (Tren) dicTrenes.recuperar(t);
        switch (opcion) {
            case 1:
                System.out.println("Ingrese el nuevo tipo de propulsion");
                String s = sc.nextLine();
                tren.setTipoPropulsion(s);
                break;
            case 2:
                System.out.println("Ingrese la nueva cantidad de vagones que poseerá el tren");
                int cant = sc.nextInt();
                tren.setCantVagones(cant);
                break;
            case 3:
                System.out.println("Ingrese la nueva cantidad de pasajeros llevará el tren");
                cant = sc.nextInt();
                tren.setCantPasajeros(cant);
                break;
            case 4:
                System.out.println("Ingrese la nueva linea asiganada");
                s = sc.nextLine();
                tren.setLineaAsignada(s);
                break;
        }
        salida.write("Se modificó el tren n°" + tren.getCodigoNumerico() + " \n");
        return true;
    }

    public static boolean altaEstaciones() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean exito;
        System.out.println("Ingrese el nombre de la estación");
        String est = sc.nextLine();
        Estacion nuevaEstacion = new Estacion(est);
        exito = dicEstaciones.insertar(nuevaEstacion, nuevaEstacion.getDatos());
        if (exito) {
            System.out.println("Ingrese el domicilio de la estación");
            System.out.println("Calle:");
            String calle = sc.nextLine();
            System.out.println("numero:");
            int num = sc.nextInt();
            sc.nextLine();
            System.out.println("Ingrese la ciudad");
            String ciudad = sc.nextLine();
            System.out.println("Ingrese el código postal");
            String cod = sc.nextLine();
            System.out.println("Ingrese la cantidad de vías que posee la estación");
            int cantV = sc.nextInt();
            sc.nextLine();
            System.out.println("Ingrese la cantidad de plataforma que posee la estación");
            int cantP = sc.nextInt();

            cargaDatosEstacion(nuevaEstacion, calle, num, ciudad, cod, cantV, cantP);
            graf.insertarVertice(nuevaEstacion.getNombreEstacion());
            System.out.println("Estación cargada con exito!");
            salida.write("Se creó la estación " + est + " \n");
        } else {
            System.out.println("La estación ya se encuentra cargada en el sistema");
        }
        return exito;
    }

    public static void bajasEstaciones() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean exito;
        System.out.println("Ingrese el nombre de la estación a eliminar");
        String est = sc.nextLine();
        Estacion e = new Estacion(est);
        exito = dicEstaciones.eliminar(e);
        if (!exito) {
            System.out.println("No existe la estación");
        } else {
            graf.eliminarVertice(est);
            mapa.forEach((key, list) -> {
                boolean a = ((ArrayList) list).contains(est);
                if (a) {
                    ((ArrayList) list).remove(est);
                }
            });
            salida.write("Se eliminó la estación " + est + " \n");
            System.out.println("Se eliminó la estación con éxito!");

        }
    }

    public static boolean modificacionesEstaciones() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean exito;
        int cod;
        System.out.println("Ingrese el nombre de la estación a modificar");
        String est = sc.nextLine();
        Estacion e = new Estacion(est);
        exito = dicEstaciones.existeClave(e);
        if (exito) {
            System.out.println("Que modificacion desea realizar? \n"
                    + "1- Modificar la calle \n"
                    + "2- Modificar el numero de domicilio \n"
                    + "3- Modificar la ciudad \n"
                    + "4- Modificar el codigo postal \n"
                    + "5- Modificar la cantidad de vias \n"
                    + "6- Modificar la cantidad de plataformas");
            cod = sc.nextInt();
            modificacionesEstacionesAux(cod, e);
            System.out.println("Modificacion realizada con exito!");
            salida.write("Se modificó la estación " + est + " \n");
        } else {
            System.out.println("El tren no existe en el sistema");
        }
        return exito;
    }

    public static boolean modificacionesEstacionesAux(int opcion, Estacion e) {
        Scanner sc = new Scanner(System.in);
        boolean exito = true;
        Estacion estacionMod = (Estacion) dicEstaciones.recuperar(e);
        switch (opcion) {
            case 1:
                System.out.println("Ingrese la nueva calle");
                String s = sc.nextLine();
                estacionMod.setCalle(s);
                break;
            case 2:
                System.out.println("Ingrese el nuevo numero de domicilio");
                int cant = sc.nextInt();
                estacionMod.setNumero(cant);
                break;
            case 3:
                System.out.println("Ingrese la nueva ciudad");
                s = sc.nextLine();
                estacionMod.setCiudad(s);
                break;
            case 4:
                System.out.println("Ingrese el nuevo código postal");
                s = sc.nextLine();
                estacionMod.setCodPostal(s);
                break;
            case 5:
                System.out.println("Ingrese la nueva cantidad de vías");
                cant = sc.nextInt();
                estacionMod.setCantVias(cant);
                break;
            case 6:
                System.out.println("Ingrese la nueva cantidad de plataformas");
                cant = sc.nextInt();
                estacionMod.setCantPlataformas(cant);
                break;
        }
        return true;
    }

    public static boolean altaLineas() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean exito = true;
        char continuar = 's';
        System.out.println("Inserte el nombre de la nueva línea");
        String l = sc.nextLine();
        if (!mapa.containsKey(l)) {
            cargaDatosMapa(l, null);
            salida.write("Se creó la línea " + l + " \n");
            System.out.println("Línea creada");
        } else {
            System.out.println("Ya existe la línea");
        }

        return exito;
    }

    public static boolean bajaLineas() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean exito = true;
        System.out.println("Inserte el nombre de la línea que desea eliminar");
        String l = sc.nextLine();
        if (mapa.containsKey(l)) {
            mapa.remove(l);
            salida.write("Se eliminó la línea " + l + " \n");
            System.out.println("Línea eliminada");
        } else {
            System.out.println("La línea no existe");
        }
        return exito;
    }

    public static boolean modificacionesLineas() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean exito = true;
        int cod;
        System.out.println("Inserte el nombre de la línea a modificar");
        String l = sc.nextLine();
        if (mapa.containsKey(l)) {
            System.out.println("Que modificación desea realizar? \n"
                    + "1- Eliminar una estación de la línea \n"
                    + "2- Agregar una estacion a la línea");
            cod = sc.nextInt();

            if (cod == 1) {
                sc.nextLine();
                System.out.println("Inserte el nombre de la estación a eliminar");
                String est = sc.nextLine();
                Estacion e = new Estacion(est);
                exito = dicEstaciones.existeClave(e);
                if (exito) {
                    if (mapa.get(l).contains(est)) {
                        mapa.get(l).remove(est);
                        salida.write("Se eliminó la estación " + est + " de la línea " + l + " \n");
                    } else {
                        System.out.println("La estación no se encuentra en la línea");
                    }
                } else {
                    System.out.println("No existe la estación");
                }
            }
            if (cod == 2) {
                sc.nextLine();
                System.out.println("Inserte el nombre de la estacion a insertar en la línea");
                String est = sc.nextLine();
                Estacion e = new Estacion(est);
                exito = dicEstaciones.existeClave(e);
                if (exito) {
                    cargaDatosMapa(l, est);
                    salida.write("Se añadió la estación " + est + " a la línea " + l + " \n");
                } else {
                    System.out.println("No existe la estación");
                }
            }
        } else {
            System.out.println("No existe la linea");
        }
        return exito;
    }

    public static boolean altaRieles() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean exito;
        String s1, s2;
        System.out.println("Ingrese la primera estación");
        s1 = sc.nextLine();

        System.out.println("Inserte la segunda estación");
        s2 = sc.nextLine();

        System.out.println("Inserte la distancia entre las dos estaciones");
        double dis = sc.nextDouble();
        exito = graf.insertarArco(s1, s2, dis);
        if (exito) {
            salida.write("Se añadió un tramo de riel entre la estación " + s1 + " y la estación " + s2 + " \n");
            System.out.println("Riel creado con exito!");
        } else {
            System.out.println("No se creó el tramo de riel");
        }
        return exito;
    }

    public static boolean bajaRieles() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean exito;
        System.out.println("Ingrese la primera estación");
        String s1 = sc.nextLine();

        System.out.println("Inserte la segunda estación");
        String s2 = sc.nextLine();

        exito = graf.eliminarArco(s1, s2);
        if (exito) {
            salida.write("Se eliminó el tramo de riel entre la estación " + s1 + " y la estación " + s2 + " \n");
            System.out.println("Riel eliminado con exito!");
        } else {
            System.out.println("No se pudo eliminar el tramo de riel");
        }
        return exito;
    }

    public static void consultaTrenes() throws IOException {
        System.out.println("--------------------------------------------------------");
        Scanner sc = new Scanner(System.in);
        System.out.println("Que consulta desea realizar? \n"
                + "1- Mostrar informacion de un determinado tren \n"
                + "2- Mostrar informacion sobre linea asiganda a un determinado tren");
        int cod = sc.nextInt();
        switch (cod) {
            case 1:
                consultaTrenesAux(cod);
                break;
            case 2:
                consultaTrenesAux2(cod);
                break;
        }
        salida.write("Se realizó consulta sobre trenes \n");
        System.out.println("--------------------------------------------------------");
    }

    public static void consultaTrenesAux(int cod) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserte el codigo del tren");
        int c = sc.nextInt();
        Tren unTren = new Tren(c);
        boolean exito = dicTrenes.existeClave(unTren);
        unTren = (Tren)dicTrenes.recuperar(unTren); 
        if (exito) {
            System.out.println("Informacion del tren");
            System.out.println(unTren.getDatos());
        } else {
            System.out.println("Código de tren inválido");
        }
    }

    public static void consultaTrenesAux2(int cod) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserte el codigo del tren");
        String cad = "";
        int c = sc.nextInt();
        Tren unTren = new Tren(c);
        boolean exito = dicTrenes.existeClave(unTren);
        if (exito) {
            unTren = (Tren) dicTrenes.recuperar(unTren);
            if (mapa.containsKey(unTren.getLineaAsiganda())) {
                cad = cad + "" + mapa.get(unTren.getLineaAsiganda());
                System.out.println("Estaciones en las cuales el tren N°" + unTren.getCodigoNumerico() + " pasa: ");
                System.out.println(cad);
            } else {
                System.out.println("El tren no se encuentra asignado a ninguna línea");
            }
        } else {
            System.out.println("Código de tren inválido");
        }
    }

    public static void consultaEstaciones() throws IOException {
        System.out.println("--------------------------------------------------------");
        Scanner sc = new Scanner(System.in);
        System.out.println("Que consulta desea realizar? \n"
                + "1- Informacion sobre una determinada estación \n"
                + "2- Estaciones que contienen una determinada cadena");
        int c = sc.nextInt();
        switch (c) {
            case 1:
                consultaEstacionesAux();
                break;
            case 2:
                consultaEstacionesAux2();
                break;
        }
        salida.write("Se realizó consulta sobre estaciones" +"\n");
        System.out.println("--------------------------------------------------------");
    }

    public static void consultaEstacionesAux() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserte el nombre de la estación");
        String s = sc.nextLine();
        Estacion e = new Estacion(s);
        boolean exito = dicEstaciones.existeClave(e);
        if (exito) {
            e = (Estacion) dicEstaciones.recuperar(e);
            System.out.println("Informacion de la estación");
            System.out.println(e.getDatos());
        } else {
            System.out.println("No existe la estación");
        }
    }

    public static void consultaEstacionesAux2() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserte la subcadena a comparar");
        String cad = sc.nextLine();
        String ret = "";
        Lista l = new Lista();
        l = dicEstaciones.listarClaves();
        Estacion e;
        int longi = l.longitud();
        int i = 1;
        while (i < longi + 1) {
            e = (Estacion) l.recuperar(i);
            if (e.getNombreEstacion().startsWith(cad)) {
                ret = ret + e.getNombreEstacion() + ",";
            }
            i++;
        }
        System.out.println("Estaciones que contienen la subcadena: " + cad);
        System.out.println(ret);
    }

    public static void consultaViajes() throws IOException {
        System.out.println("--------------------------------------------------------");
        Scanner sc = new Scanner(System.in);
        System.out.println("Que consulta desea realizar? \n"
                + "1- Determinar el camino más corto desde una estación A a otra estación B \n"
                + "2- Obtener el camino que llegue desde una estación A a otra estación B de menor distancia en kilómetro");
        int c = sc.nextInt();
        switch (c) {
            case 1:
                consultaViajesAux();
                break;
            case 2:
                consultaViajesAux2();
                break;
        }
        salida.write("Se realizó consulta sobre viajes \\n");
        System.out.println("--------------------------------------------------------");

    }

    public static void consultaViajesAux() {
        Scanner sc = new Scanner(System.in);
        Lista l = new Lista();
        System.out.println("Inserte la primera estación");
        String s = sc.nextLine();
        System.out.println("Inserte la segunda estación");
        String s2 = sc.nextLine();
        boolean exito = graf.existeCamino(s, s2);
        if (exito) {
            l = graf.caminoMasCorto(s, s2);
            System.out.println("Camino más corto: " + l.toString());
        } else {
            System.out.println("No existe camino que conecte a las estaciones");
        }
    }

    public static void consultaViajesAux2() {
        Scanner sc = new Scanner(System.in);
        Lista l = new Lista();
        System.out.println("Inserte la primera estación");
        String s = sc.nextLine();
        System.out.println("Inserte la segunda estación");
        String s2 = sc.nextLine();
        boolean exito = graf.existeCamino(s, s2);
        if (exito) {
            l = graf.caminoMenorKm(s, s2);
            System.out.println("Camino más corto en km: " + l.toString());
        } else {
            System.out.println("No existe camino que conecte a las estaciones");
        }
    }

    public static void muestraSistema() {
        System.out.println("--------------------------------------------------------");
        System.out.println("ARBOL DE ESTACIONES");
        System.out.println(dicEstaciones.toString());
        System.out.println("");
        System.out.println("ARBOL DE TRENES:");
        System.out.println(dicTrenes.toString());
        System.out.println("");
        System.out.println("LINEAS:");
        String cad = "";
        for (Object key : mapa.keySet()) {
            cad = cad + "Linea: [" + key + "] : estaciones por las que pasa ---> " + mapa.get(key) + "\n";
        }
        System.out.println(cad);
        System.out.println("MAPA DE RIELES");
        System.out.println(graf.toString());
        System.out.println("--------------------------------------------------------");
    }

}
