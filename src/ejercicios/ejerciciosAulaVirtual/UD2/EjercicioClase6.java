package ejercicios.ejerciciosAulaVirtual.UD2;

import java.util.stream.Stream;

public class EjercicioClase6 {

    public static int contador = 0;

    static void main() throws InterruptedException {
        String texto = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum gravida nisl et eros gravida luctus." +
                "Vivamus imperdiet volutpat risus ac porttitor. Vivamus sollicitudin" +
                " dolor eu nibh egestas tristique. Cras at sapien tempus, sollicitudin justo eu, fermentum est. ";

        Thread contadorA = new Thread(new hiloContador('a', texto));
        Thread contadorE = new Thread(new hiloContador('e', texto));
        Thread contadorI = new Thread(new hiloContador('i', texto));
        Thread contadorO = new Thread(new hiloContador('o', texto));
        Thread contadorU = new Thread(new hiloContador('u', texto));

        contadorA.start();
        contadorE.start();
        contadorI.start();
        contadorO.start();
        contadorU.start();

        contadorA.join();
        contadorE.join();
        contadorI.join();
        contadorO.join();
        contadorU.join();


        System.out.println(contador);

    }


    static class hiloContador implements Runnable {

        private Character letra;
        private String texto;

        public hiloContador(Character letra, String texto) {
            this.letra = letra;
            this.texto = texto;

        }

        @Override
        public void run() {
            contarVocal();
        }

        public synchronized void contarVocal() {
            for (int i = 0; i < texto.length(); i++) {
                if (letra.equals(texto.charAt(i))) {
                    contador++;

                }

            }


        }
    }


}



