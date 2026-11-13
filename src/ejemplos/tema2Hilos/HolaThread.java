package ejemplos.tema2Hilos;

// Primera forma: heredar de Thread y sobrescribir run()
public class HolaThread extends Thread {

    private final String saludo;

    public HolaThread(String saludo) {
        this.saludo = saludo;
    }

    // Todo lo que pongamos aquí se ejecuta en el hilo nuevo
    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(getName() + " dice: " + saludo + " (vuelta " + i + ")");
            pausa();   // sin esta pausa un hilo acaba antes de que arranque el otro y no se nota la concurrencia
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
        HolaThread uno = new HolaThread("hola");
        HolaThread dos = new HolaThread("ola");

        uno.start();   // start() crea el hilo y llama a run() por nosotros
        dos.start();

        System.out.println("main ha terminado de lanzar los hilos");
    }
}
