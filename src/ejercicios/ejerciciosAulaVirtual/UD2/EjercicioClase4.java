package ejercicios.ejerciciosAulaVirtual.UD2;

public class EjercicioClase4 {
    void main() throws InterruptedException {
        Hilo hilo = new Hilo();


        System.out.println("Estado antes de iniciarse: " + hilo.isAlive());
        hilo.start();
        System.out.println("Estado en ejecución: " + hilo.isAlive());
        hilo.join();
        System.out.println("Estado después de ejecutarse: " + hilo.isAlive());
    }


    class Hilo extends Thread {


        @Override
        public void run() {
            try {
                sleep(2000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
