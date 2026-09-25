package ejemplos;

import java.io.IOException;

public class LanzadorSuma {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Cada parámetro adicional del constructor se traduce en un
        // argumento más de la línea de comandos del proceso hijo.
        // El "-cp" con nuestro propio classpath es necesario por el mismo
        // motivo que en el ejemplo anterior: para que el hijo encuentre ejemplos.TareaSuma.
        String classpath = System.getProperty("java.class.path");
        ProcessBuilder constructor = new ProcessBuilder("java", "-cp", classpath, "ejemplos.TareaSuma", "12", "30");
        constructor.inheritIO();

        Process proceso = constructor.start();
        proceso.waitFor();
    }
}