package modelo;

/**
 * Clase generica Nodo<T>.
 * Representa un nodo del arbol binario. Almacena un dato de tipo T
 * y puede tener como maximo dos hijos: izquierdo y derecho.
 *
 * @param <T> tipo de dato almacenado en el nodo
 */
public class Nodo<T> {

    // Informacion almacenada en el nodo
    private T dato;

    // Referencia al hijo izquierdo (null si no existe)
    private Nodo<T> izquierdo;

    // Referencia al hijo derecho (null si no existe)
    private Nodo<T> derecho;

    /**
     * Constructor: crea un nodo sin hijos.
     * @param dato informacion a almacenar
     */
    public Nodo(T dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    /**
     * Determina si el nodo es una hoja (no tiene ningun hijo).
     * @return true si no tiene hijos, false en caso contrario
     */
    public boolean esHoja() {
        return izquierdo == null && derecho == null;
    }

    /**
     * Devuelve el grado del nodo, es decir, su numero de hijos (0, 1 o 2).
     * @return cantidad de hijos del nodo
     */
    public int grado() {
        int hijos = 0;
        if (izquierdo != null) {
            hijos++;
        }
        if (derecho != null) {
            hijos++;
        }
        return hijos;
    }

    // ---------------- Getters y setters ----------------

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Nodo<T> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Nodo<T> getDerecho() {
        return derecho;
    }

    public void setDerecho(Nodo<T> derecho) {
        this.derecho = derecho;
    }
}
