package ejemplos.tema2Hilos;

public class RunContraStart {

    public static void main(String[] args) {
        Thread hilo = new Thread(() ->
                System.out.println("Ejecutándome en: " + Thread.currentThread().getName()), "mi-hilo");

        hilo.run();     // MAL: es una llamada normal, se ejecuta en main y no se crea ningún hilo
        hilo.start();   // BIEN: ahora sí corre en "mi-hilo"

        try {
            hilo.join();      // esperamos a que acabe antes de intentar arrancarlo otra vez
            hilo.start();     // un hilo solo se puede arrancar una vez
        } catch (IllegalThreadStateException e) {
            System.out.println("No se puede: " + e.getClass().getSimpleName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
