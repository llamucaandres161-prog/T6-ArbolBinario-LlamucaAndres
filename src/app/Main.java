package app;

import negocio.ArbolBinario;

/**
 * Clase Main.
 * Punto de entrada del programa: construye el arbol de dependencias,
 * lo muestra por consola y presenta el analisis de su estructura.
 */
public class Main {

    public static void main(String[] args) {
        // Se crea el arbol y se construye la jerarquia institucional
        ArbolBinario arbol = new ArbolBinario();
        arbol.construirArbol();

        // Visualizacion del arbol
        System.out.println("=== ARBOL BINARIO DE DEPENDENCIAS ===");
        arbol.mostrarArbol();

        // Analisis de la estructura (calculos recursivos)
        System.out.println();
        System.out.println("=== ANALISIS DE LA ESTRUCTURA ===");
        System.out.println("Total de nodos : " + arbol.contarNodos());
        System.out.println("Nodos hoja     : " + arbol.contarHojas());
        System.out.println("Altura (aristas): " + arbol.calcularAltura());
    }
}
