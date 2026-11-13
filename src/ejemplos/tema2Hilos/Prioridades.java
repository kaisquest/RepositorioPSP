package ejemplos.tema2Hilos;

public class Prioridades implements Runnable{

    private long vueltas = 0;
    private volatile boolean fin = false;

    @Override
    public void run() {
        while (!fin) {
            vueltas++;                      // cuantas más vueltas, más CPU ha recibido
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Prioridades[] tareas = new Prioridades[3];
        Thread[] hilos = new Thread[3];
        int[] prioridades = {Thread.MIN_PRIORITY, Thread.NORM_PRIORITY, Thread.MAX_PRIORITY};

        for (int i = 0; i < 3; i++) {
            tareas[i] = new Prioridades();
            hilos[i] = new Thread(tareas[i], "prioridad-" + prioridades[i]);
            hilos[i].setPriority(prioridades[i]);
            hilos[i].start();
        }

        Thread.sleep(2000);                 // los dejamos competir dos segundos
        for (int i = 0; i < 3; i++) {
            tareas[i].fin = true;
        }
        for (Thread h : hilos) {
            h.join();
        }

        for (int i = 0; i < 3; i++) {
            System.out.println(hilos[i].getName() + " (" + hilos[i].getPriority() + "): " + tareas[i].vueltas + " vueltas");
        }
    }
}
