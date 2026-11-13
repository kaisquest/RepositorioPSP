package ejercicios.ejerciciosAulaVirtual.UD2.practica1_5_tortuga;

import java.util.Random;

public class Ejercicio1_LaTortuga {

    class Tortuga implements Runnable {
        final static int espera = 200;

        public int pasos;

        @Override
        public void run() {
            for (int i = 0; i <= pasos; i++) {
                System.out.printf("Tortuga: pasos" + i);

                pausa(espera);

            }
            System.out.println("La TORTUGA llega a la meta");

        }

        public Tortuga(int pasos) {
            this.pasos = pasos;

        }

        private static void pausa(int tiempo) {
            try {
                Thread.sleep(tiempo);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }


    }


    class Liebre extends Thread {

        final static int espera = 50;
        public int pasos;
        public int siesta = new Random().nextInt(500,2000);

        @Override
        public void run() {
            for (int i = 0; i <= pasos; i++) {
                System.out.printf("Liebre: pasos" + i);
                if (i == 5) {
                    pausa(siesta);
                    System.out.println("Liebre: se echa una siesta de " + siesta);
                }
                pausa(espera);

            }
            System.out.println("La LIEBRE llega a la meta");

        }

        public Liebre(int pasos) {
            this.pasos = pasos;
        }


        private static void pausa(int tiempo) {
            try {
                Thread.sleep(tiempo);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }


    }

    void main() {
        final int pasos = 10;


        Thread tortuga = new Thread(() -> new Tortuga(pasos));
        Thread liebre = new Liebre(pasos);

        tortuga.setName("tortuga");
        liebre.setName("liebre");

        System.out.println("Antes de start(): " + tortuga.getName() + "= " + tortuga.getState() + " " + liebre.getName() + "= " + liebre.getState());
        System.out.println("¡Preparados, listos, ya!");

        tortuga.start();
        liebre.start();


    }


}
