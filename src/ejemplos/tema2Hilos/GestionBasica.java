package ejemplos.tema2Hilos;

public class GestionBasica {
    public static void main(String[] args) throws InterruptedException {
        // 1. Ponerle nombre al hilo (luego sale en depuración y en las trazas)
        Thread lento = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // nos da igual, el hilo acaba
            }
        }, "tarea-lenta");
        lento.start();

        // 2. join con tiempo máximo: esperamos 1 segundo como mucho, no hasta el final
        lento.join(1000);
        if (lento.isAlive()) {
            System.out.println("Pasó 1 segundo y '" + lento.getName() + "' sigue en marcha, no esperamos más");
        }

        // 3. Hilo demonio: no impide que el programa termine
        Thread vigilante = new Thread(() -> {
            while (true) {
                System.out.println("  (vigilante: sigo aquí)");
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }, "vigilante");
        vigilante.setDaemon(true);   // siempre ANTES de start()
        vigilante.start();

        lento.join();                // main espera a que acabe la tarea lenta
        System.out.println("main termina. El vigilante es demonio, así que el programa acaba con él.");
    }
}
