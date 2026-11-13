package ejemplos.tema2Hilos;

public class ParadaConBandera implements Runnable {
    // volatile: obliga a que el hilo vea siempre el valor más reciente que escribe main
    private volatile boolean seguir = true;

    public void parar() {
        seguir = false;
    }

    @Override
    public void run() {
        int n = 0;
        while (seguir) {
            n++;
            // trabajo de cada vuelta
        }
        System.out.println("Bandera a false: salgo del bucle tras " + n + " vueltas");
    }

    public static void main(String[] args) throws InterruptedException {
        ParadaConBandera tarea = new ParadaConBandera();
        Thread hilo = new Thread(tarea);
        hilo.start();

        Thread.sleep(500);
        tarea.parar();       // cambiamos la bandera; el hilo se dará cuenta en su siguiente vuelta
        hilo.join();
    }
}
