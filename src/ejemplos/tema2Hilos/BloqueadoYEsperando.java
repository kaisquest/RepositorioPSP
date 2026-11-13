package ejemplos.tema2Hilos;

public class BloqueadoYEsperando {
    public static void main(String[] args) throws InterruptedException {
        Object cerrojo = new Object();

        // Este hilo coge el "cerrojo" y se lo queda un segundo
        Thread dueno = new Thread(() -> {
            synchronized (cerrojo) {            // el synchronized lo veremos en el siguiente apunte
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "dueño");

        // Este quiere entrar al mismo cerrojo y tiene que esperar a que el otro salga
        Thread bloqueado = new Thread(() -> {
            synchronized (cerrojo) {
                System.out.println("bloqueado: ya he entrado");
            }
        }, "bloqueado");

        // Este espera a que termine "dueño"
        Thread esperando = new Thread(() -> {
            try {
                dueno.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "esperando");

        dueno.start();
        Thread.sleep(100);                      // aseguramos que "dueño" ya tiene el cerrojo
        bloqueado.start();
        esperando.start();
        Thread.sleep(100);

        System.out.println("dueño:     " + dueno.getState());      // TIMED_WAITING (está en el sleep)
        System.out.println("bloqueado: " + bloqueado.getState());  // BLOCKED
        System.out.println("esperando: " + esperando.getState());  // WAITING (join sin tiempo)
    }
}
