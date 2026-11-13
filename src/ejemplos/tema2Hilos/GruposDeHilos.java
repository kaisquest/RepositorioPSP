package ejemplos.tema2Hilos;

public class GruposDeHilos {

    public static void main(String[] args) throws InterruptedException {
        // Creamos un grupo con nombre. Su "padre" será el grupo del hilo que lo crea (main)
        ThreadGroup descargas = new ThreadGroup("descargas");

        // El grupo se indica al CREAR el hilo; después ya no se puede cambiar
        Runnable espera = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrumpido");
            }
        };
        for (int i = 1; i <= 3; i++) {
            new Thread(descargas, espera, "descarga-" + i).start();
        }

        Thread.sleep(200);   // un momento para que los hilos arranquen

        System.out.println("Grupo:            " + descargas.getName());
        System.out.println("Grupo padre:      " + descargas.getParent().getName());
        System.out.println("Hilos activos:    " + descargas.activeCount());
        System.out.println("Prioridad máx.:   " + descargas.getMaxPriority());

        // enumerate() copia los hilos activos a un array que preparamos nosotros
        Thread[] lista = new Thread[descargas.activeCount()];
        int cuantos = descargas.enumerate(lista);
        for (int i = 0; i < cuantos; i++) {
            System.out.println("  - " + lista[i].getName());
        }

        descargas.list();            // imprime el contenido del grupo por consola

        descargas.interrupt();       // interrumpe a TODOS los hilos del grupo de una vez
        Thread.sleep(200);
        System.out.println("Hilos activos tras interrupt(): " + descargas.activeCount());
    }
}
