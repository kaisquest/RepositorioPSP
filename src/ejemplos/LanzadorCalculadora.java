package ejemplos;

import java.io.IOException;

public class LanzadorCalculadora  {

    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder constructor = new ProcessBuilder("calc.exe");
        // Alternativa si el ejecutable no está en el PATH: indicar la ruta completa.
        // Ojo con las barras: en una cadena Java hay que escapar cada "\" como "\\".
        // ProcessBuilder constructor = new ProcessBuilder("C:\\Windows\\System32\\calc.exe");
        Process proceso = constructor.start();

        System.out.println("Calculadora abierta, PID: " + proceso.pid());
        int codigoSalida = proceso.waitFor();
        System.out.println("La calculadora se cerró con código " + codigoSalida);


    }




}






