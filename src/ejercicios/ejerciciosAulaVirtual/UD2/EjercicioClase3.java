package ejercicios.ejerciciosAulaVirtual.UD2;

public class EjercicioClase3 {
    void main() {
        int veces = 20000;

        Thread primero = new Thread(new HiloA(veces, "A"));
        Thread segundo = new Thread(new HiloB(veces, "B"));

        primero.setName("Hilo letra A");
        segundo.setName("Hilo letra B");

        primero.setPriority(Thread.MAX_PRIORITY);
        segundo.setPriority(Thread.MIN_PRIORITY);

        primero.start();
        segundo.start();


    }


    class HiloA implements Runnable {
        int veces;
        String texto;

        @Override
        public void run() {
            bucle(veces);

        }


        public void bucle(int veces) {
            for (int i = 0; i < veces; i++) {
                System.out.println(texto);

            }

        }

        public HiloA(int veces, String texto) {
            this.veces = veces;
            this.texto = texto;
        }
    }

    class HiloB implements Runnable {
        String texto;
        int veces;

        @Override
        public void run() {
            bucle(veces);

        }


        public void bucle(int veces) {
            for (int i = 0; i < veces; i++) {
                System.out.println(texto);

            }

        }

        public HiloB(int veces, String texto) {
            this.veces = veces;
            this.texto = texto;
        }
    }

}
