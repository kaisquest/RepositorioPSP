package ejemplos.tema2Hilos.ejemploPrimos;

// La salida, igual para las dos versiones, para poder compararlas de un vistazo.
public class Informe {

    static void imprimir(String version, int limite, int k, long contados, long esperados, long ms) {
        System.out.println("==================== " + version + " ====================");
        System.out.println("Limite ............ " + limite);
        System.out.println("Trabajadores ...... " + k);
        System.out.println("Primos esperados .. " + esperados);
        System.out.println("Primos contados ... " + contados);
        if (contados == esperados) {
            System.out.println("Resultado ......... CORRECTO");
        } else {
            System.out.println("Resultado ......... INCORRECTO (diferencia: " + (contados - esperados) + ")");
        }
        System.out.println("Tiempo ............ " + ms + " ms");
    }
}
