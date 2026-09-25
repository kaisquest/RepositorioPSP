package ejemplos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LanzadorSumaConLectura {

    public static void main(String[] args) throws IOException, InterruptedException {
        String classpath = System.getProperty("java.class.path");
        ProcessBuilder constructor = new ProcessBuilder("java", "-cp", classpath, "ejemplos.TareaSuma", "12", "30");
        // Esta vez NO usamos inheritIO(): queremos leer la salida nosotros,
        // no que se imprima directamente en nuestra consola.
        Process proceso = constructor.start();


        //Estas 2 líneas se van a utilizar para poder convertir la comunicacion entre procesos hijos de bits traducido
        //a String.
        BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
        String resultado = lector.readLine();
        proceso.waitFor();

        int suma = Integer.parseInt(resultado);
        System.out.println("El proceso hijo devolvió: " + suma);
        System.out.println("El doble de ese resultado es: " + (suma * 2));
    }
}