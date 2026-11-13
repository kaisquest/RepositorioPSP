package ejercicios.ejerciciosAulaVirtual.UD2;


/**
 * Crear un bucle que repita n veces la letra A
 * Crear un hilo antes del bucle que repite la letra B las mismas n veces
 */
public class EjercicioClase1 {

    static void main() {
        int veces = 200;

        Thread hiloB = new Thread(() -> {
            for (int i = 0; i < veces; i++) {
                System.out.println("YES");
            }
        });

        hiloB.start();

        for (int i = 0; i < veces; i++) {
            System.out.println("NO");
        }

    }



}
