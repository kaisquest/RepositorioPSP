package ejemplos.tema2Hilos;

public class Egoista extends Thread {

    private final String color;
    private final boolean cedoElTurno;

    public Egoista(String color, boolean cedoElTurno) {
        this.color = color;
        this.cedoElTurno = cedoElTurno;
    }

    @Override
    public void run() {
        for (int i = 0; i < 6; i++) {
            System.out.println(color + " " + i);
            if (cedoElTurno) {
                Thread.yield();     // "yo ya he hecho lo mío, que pase otro hilo si quiere"
            }
        }
    }

    public static void main(String[] args) {
        boolean ceder = args.length > 0 && args[0].equals("yield");
        new Egoista("ROJO", ceder).start();
        new Egoista("AZUL", ceder).start();
    }
}
