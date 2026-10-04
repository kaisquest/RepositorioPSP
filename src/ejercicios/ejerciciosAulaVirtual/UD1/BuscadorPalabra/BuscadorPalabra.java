package ejercicios.ejerciciosAulaVirtual.UD1.BuscadorPalabra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Comunicación entre procesos (con padre común --> Streams!)
 * Escribe una clase BuscaPalabra (lo usaremos como subproceso) que reciba 2 argumentos:  1) el nombre de un fichero, 2) una palabra.
 * Escribe también un lanzador (será nuestro proceso padre) que pregunte dos cosas por teclado:  1) el nombre del fichero,
 * y 2) una lista de palabras separadas por comas.
 * El lanzador creará tantos subprocesos BuscaPalabra como palabras a buscar.,
 * y le pasará a cada uno el fichero en donde buscar, y SU palabra a buscar en él.
 * Versión LITE - Cada subproceso simplemente devuelve al acabar el número de apariciones
 * de la palabra que le han encargado buscar; incluso sin necesidad de usar Streams.
 * (puedes usar InheritIO + System.out.println, o el código de salida del proceso:  System.exit(totalApariciones); ).
 * Versión PRO - Cada proceso envía la LÍNEA (no frase) donde estaba la palabra (aquí necesitaréis Streams).
 * El padre muestra esas líneas a medida que las recibe, y al terminar el subproceso, muestra también el
 * total de apariciones — que el hijo devuelve como código de salida, no por el stream.
 *
 */
public class BuscadorPalabra {

    static void main() throws IOException, InterruptedException {

        List<Process> listProcesos = new ArrayList<>();

        System.out.println("Qué palabra estás buscando");
        Scanner sc = new Scanner(System.in);
        String palabra = sc.nextLine();

        String rutaFichero = "src/ejercicios/ejerciciosAulaVirtual/UD1/BuscadorPalabra/Archivos/DonQuijote.txt";


        String classpath = System.getProperty("java.class.path");
        String rutaClaseHija = "ejercicios.ejerciciosAulaVirtual.UD1.BuscadorPalabra.BuscaPalabra";
        ProcessBuilder constructor = new ProcessBuilder("java", "-cp", classpath, rutaClaseHija, rutaFichero, palabra);




        constructor.inheritIO();
        Process proceso = constructor.start();



      // BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
      // String resultado = lector.readLine();


        proceso.waitFor();






    }
}
