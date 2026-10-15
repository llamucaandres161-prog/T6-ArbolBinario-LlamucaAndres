package negocio;

import java.util.ArrayList;
import java.util.List;
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
     * Clase interna que guarda el dibujo de un subarbol:
     * sus lineas de texto, su ancho y la columna donde esta el centro de su raiz.
     */
    private static class Bloque {
        List<String> lineas = new ArrayList<>();
        int ancho;
        int raiz;
    }

    /**
     * Muestra el arbol en consola con forma de arbol: cada padre queda
     * centrado sobre sus hijos y se unen con ramas "/" y "\".
     */
    public void mostrarArbol() {
        if (raiz == null) {
            System.out.println("El arbol esta vacio.");
            return;
        }
        Bloque dibujo = construirBloque(raiz);
        for (String linea : dibujo.lineas) {
            System.out.println(quitarEspaciosFinales(linea));
        }
    }

    /**
     * Construye (recursivamente) el dibujo del subarbol que empieza en "nodo".
     * Primero dibuja los hijos, luego los junta uno al lado del otro y
     * finalmente coloca al padre centrado encima con sus ramas.
     */
    private Bloque construirBloque(Nodo<T> nodo) {
        String etiqueta = String.valueOf(nodo.getDato());
        int largo = etiqueta.length();

        // Dibujo de cada hijo (null si no existe)
        Bloque izq = (nodo.getIzquierdo() != null) ? construirBloque(nodo.getIzquierdo()) : null;
        Bloque der = (nodo.getDerecho() != null) ? construirBloque(nodo.getDerecho()) : null;

        Bloque resultado = new Bloque();

        // Caso base: una hoja se dibuja solo con su nombre
        if (izq == null && der == null) {
            resultado.lineas.add(etiqueta);
            resultado.ancho = largo;
            resultado.raiz = largo / 2;
            return resultado;
        }

        int separacion = 3;       // espacios entre el subarbol izquierdo y el derecho
        int anchoCuerpo;          // ancho del dibujo de los hijos
        int centro;               // columna donde se centra el padre
        int conectorIzq = -1;     // columna de la rama "/" (-1 si no hay)
        int conectorDer = -1;     // columna de la rama "\" (-1 si no hay)
        List<String> cuerpo = new ArrayList<>();

        if (izq != null && der != null) {
            // Dos hijos: se colocan lado a lado y el padre va en el medio
            int colDer = izq.ancho + separacion;
            anchoCuerpo = colDer + der.ancho;
            int raizIzq = izq.raiz;
            int raizDer = colDer + der.raiz;
            centro = (raizIzq + raizDer) / 2;
            conectorIzq = Math.max(raizIzq, (raizIzq + centro) / 2);
            conectorDer = Math.min(raizDer, (centro + raizDer + 1) / 2);

            // Se unen las lineas de ambos subarboles (el mas bajo se rellena con espacios)
            int alto = Math.max(izq.lineas.size(), der.lineas.size());
            for (int k = 0; k < alto; k++) {
                String l = (k < izq.lineas.size()) ? izq.lineas.get(k) : espacios(izq.ancho);
                String r = (k < der.lineas.size()) ? der.lineas.get(k) : espacios(der.ancho);
                cuerpo.add(l + espacios(separacion) + r);
            }
        } else if (izq != null) {
            // Solo hijo izquierdo: el padre queda arriba y a la derecha del hijo
            anchoCuerpo = izq.ancho;
            centro = izq.raiz + 2;
            conectorIzq = izq.raiz + 1;
            cuerpo.addAll(izq.lineas);
        } else {
            // Solo hijo derecho: el padre queda arriba y a la izquierda del hijo
            anchoCuerpo = der.ancho;
            centro = der.raiz - 2;
            conectorDer = der.raiz - 1;
            cuerpo.addAll(der.lineas);
        }

        // Si el nombre del padre se sale por la izquierda, se desplaza todo a la derecha
        int inicio = centro - largo / 2;
        int desplazamiento = (inicio < 0) ? -inicio : 0;
        centro += desplazamiento;
        inicio += desplazamiento;
        if (conectorIzq >= 0) {
            conectorIzq += desplazamiento;
        }
        if (conectorDer >= 0) {
            conectorDer += desplazamiento;
        }
        int ancho = Math.max(anchoCuerpo + desplazamiento, inicio + largo);

        // Linea de las ramas
        char[] filaRamas = new char[ancho];
        for (int c = 0; c < ancho; c++) {
            filaRamas[c] = ' ';
        }
        if (conectorIzq >= 0) {
            filaRamas[conectorIzq] = '/';
        }
        if (conectorDer >= 0) {
            filaRamas[conectorDer] = '\\';
        }

        // Se arma el resultado: nombre del padre, ramas y dibujo de los hijos
        resultado.lineas.add(rellenar(espacios(inicio) + etiqueta, ancho));
        resultado.lineas.add(new String(filaRamas));
        for (String linea : cuerpo) {
            resultado.lineas.add(rellenar(espacios(desplazamiento) + linea, ancho));
        }
        resultado.ancho = ancho;
        resultado.raiz = centro;
        return resultado;
    }

    // Devuelve una cadena con "n" espacios
    private String espacios(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }

    // Rellena con espacios a la derecha hasta alcanzar el ancho indicado
    private String rellenar(String texto, int ancho) {
        return texto + espacios(ancho - texto.length());
    }

    // Quita los espacios sobrantes al final de una linea
    private String quitarEspaciosFinales(String texto) {
        int fin = texto.length();
        while (fin > 0 && texto.charAt(fin - 1) == ' ') {
            fin--;
        }
        return texto.substring(0, fin);
    }
}