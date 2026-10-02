package ejercicios.ejerciciosAulaVirtual.UD1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio1 {


    /**
     * Ejercicio 1 — N instancias del Bloc de notas, avisando de cada cierre
     * Escribe un programa que pida un número entero por teclado, lance ese número de instancias del
     * Bloc de notas, y a medida que el usuario las vaya cerrando (en el orden real en que las cierre, no necesariamente el de lanzamiento),
     * muestre por consola cuál se ha cerrado — identificándola por su PID o por el número de orden en que se lanzó.
     * Como ejecutable puedes usar cualquiera que esté ya en el path de Windows: calc, notepad, cmd, powershell, etc...
     * Ojo aquí con el waitFor()! (;-P
     */


    static void main() throws IOException, InterruptedException {
        List<Process> listProcesos = new ArrayList<>();


        System.out.println("Introduce el número de veces a abrir un proceso");
        Scanner sc = new Scanner(System.in);
        int nVeces = sc.nextInt();


        ProcessBuilder constructor = new ProcessBuilder("mspaint.exe");


        for (int i = 0; i < nVeces; i++) {
            Process proceso = constructor.start();
            listProcesos.add(proceso);
            System.out.println("Instancia " + (i + 1) + " abierta, PID: " + proceso.pid());
        }

        while (!listProcesos.isEmpty()) {
            for (int i = 0; i < listProcesos.size(); i++) {

                Process proceso = listProcesos.get(i);

                if (!proceso.isAlive()) {
                    System.out.println("Proceso " + proceso.pid() + " cerrado");
                    listProcesos.remove(proceso);
                }


            }


        }


    }


}
