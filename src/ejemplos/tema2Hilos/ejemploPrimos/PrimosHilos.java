package ejemplos.tema2Hilos.ejemploPrimos;

// VERSIÓN CON HILOS
// Uso: java PrimosHilos <limite> <numero de hilos>
//
// Cada hilo busca primos en SUS bloques de números y, cada vez que encuentra uno,
// suma 1 a UNA VARIABLE COMÚN (total) que ven todos los hilos.
public class PrimosHilos {

    // La variable común. Todos los hilos escriben en ella.
    static long total = 0;

    // Sumar 1 a total, pero con el candado de la clase: solo un hilo a la vez puede estar dentro.
    static synchronized void sumarSincronizado() {
        total++;
    }

    public static void main(String[] args) throws Exception {
        int limite = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);

        long esperados = Primos.esperados(limite);       // antes de empezar a medir
        long inicio = System.nanoTime();

        Thread[] hilos = new Thread[k];
        for (int i = 0; i < k; i++) {
            final int id = i;
            hilos[i] = new Thread(() ->
                Primos.buscar(id, k, limite, () -> {

                    // ============================================================
                    //  >>>  ZONA PARA JUGAR  <<<
                    //  Dejad SOLO UNA de las dos líneas sin comentar (la otra, con //).
                    //
                    //  (A) Sin proteger: todos los hilos hacen "leer, sumar, escribir"
                    //      sobre la misma variable y se pisan. El resultado sale mal.
                    //  (B) Protegido: se suma de uno en uno. El resultado sale bien.
                    // ============================================================
                    total++;                             // (A) SIN proteger
                    // sumarSincronizado();              // (B) PROTEGIDO

                    // ============================================================
                })
            );
            hilos[i].start();
        }

        for (Thread h : hilos) h.join();                 // esperamos a que acaben todos

        long ms = (System.nanoTime() - inicio) / 1_000_000;
        Informe.imprimir("HILOS", limite, k, total, esperados, ms);
    }
}
