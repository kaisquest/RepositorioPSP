package ejercicios.ejerciciosAulaVirtual.UD1.PiedraPapelTijera;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;

public class Arbitro {


    static final String MESA = "src/ejercicios/ejerciciosAulaVirtual/UD1/PiedraPapelTijera/mesa.txt";
    public static final int JUGADORES = 2;
    static final int PUNTOS_PARA_GANAR = 3;

    static void main() throws IOException, InterruptedException {
        int idJugador = 0;
        List<Process> listaProcesos = new ArrayList<>();
        List<BufferedReader> deHijo = new ArrayList<>();
        List<PrintWriter> aHijo = new ArrayList<>();

        String classPath = System.getProperty("java.class.path");
        String rutaJugador = "ejercicios.ejerciciosAulaVirtual.UD1.PiedraPapelTijera.Jugador";
        limpiarMesa();

        for (int i = 0; i < JUGADORES; i++) {
            idJugador = i + 1;

            ProcessBuilder constructor = new ProcessBuilder("java", "-cp", classPath, rutaJugador,"Jugador", String.valueOf(idJugador));
            constructor.inheritIO();

            Process jugador = constructor.start();
            constructor.redirectError(ProcessBuilder.Redirect.INHERIT);

            listaProcesos.add(jugador);

            PrintWriter escritor = new PrintWriter(jugador.getOutputStream(), true);
            aHijo.add(escritor);

            BufferedReader lector = new BufferedReader(new InputStreamReader(jugador.getInputStream()));
            deHijo.add(lector);


        }


        List<Integer> ronda = new ArrayList<>();
        List<Integer> puntos = new ArrayList<>();
        boolean fin = false;






        //  while (!listaProcesos.isEmpty()){

        //      for (int i = 0; i < listaProcesos.size(); i++) {
        //          Process process = listaProcesos.get(i);
        //
        //      }
        //  }


    }


    private static void limpiarMesa() throws IOException {

        try (RandomAccessFile raf = new RandomAccessFile(MESA, "rw");
             FileChannel ch = raf.getChannel();
             FileLock lock = ch.lock()) {
            raf.setLength(0);
            raf.writeBytes("J1=\nJ2=\n");


        }


    }
}
