package app;

import java.util.Scanner;
import negocio.ArbolBinario;
import modelo.Nodo;

/**
 * Clase Main.
 * Programa principal con menu interactivo (Scanner) para construir,
 * visualizar y analizar el arbol binario de dependencias universitarias.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // El arbol inicia vacio hasta que el usuario elija la opcion 1
        ArbolBinario<String> arbol = new ArbolBinario<>();
        int opcion = -1;

        while (opcion != 0) {
            mostrarMenu();
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1:
                    // Se crea un arbol nuevo con la estructura inicial
                    arbol = construirArbolInicial();
                    System.out.println("Arbol inicial creado correctamente.");
                    break;
                case 2:
                    System.out.println("--- Estructura del arbol ---");
                    arbol.mostrarArbol();
                    break;
                case 3:
                    System.out.println("Numero de nodos: " + arbol.contarNodos());
                    break;
                case 4:
                    System.out.println("Numero de hojas: " + arbol.contarHojas());
                    break;
                case 5:
                    System.out.println("Altura del arbol (aristas): " + arbol.altura());
                    break;
                case 6:
                    consultarGrado(sc, arbol);
                    break;
                case 7:
                    consultarHoja(sc, arbol);
                    break;
                case 8:
                    ejecutarCasosDePrueba();
                    break;
                case 0:
                    System.out.println("Fin del programa.");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente de nuevo.");
            }
            System.out.println();
        }
        sc.close();
    }

    // Imprime las opciones del menu
    private static void mostrarMenu() {
        System.out.println("========== MENU ARBOL BINARIO ==========");
        System.out.println("1. Crear el arbol inicial");
        System.out.println("2. Visualizar el arbol");
        System.out.println("3. Consultar el numero de nodos");
        System.out.println("4. Consultar el numero de hojas");
        System.out.println("5. Consultar la altura");
        System.out.println("6. Consultar el grado de un nodo");
        System.out.println("7. Verificar si un nodo es hoja");
        System.out.println("8. Ejecutar casos de prueba");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    // Lee un entero del teclado; si el texto no es un numero devuelve -1
    private static int leerEntero(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Pide un nombre de dependencia al usuario y lo normaliza a mayusculas
    private static String pedirNombre(Scanner sc) {
        System.out.print("Ingrese el nombre del nodo (ej. FISEI): ");
        return sc.nextLine().trim().toUpperCase();
    }

    // Opcion 6: muestra el grado (numero de hijos) de un nodo
    private static void consultarGrado(Scanner sc, ArbolBinario<String> arbol) {
        String nombre = pedirNombre(sc);
        Nodo<String> nodo = arbol.buscar(nombre);
        if (nodo == null) {
            System.out.println("El nodo " + nombre + " no existe en el arbol.");
        } else {
            System.out.println("Grado de " + nombre + ": " + nodo.grado());
        }
    }

    // Opcion 7: indica si un nodo es hoja
    private static void consultarHoja(Scanner sc, ArbolBinario<String> arbol) {
        String nombre = pedirNombre(sc);
        Nodo<String> nodo = arbol.buscar(nombre);
        if (nodo == null) {
            System.out.println("El nodo " + nombre + " no existe en el arbol.");
        } else if (nodo.esHoja()) {
            System.out.println(nombre + " ES una hoja.");
        } else {
            System.out.println(nombre + " NO es una hoja.");
        }
    }

    /**
     * Construye el arbol inicial de 9 nodos del enunciado:
     *
     *              UNIVERSIDAD
     *              /         \
     *        FISEI             DIDE
     *        /   \             /   \
     *  SOFTWARE   TI     INVEST.  VINCULACION
     *    /   \
     * NIVEL1 NIVEL3
     */
    private static ArbolBinario<String> construirArbolInicial() {
        ArbolBinario<String> arbol = new ArbolBinario<>();
        arbol.crearRaiz("UNIVERSIDAD");

        arbol.agregarIzquierdo("UNIVERSIDAD", "FISEI");
        arbol.agregarDerecho("UNIVERSIDAD", "DIDE");

        arbol.agregarIzquierdo("FISEI", "SOFTWARE");
        arbol.agregarDerecho("FISEI", "TI");

        arbol.agregarIzquierdo("DIDE", "INVEST.");
        arbol.agregarDerecho("DIDE", "VINCULACION");

        arbol.agregarIzquierdo("SOFTWARE", "NIVEL1");
        arbol.agregarDerecho("SOFTWARE", "NIVEL3");

        return arbol;
    }

    // Opcion 8: ejecuta los casos de prueba obligatorios del enunciado
    private static void ejecutarCasosDePrueba() {
        System.out.println("=========== CASOS DE PRUEBA ===========");

        // Caso 1: arbol vacio
        ArbolBinario<String> vacio = new ArbolBinario<>();
        System.out.println("[Caso 1] Arbol vacio");
        System.out.println("  esVacio: " + vacio.esVacio());
        System.out.println("  Nodos  : " + vacio.contarNodos() + " (esperado 0)");
        System.out.println("  Altura : " + vacio.altura() + " (esperado -1)");

        // Caso 2: arbol con un solo nodo
        ArbolBinario<String> unico = new ArbolBinario<>();
        unico.crearRaiz("UNIVERSIDAD");
        System.out.println("[Caso 2] Arbol con un solo nodo");
        System.out.println("  Hojas  : " + unico.contarHojas() + " (esperado 1)");
        System.out.println("  Altura : " + unico.altura() + " (esperado 0)");

        // Caso 3: arbol inicial
        ArbolBinario<String> inicial = construirArbolInicial();
        System.out.println("[Caso 3] Arbol inicial");
        System.out.println("  Nodos  : " + inicial.contarNodos() + " (esperado 9)");
        System.out.println("  Hojas  : " + inicial.contarHojas() + " (esperado 5)");
        System.out.println("  Altura : " + inicial.altura() + " (esperado 3)");

        // Caso 4: intento de insertar en una posicion ocupada
        System.out.println("[Caso 4] Insertar en posicion ocupada");
        try {
            inicial.agregarIzquierdo("FISEI", "NUEVO");
            System.out.println("  ERROR: se inserto en una posicion ocupada.");
        } catch (IllegalStateException e) {
            System.out.println("  Rechazado correctamente: " + e.getMessage());
        }

        // Caso 5: grado de FISEI y SOFTWARE
        System.out.println("[Caso 5] Grado de nodos");
        System.out.println("  Grado de FISEI   : " + inicial.buscar("FISEI").grado() + " (esperado 2)");
        System.out.println("  Grado de SOFTWARE: " + inicial.buscar("SOFTWARE").grado() + " (esperado 2)");
    }
}
