package ejemplos;

import java.io.IOException;

public class LanzadorSaludo {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Le pasamos al hijo el MISMO classpath con el que se está ejecutando
        // este propio programa. Sin esto, "java ejemplos.TareaSaludo" a secas busca la
        // clase en el directorio de trabajo actual y normalmente no la
        // encuentra (falla con ClassNotFoundException), sobre todo lanzando
        // desde un IDE como IntelliJ o Eclipse.
        String classpath = System.getProperty("java.class.path");
        ProcessBuilder constructor = new ProcessBuilder("java", "-cp", classpath, "ejemplos.TareaSaludo");

        // inheritIO() hace que el proceso hijo comparta la consola del padre:
        // lo que el hijo escriba con System.out o System.err aparece
        // directamente en nuestra propia consola, sin que tengamos que leerlo.
        constructor.inheritIO();

        Process proceso = constructor.start();
        int codigoSalida = proceso.waitFor();
        System.out.println("El proceso hijo terminó con código " + codigoSalida);
    }
}