package ejemplos.tema2Hilos.ejemploPrimos;

// Lo que comparten las dos versiones (hilos y procesos).
// Así las dos hacen EXACTAMENTE el mismo trabajo, y lo único que cambia es quién lo ejecuta
// y dónde se suma cada primo encontrado.
public class Primos {

    // Los números se reparten en bloques de 1000, por turnos entre los trabajadores:
    // el bloque 0 para el trabajador 0, el bloque 1 para el 1... y vuelta a empezar al llegar a k.
    // (Se hace por bloques y no en un trozo grande por trabajador porque los números grandes
    // cuestan más de comprobar, y el último trabajador tendría más trabajo que los demás.)
    static final int BLOQUE = 1000;

    // Un número es primo si no tiene ningún divisor entre 2 y su raíz cuadrada.
    // Es un cálculo lento a propósito, para que se note el reparto.
    static boolean esPrimo(int n) {
        if (n < 2) return false;
        for (int d = 2; (long) d * d <= n; d++) {
            if (n % d == 0) return false;
        }
        return true;
    }

    // El trabajo de un trabajador: recorre SUS bloques y, por cada primo que encuentra,
    // ejecuta "alEncontrarPrimo". Cada versión decide qué hacer en ese momento:
    //   - los hilos suman en una variable común
    //   - los procesos le mandan un mensaje al padre
    static void buscar(int id, int k, int limite, Runnable alEncontrarPrimo) {
        for (int inicio = 0; inicio <= limite; inicio += BLOQUE) {
            if ((inicio / BLOQUE) % k != id) continue;          // este bloque es de otro trabajador
            int fin = Math.min(inicio + BLOQUE - 1, limite);
            for (int n = inicio; n <= fin; n++) {
                if (esPrimo(n)) {
                    alEncontrarPrimo.run();
                }
            }
        }
    }

    // El resultado correcto, para poder comprobar. Se calcula con la criba de Eratóstenes,
    // que es rapidísima, y no tiene nada que ver con el reparto de los trabajadores.
    static long esperados(int limite) {
        boolean[] compuesto = new boolean[limite + 1];
        long cuenta = 0;
        for (int i = 2; i <= limite; i++) {
            if (!compuesto[i]) {
                cuenta++;
                for (long j = (long) i * i; j <= limite; j += i) compuesto[(int) j] = true;
            }
        }
        return cuenta;
    }
}
