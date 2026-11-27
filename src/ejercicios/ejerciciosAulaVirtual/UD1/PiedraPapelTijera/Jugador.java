package ejercicios.ejerciciosAulaVirtual.UD1.PiedraPapelTijera;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Jugador {

    static final String MESA = "mesa.txt";
    static final String[] OPCIONES = {"PIEDRA", "PAPEL", "TIJERA"};


    static void main(String[] args) throws IOException {
        int id = Integer.parseInt(args[0]);
        Random eleccionAleatoria = new Random();

        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

        String msg = entrada.readLine();

        while (msg != null || !msg.startsWith("GANADOR")){


            msg = entrada.readLine();
        }




    }


}
