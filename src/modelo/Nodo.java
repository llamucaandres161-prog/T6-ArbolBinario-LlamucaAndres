package modelo;

/**
 * Clase Nodo.
 * Representa un nodo del arbol binario. Cada nodo guarda el nombre
 * de una dependencia y puede tener como maximo dos hijos:
 * uno izquierdo y uno derecho.
 */
public class Nodo {

    // Dato almacenado en el nodo (nombre de la dependencia)
    private String dato;

    // Referencia al hijo izquierdo (null si no existe)
    private Nodo izquierdo;

    // Referencia al hijo derecho (null si no existe)
    private Nodo derecho;

    /**
     * Constructor: crea un nodo sin hijos.
     * @param dato nombre de la dependencia
     */
    public Nodo(String dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    // Devuelve el dato del nodo
    public String getDato() {
        return dato;
    }

    // Modifica el dato del nodo
    public void setDato(String dato) {
        this.dato = dato;
    }

    // Devuelve el hijo izquierdo
    public Nodo getIzquierdo() {
        return izquierdo;
    }

    // Asigna el hijo izquierdo
    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    // Devuelve el hijo derecho
    public Nodo getDerecho() {
        return derecho;
    }

    // Asigna el hijo derecho
    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }

    /**
     * Indica si el nodo es hoja (no tiene ningun hijo).
     * @return true si no tiene hijos, false en caso contrario
     */
    public boolean esHoja() {
        return izquierdo == null && derecho == null;
    }
}
