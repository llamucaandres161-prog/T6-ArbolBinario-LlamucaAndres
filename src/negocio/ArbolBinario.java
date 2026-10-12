package negocio;

import modelo.Nodo;

/**
 * Clase ArbolBinario.
 * Contiene la logica del arbol: construccion de la jerarquia
 * institucional, visualizacion por consola y calculos recursivos
 * (total de nodos, nodos hoja y altura).
 */
public class ArbolBinario {

    // Nodo raiz del arbol
    private Nodo raiz;

    /**
     * Constructor: crea un arbol vacio.
     */
    public ArbolBinario() {
        this.raiz = null;
    }

    /**
     * Construye la jerarquia de dependencias academicas.
     *
     *              UNIVERSIDAD
     *              /         \
     *        FISEI             DIDE
     *        /   \             /   \
     *  SOFTWARE   TI     INVEST.  VINCULACION
     *    /   \
     * NIVEL1 NIVEL3
     */
    public void construirArbol() {
        // Nivel 0: raiz
        raiz = new Nodo("UNIVERSIDAD");

        // Nivel 1: hijos de la raiz
        Nodo fisei = new Nodo("FISEI");
        Nodo dide = new Nodo("DIDE");
        raiz.setIzquierdo(fisei);
        raiz.setDerecho(dide);

        // Nivel 2: hijos de FISEI
        Nodo software = new Nodo("SOFTWARE");
        Nodo ti = new Nodo("TI");
        fisei.setIzquierdo(software);
        fisei.setDerecho(ti);

        // Nivel 2: hijos de DIDE
        Nodo investigacion = new Nodo("INVESTIGACION");
        Nodo vinculacion = new Nodo("VINCULACION");
        dide.setIzquierdo(investigacion);
        dide.setDerecho(vinculacion);

        // Nivel 3: hijos de SOFTWARE
        software.setIzquierdo(new Nodo("NIVEL1"));
        software.setDerecho(new Nodo("NIVEL3"));
    }

    /**
     * Muestra el arbol por consola con sangria segun el nivel.
     * Se imprime en preorden: primero el nodo y luego sus hijos.
     */
    public void mostrarArbol() {
        if (raiz == null) {
            System.out.println("El arbol esta vacio.");
            return;
        }
        mostrar(raiz, 0, "RAIZ");
    }

    // Metodo recursivo auxiliar para imprimir el arbol
    private void mostrar(Nodo nodo, int nivel, String posicion) {
        // Caso base: nodo nulo, no se imprime nada
        if (nodo == null) {
            return;
        }

        // Genera la sangria (4 espacios por nivel)
        StringBuilder sangria = new StringBuilder();
        for (int i = 0; i < nivel; i++) {
            sangria.append("    ");
        }

        // Imprime el nodo con su posicion (RAIZ, IZQ o DER)
        System.out.println(sangria + "[" + posicion + "] " + nodo.getDato());

        // Llamadas recursivas para los hijos
        mostrar(nodo.getIzquierdo(), nivel + 1, "IZQ");
        mostrar(nodo.getDerecho(), nivel + 1, "DER");
    }

    /**
     * Cuenta el total de nodos del arbol.
     * @return cantidad de nodos
     */
    public int contarNodos() {
        return contarNodos(raiz);
    }

    // Recursivo: nodos = 1 (el actual) + nodos del subarbol izq + der
    private int contarNodos(Nodo nodo) {
        // Caso base: un arbol vacio tiene 0 nodos
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodos(nodo.getIzquierdo()) + contarNodos(nodo.getDerecho());
    }

    /**
     * Cuenta los nodos hoja (nodos sin hijos).
     * @return cantidad de hojas
     */
    public int contarHojas() {
        return contarHojas(raiz);
    }

    // Recursivo: si el nodo es hoja suma 1, si no, suma las hojas de sus hijos
    private int contarHojas(Nodo nodo) {
        // Caso base 1: arbol vacio
        if (nodo == null) {
            return 0;
        }
        // Caso base 2: el nodo es una hoja
        if (nodo.esHoja()) {
            return 1;
        }
        return contarHojas(nodo.getIzquierdo()) + contarHojas(nodo.getDerecho());
    }

    /**
     * Calcula la altura del arbol medida en aristas.
     * Un arbol vacio tiene altura -1 y un solo nodo tiene altura 0.
     * @return altura del arbol
     */
    public int calcularAltura() {
        return calcularAltura(raiz);
    }

    // Recursivo: altura = 1 + la mayor altura entre los dos subarboles
    private int calcularAltura(Nodo nodo) {
        // Caso base: arbol vacio
        if (nodo == null) {
            return -1;
        }
        int alturaIzquierda = calcularAltura(nodo.getIzquierdo());
        int alturaDerecha = calcularAltura(nodo.getDerecho());
        return 1 + Math.max(alturaIzquierda, alturaDerecha);
    }
}
