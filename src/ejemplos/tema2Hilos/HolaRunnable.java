package ejemplos.tema2Hilos;

public class HolaRunnable implements Runnable {

    private final String nombre;

    public HolaRunnable(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("Hola " + nombre + " (vuelta " + i + ") desde " + Thread.currentThread().getName());
            pausa();   // para que se note que los dos hilos avanzan a la vez
        }
    }

    private static void pausa() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        // El hilo se crea aparte, y se le pasa la tarea que tiene que hacer
        Thread h1 = new Thread(new HolaRunnable("Ana"), "hilo-ana");
        Thread h2 = new Thread(new HolaRunnable("Luis"), "hilo-luis");

        h1.start();
        h2.start();
    }
}