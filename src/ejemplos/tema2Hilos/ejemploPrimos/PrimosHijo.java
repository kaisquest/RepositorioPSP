package ejemplos.tema2Hilos.ejemploPrimos;

// VERSIÓN CON PROCESOS: el hijo
// Uso: java PrimosHijo <limite> <su numero> <total de procesos>
//
// Busca primos en SUS bloques de números. Cada vez que encuentra uno, le manda
// una línea al padre por la salida estándar. Al terminar, manda la línea FIN.
public class PrimosHijo {

    public static void main(String[] args) {
        int limite = Integer.parseInt(args[0]);
        int id = Integer.parseInt(args[1]);
        int k = Integer.parseInt(args[2]);

        // Un primo encontrado = un mensaje al padre. El hijo NO guarda ningún total:
        // el total vive solo en el padre.
        Primos.buscar(id, k, limite, () -> System.out.println("1"));

        System.out.println("FIN");
    }
}
