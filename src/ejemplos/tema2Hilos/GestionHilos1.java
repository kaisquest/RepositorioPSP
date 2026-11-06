package ejemplos.tema2Hilos;

public class GestionHilos1 {

    public static void main(String[] args) {
        // Aunque no hayamos creado ningún hilo, ya hay uno: el que ejecuta main()
        Thread actual = Thread.currentThread();

        System.out.println("Nombre:     " + actual.getName());
        System.out.println("Prioridad:  " + actual.getPriority());
        System.out.println("Grupo:      " + actual.getThreadGroup().getName());
        System.out.println("¿Demonio?:  " + actual.isDaemon());
    }
}

