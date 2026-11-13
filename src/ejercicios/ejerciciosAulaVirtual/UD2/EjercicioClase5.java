package ejercicios.ejerciciosAulaVirtual.UD2;

public class EjercicioClase5 {

    void main() throws InterruptedException {

        HiloLento hiloLento = new HiloLento(null);
        HiloRapido hiloRapido = new HiloRapido(hiloLento);

        System.out.println("Estado antes de iniciarse: " + hiloRapido.isAlive());
        System.out.println("Estado antes de iniciarse: " + hiloLento.isAlive());


        hiloLento.start();
        hiloRapido.start();


        System.out.println("Estado en ejecución: " + hiloRapido.isAlive());
        System.out.println("Estado en ejecución: " + hiloLento.isAlive());

        hiloRapido.join();


        System.out.println("Estado después de ejecutarse: " + hiloRapido.isAlive());
        System.out.println("Estado después de ejecutarse: " + hiloLento.isAlive());

    }


    class HiloRapido extends Thread {

        Thread hilo;

        @Override
        public void run() {
            try {
                if (hilo != null) hilo.join();
                sleep(2000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public HiloRapido(Thread hilo) {
            this.hilo = hilo;
        }
    }

    class HiloLento extends Thread {

        Thread hilo;

        @Override
        public void run() {
            try {

                if (hilo != null) hilo.join();
                sleep(10000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

       public HiloLento(Thread hilo) {
           this.hilo = hilo;
       }
    }


}
