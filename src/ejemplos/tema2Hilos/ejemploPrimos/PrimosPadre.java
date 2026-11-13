package ejemplos.tema2Hilos.ejemploPrimos;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// VERSIÓN CON PROCESOS: el padre (hace de árbitro)
// Uso: java PrimosPadre <limite> <numero de procesos>
//
// Lanza un hijo por trabajador. La variable común (total) vive SOLO en el padre.
// Los hijos no pueden tocarla: le mandan un mensaje por cada primo y el padre suma.
public class PrimosPadre {

    public static void main(String[] args) throws Exception {
        int limite = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);

        long esperados = Primos.esperados(limite);       // antes de empezar a medir
        long inicio = System.nanoTime();

        // La orden se prepara una vez; en cada vuelta solo cambia el número del hijo.
        ProcessBuilder pb = new ProcessBuilder();
        pb.redirectError(ProcessBuilder.Redirect.INHERIT);

        Process[] procesos = new Process[k];
        BufferedReader[] desdeHijo = new BufferedReader[k];

        for (int i = 0; i < k; i++) {
            pb.command("java", "-cp", System.getProperty("java.class.path"),
                       "PrimosHijo", String.valueOf(limite), String.valueOf(i), String.valueOf(k));
            procesos[i] = pb.start();
            desdeHijo[i] = new BufferedReader(new InputStreamReader(procesos[i].getInputStream()));
        }

        // La variable común. Solo la toca el padre, y lo hace de uno en uno:
        // lee un mensaje, suma, lee el siguiente... Por eso aquí no hay carrera posible.
        long total = 0;
        for (int i = 0; i < k; i++) {
            String linea;
            while (!(linea = desdeHijo[i].readLine()).equals("FIN")) {
                total++;                                  // un mensaje = un primo
            }
            procesos[i].waitFor();
        }

        long ms = (System.nanoTime() - inicio) / 1_000_000;
        Informe.imprimir("PROCESOS", limite, k, total, esperados, ms);
    }
}
