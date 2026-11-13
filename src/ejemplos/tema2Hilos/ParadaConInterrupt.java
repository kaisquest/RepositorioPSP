package ejemplos.tema2Hilos;

public class ParadaConInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread contador = new Thread(() -> {
            int n = 0;
            // Mientras nadie nos interrumpa, seguimos trabajando
            while (!Thread.currentThread().isInterrupted()) {
                n++;
                System.out.println("Vuelta " + n);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    // Ojo: al saltar esta excepción Java "borra" la marca de interrumpido.
                    // Si no salimos del bucle nosotros, el while seguiría para siempre.
                    System.out.println("Me han interrumpido durante el sleep, termino.");
                    break;
                }
            }
            System.out.println("Hilo terminado limpiamente tras " + n + " vueltas");
        }, "contador");

        contador.start();
        Thread.sleep(1000);       // main deja trabajar al hilo un segundo
        contador.interrupt();     // y le pide que pare (se lo pide, no lo mata)
        contador.join();          // esperamos a que termine de verdad
        System.out.println("main: el contador ya ha acabado, ¿vivo? " + contador.isAlive());
    }

}
