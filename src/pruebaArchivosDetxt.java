/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import conjuntistas.dinamicas.*;
import TPO.*; 
import java.io.*;
/**
 *
 * @author Tomas
 */
public class pruebaArchivosDetxt {
    public static void main(String[] args) throws IOException{
        DiccionarioAVL dicEst = new DiccionarioAVL();
        String file = "C:\\Users\\Tomas\\OneDrive\\Escritorio\\tpo\\tpo.txt";
        String[] parts; 
        try(BufferedReader br = new BufferedReader(new FileReader(file))) 
        {
            String line;
            while ((line = br.readLine()) != null) {
            parts = line.split(";"); 
            carga(parts, dicEst); 
            }
        }
        System.out.println(dicEst.toString());
    }
    
    public static void carga(String[] arr, DiccionarioAVL dicEst){ 
        Object a = arr[0];  
        if (a.equals("E")){
            Estacion unaEst = new Estacion(arr[1]);           
            unaEst.setCalle(arr[2]);
            unaEst.setNumero(Integer.parseInt(arr[3]));
            unaEst.setCiudad(arr[4]);
            unaEst.setCodPostal(Integer.parseInt(arr[5]));
            unaEst.setCantVias(Integer.parseInt(arr[6]));
            unaEst.setCantPlataformas(Integer.parseInt(arr[7]));
            dicEst.insertar(unaEst, unaEst.getDatos()); 
            
    }

    }
}
