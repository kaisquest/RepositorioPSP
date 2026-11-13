package ejemplos.tema2Hilos;

public class EstadosDeUnHilo {

    public static void main(String[] args) throws InterruptedException {
        Thread hilo = new Thread(() -> {
            try {
                Thread.sleep(500);              // el hilo se duerme medio segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Recién creado:      " + hilo.getState());   // NEW

        hilo.start();
        System.out.println("Tras start():       " + hilo.getState());   // RUNNABLE

        Thread.sleep(100);                      // dejamos que el hilo llegue a su sleep
        System.out.println("Durmiendo:          " + hilo.getState());   // TIMED_WAITING

        hilo.join();                            // main espera a que termine
        System.out.println("Tras terminar:      " + hilo.getState());   // TERMINATED
        System.out.println("¿Sigue vivo?        " + hilo.isAlive());    // false
    }
}