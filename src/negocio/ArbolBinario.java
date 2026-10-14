package negocio;

import modelo.Nodo;

/**
 * Clase generica ArbolBinario<T>.
 * Contiene las operaciones de construccion, consulta y analisis del arbol.
 * El conteo de nodos, hojas y la altura se implementan con recursividad.
 *
 * @param <T> tipo de dato almacenado en los nodos
 */
public class ArbolBinario<T> {

    // Nodo raiz del arbol (null si el arbol esta vacio)
    private Nodo<T> raiz;

    /**
     * Constructor: crea un arbol vacio.
     */
    public ArbolBinario() {
        this.raiz = null;
    }

    // ---------------- Construccion ----------------

    /**
     * Crea el nodo raiz del arbol.
     * @param dato informacion de la raiz
     * @throws IllegalStateException si el arbol ya tiene raiz
     */
    public void crearRaiz(T dato) {
        if (raiz != null) {
            throw new IllegalStateException("El arbol ya tiene una raiz.");
        }
        raiz = new Nodo<>(dato);
    }

    /**
     * Agrega un hijo izquierdo al nodo cuyo dato sea "padre".
     * @param padre dato del nodo padre
     * @param dato  dato del nuevo hijo
     * @throws IllegalArgumentException si el padre no existe
     * @throws IllegalStateException    si la posicion izquierda ya esta ocupada
     */
    public void agregarIzquierdo(T padre, T dato) {
        Nodo<T> nodoPadre = buscar(padre);
        if (nodoPadre == null) {
            throw new IllegalArgumentException("No existe el nodo padre: " + padre);
        }
        if (nodoPadre.getIzquierdo() != null) {
            throw new IllegalStateException("La posicion izquierda de " + padre
                    + " ya esta ocupada por " + nodoPadre.getIzquierdo().getDato());
        }
        nodoPadre.setIzquierdo(new Nodo<>(dato));
    }

    /**
     * Agrega un hijo derecho al nodo cuyo dato sea "padre".
     * @param padre dato del nodo padre
     * @param dato  dato del nuevo hijo
     * @throws IllegalArgumentException si el padre no existe
     * @throws IllegalStateException    si la posicion derecha ya esta ocupada
     */
    public void agregarDerecho(T padre, T dato) {
        Nodo<T> nodoPadre = buscar(padre);
        if (nodoPadre == null) {
            throw new IllegalArgumentException("No existe el nodo padre: " + padre);
        }
        if (nodoPadre.getDerecho() != null) {
            throw new IllegalStateException("La posicion derecha de " + padre
                    + " ya esta ocupada por " + nodoPadre.getDerecho().getDato());
        }
        nodoPadre.setDerecho(new Nodo<>(dato));
    }

    // ---------------- Consultas ----------------

    /**
     * Verifica si el arbol esta vacio.
     * @return true si no tiene raiz
     */
    public boolean esVacio() {
        return raiz == null;
    }

    /**
     * Busca un nodo por su dato (recorrido en preorden).
     * @param dato dato a buscar
     * @return el nodo encontrado o null si no existe
     */
    public Nodo<T> buscar(T dato) {
        return buscar(raiz, dato);
    }

    // Busqueda recursiva: revisa el nodo actual y luego sus subarboles
    private Nodo<T> buscar(Nodo<T> nodo, T dato) {
        // Caso base 1: llegamos a un hueco, no se encontro
        if (nodo == null) {
            return null;
        }
        // Caso base 2: se encontro el dato
        if (nodo.getDato().equals(dato)) {
            return nodo;
        }
        // Se busca primero en el subarbol izquierdo y luego en el derecho
        Nodo<T> enIzquierdo = buscar(nodo.getIzquierdo(), dato);
        if (enIzquierdo != null) {
            return enIzquierdo;
        }
        return buscar(nodo.getDerecho(), dato);
    }

    // ---------------- Analisis (recursivos) ----------------

    /**
     * Cuenta el total de nodos del arbol.
     * @return cantidad de nodos
     */
    public int contarNodos() {
        return contarNodos(raiz);
    }

    // nodos = 1 (el actual) + nodos del subarbol izquierdo + nodos del derecho
    private int contarNodos(Nodo<T> nodo) {
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

    // Si el nodo es hoja suma 1; si no, suma las hojas de sus subarboles
    private int contarHojas(Nodo<T> nodo) {
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
     * Arbol vacio = -1, un solo nodo = 0.
     * @return altura del arbol
     */
    public int altura() {
        return altura(raiz);
    }

    // altura = 1 + la mayor altura entre los dos subarboles
    private int altura(Nodo<T> nodo) {
        // Caso base: arbol vacio
        if (nodo == null) {
            return -1;
        }
        int alturaIzquierda = altura(nodo.getIzquierdo());
        int alturaDerecha = altura(nodo.getDerecho());
        return 1 + Math.max(alturaIzquierda, alturaDerecha);
    }

    // ---------------- Visualizacion ----------------

    /**
     * Muestra la estructura del arbol en consola, con sangria por nivel.
     */
    public void mostrarArbol() {
        if (raiz == null) {
            System.out.println("El arbol esta vacio.");
            return;
        }
        mostrar(raiz, 0, "RAIZ");
    }

    // Impresion recursiva en preorden con 4 espacios de sangria por nivel
    private void mostrar(Nodo<T> nodo, int nivel, String posicion) {
        // Caso base: no hay nodo, no se imprime nada
        if (nodo == null) {
            return;
        }
        StringBuilder sangria = new StringBuilder();
        for (int i = 0; i < nivel; i++) {
            sangria.append("    ");
        }
        System.out.println(sangria + "[" + posicion + "] " + nodo.getDato());

        mostrar(nodo.getIzquierdo(), nivel + 1, "IZQ");
        mostrar(nodo.getDerecho(), nivel + 1, "DER");
    }
}
