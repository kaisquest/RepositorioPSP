package ejemplos.tema2Hilos;

public class FormaDeCrear {
    public static void main(String[] args) {
        // 1. Clase anónima: la tarea se escribe en el sitio, sin darle nombre
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hilo con clase anónima");
            }
        });

        // 2. Expresión lambda: lo mismo en una línea (Runnable solo tiene un método, run)
        Thread b = new Thread(() -> System.out.println("Hilo con lambda"));

        a.start();
        b.start();
    }
}
