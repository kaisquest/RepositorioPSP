package ejercicios.ejerciciosAulaVirtual.UD1.BuscadorPalabra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BuscaPalabra {

    static void main(String[] args) {

        String ruta = args[0];
        String palabra = args[1];



        ArrayList<String> lineas = new ArrayList<>();
        int contadorPalabra = 0;

        try (BufferedReader lector = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                lineas.add(linea);
                if (linea.contains(palabra)) {
                    contadorPalabra++;
                }
            }
        } catch (IOException e) {
            System.err.println("No se pudo leer el fichero: " + e.getMessage());
        }

        System.out.println("La palabra se ha encontrado " + contadorPalabra + " veces.");
    }


}
