package TPO;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class archivos {
    private static FileWriter salida;
    
    static {
        try {
            salida = new FileWriter("‪loooooog.txt");
        } catch (IOException e){
            throw new RuntimeException();
        }
    }
     public static void main (String[] args) throws Exception{
        int i = 1; 
        if(i == 1){
            salida.write("eeeeeee");
            salida.close();
        }
        
     }
}
