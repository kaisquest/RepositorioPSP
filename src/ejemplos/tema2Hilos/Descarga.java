package ejemplos.tema2Hilos;
import java.util.Random;

// La tarea: cada hilo "descarga" un fichero que tarda un tiempo al azar
public class Descarga implements Runnable {

    private final String fichero;
    private final int milisegundos;
    private boolean terminada = false;      // resultado que leerá main cuando el hilo acabe

    Descarga(String fichero, int milisegundos) {
        this.fichero = fichero;
        this.milisegundos = milisegundos;
    }

    @Override
    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "] empieza " + fichero);
        try {
            Thread.sleep(milisegundos);     // aquí iría el trabajo de verdad
        } catch (InterruptedException e) {
            System.out.println("[" + Thread.currentThread().getName() + "] cancelada " + fichero);
            return;
        }
        terminada = true;
        System.out.println("[" + Thread.currentThread().getName() + "] termina " + fichero + " (" + milisegundos + " ms)");
    }

    boolean estaTerminada() {
        return terminada;
    }

    String getFichero() {
        return fichero;
    }
}

 class AplicacionMultihilo {

    public static void main(String[] args) throws InterruptedException {
        final int NUM_HILOS = 5;
        Random azar = new Random();
        long inicio = System.currentTimeMillis();

        // 1. Preparamos las tareas y los hilos (todavía no corre nada)
        Descarga[] tareas = new Descarga[NUM_HILOS];
        Thread[] hilos = new Thread[NUM_HILOS];
        for (int i = 0; i < NUM_HILOS; i++) {
            tareas[i] = new Descarga("fichero" + (i + 1) + ".zip", 500 + azar.nextInt(1500));
            hilos[i] = new Thread(tareas[i], "hilo-" + (i + 1));
        }

        // 2. Los lanzamos todos
        for (Thread hilo : hilos) {
            hilo.start();
        }

        // 3. main espera a que terminen TODOS antes de seguir
        for (Thread hilo : hilos) {
            hilo.join();
        }

        // 4. Ya podemos recoger los resultados con seguridad
        int correctas = 0;
        for (Descarga tarea : tareas) {

            if (tarea.estaTerminada()) {
                correctas++;
            }
        }
        long total = System.currentTimeMillis() - inicio;
        System.out.println("Descargas correctas: " + correctas + " de " + NUM_HILOS);
        System.out.println("Tiempo total: " + total + " ms (si fueran una detrás de otra sería la suma de todas)");
    }
}